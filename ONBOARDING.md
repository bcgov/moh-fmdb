# FMDB Developer Onboarding Guide

> *The following document was generated using Claude. Verify anything written in this document yourself before taking it to be fact.*

Welcome to the FMDB project. This guide is written for a developer joining the team after the
2026 AWS Landing Zone (LZA) migration and Payara upgrade. It covers what the application is,
how to build and run it locally, how the AWS infrastructure and deployment pipeline work, and —
importantly — the history and gotchas from the migration project, since the developers who did
that work are no longer available to ask.

Everything in here was verified against the repository (branch `LZA-Migration_Payara7.2026.4`)
except where explicitly marked *"from knowledge-transfer notes"* — those items describe manual,
out-of-repo steps that you should verify against the AWS console and Confluence before relying
on them.

---

## 1. What is FMDB?

FMDB is the BC Ministry of Health **Formulary Management Database** — a system for tracking
drugs reviewed for benefit inclusion under PharmaCare. It is a Jakarta EE application deployed
as a single EAR containing:

| Module | What it is |
|---|---|
| `fmdb/FMDB-war` | The **secure internal app** ("Formulary Management"), context root `/FMDB`. Search, submission details, admin CRUD pages, and seven reports. Protected by Keycloak OIDC with roles `MOHUSER` and `PSDADMIN`. |
| `fmdb/FMDB-war-pub` | The **anonymous public site** ("Formulary Management Public Access Site"), context root `/`. A single public drug-search page (`DrugSearch.xhtml`) plus an attachment-retrieval servlet. |
| `fmdb/FMDB-ejb` | Shared business/entity layer (JPA entities, business services, OIDC config). |
| `fmdb/FMDB-ear` | Assembles the two WARs and the EJB module into `FMDB-ear.ear`. |

The UI is **JSF (Jakarta Faces) with PrimeFaces**, themed with the Aristo theme. The database is
**PostgreSQL** (schema `fmd`), accessed through two JTA datasources defined in
`fmdb/FMDB-ear/src/main/application/META-INF/glassfish-resources.xml`:
`jdbc/fmdb` (internal app) and `jdbc/fmdb_pub` (public app). Authentication for the internal app
is Keycloak OIDC via the Payara OpenID Connect connector, configured through the
`fmdb/oidc_properties` custom resource in the same file.

## 2. Tech stack at a glance (post-migration)

| Component | Version | Where it's pinned |
|---|---|---|
| Payara Server (Community, full profile) | **7.2026.4** | `fmdb/Dockerfile` (`payara/server-full:7.2026.4-jdk25`) |
| Java | **25** (build and runtime) | `fmdb/Dockerfile` (`maven:3.9-eclipse-temurin-25-noble`) |
| Jakarta EE API | **10.0.0** | `fmdb/pom.xml` |
| PrimeFaces | **15.0.0** (classifier `jakarta`) | `fmdb/pom.xml` |
| PrimeFaces Aristo theme | 1.0.1 (vendored jar) | `fmdb/lib/aristo-1.0.1.jar` |
| Payara OIDC security connector | 2.3.0 (must match the Payara server) | `fmdb/pom.xml` |
| PostgreSQL JDBC driver | 42.4.0 | `fmdb/postgresql-42.4.0.jar`, copied into `domain1/lib` |
| PostgreSQL (local dev) | 18-alpine | `docker-compose.yml` |
| Aurora PostgreSQL (AWS) | 13.23, Serverless v2 | `Infrastructure/aurora-v2.tf` |
| Terraform / Terragrunt (dev pipeline) | 1.9.8 / 0.68.7 | `.github/workflows/dev-workflow.yaml` |

Known inconsistencies you'll notice (candidates for cleanup, not blockers):

- `README.md` says JDK 21 in the prerequisites; the Docker build actually uses JDK 25.
- `fmdb/pom.xml` still sets `maven-compiler-plugin` source/target to 11.
- `fmdb/ojdbc11.jar` (Oracle driver) is a leftover from the pre-PostgreSQL era and is
  referenced by nothing.
- The test and prod workflows still run Terraform 1.2.2 / Terragrunt 0.37.1 while dev was
  bumped to 1.9.8 / 0.68.7 (see §7).

## 3. Repository layout

```
moh-fmdb/
├── fmdb/                    # The application (Maven multi-module project)
│   ├── pom.xml              # Parent POM: modules, shared dependency versions
│   ├── Dockerfile           # Two-stage build (Maven build → Payara runtime)
│   ├── FMDB-ejb/            # Entities + business layer
│   ├── FMDB-war/            # Internal secured webapp (/FMDB)
│   ├── FMDB-war-pub/        # Public webapp (/)
│   ├── FMDB-ear/            # EAR assembly + glassfish-resources.xml (datasources, OIDC)
│   ├── lib/aristo-1.0.1.jar # Vendored PrimeFaces theme (installed into local .m2 at build)
│   ├── errorhandler-1.0.jar # Vendored CGI error-handler jar (system-scope dependency)
│   ├── postgresql-42.4.0.jar
│   └── pre-boot-commands.asadmin  # Sets JVM -Xmx/-Xms from env vars
├── Infrastructure/          # The single Terraform root module (all envs share it)
├── Terraform/               # Terragrunt live config: terragrunt.hcl + dev/ test/ prod/ sandbox/
├── Database Scripts/        # setup_db.sh + SQL to create the local database
├── .github/workflows/       # CI/CD (see §7)
├── docker-compose.yml       # Local dev: postgres + the app
├── macos-setup.sh           # Helper: export a corporate root CA from the macOS keychain
└── .env.template            # Copy to .env for local runs (gitignored)
```

## 4. Building and running locally

Full instructions are in `README.md`; the short version:

### Docker (recommended)

```bash
cp .env.template .env        # fill in PG_PASSWORD, Keycloak values, etc.
docker compose up db         # start postgres
(cd "Database Scripts" && ./setup_db.sh)   # create DB, schema, tables, constraints
docker compose up            # builds fmdb:latest and starts Payara
# App: http://localhost:8080/FMDB   Payara admin: https://localhost:4848
```

If your machine sits behind corporate TLS interception (e.g. Zscaler), pass the root CA into the
build: `--build-arg CA_ROOT=zscaler-root-ca` (on macOS, `./macos-setup.sh "<CA common name>"`
exports the cert from the System Keychain to a `.pem` next to the script).

### Bare metal

Two vendored jars must be installed into your local Maven repo **before** the first build —
this replaced the old CGI Artifactory (`artifactory.hlth.gov.bc.ca`), which the project no
longer uses:

```bash
mvn install:install-file -Dfile=fmdb/errorhandler-1.0.jar -DgroupId=com.cgi.fmdb \
    -DartifactId=errorhandler -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=fmdb/lib/aristo-1.0.1.jar -DgroupId=org.primefaces.themes \
    -DartifactId=aristo -Dversion=1.0.1 -Dpackaging=jar -DgeneratePom=true
mvn -f fmdb/pom.xml clean package
```

Then download a Payara matching the Docker image version (7.2026.4), copy
`fmdb/postgresql-42.4.0.jar` into `$PAYARA_HOME/glassfish/domains/domain1/lib`, source `.env`,
start the domain with `--prebootcommandfile fmdb/pre-boot-commands.asadmin`, and
`asadmin deploy --force=true fmdb/FMDB-ear/target/FMDB-ear.ear`.

Note: `README.md` §database claims `setup_db.sh` creates no tables — that's outdated; the
script runs `create_db.sql`, `create_schema.sql`, `fmdb_postgres.sql`, and
`fmdb_constraints.sql` in order. The table/constraint SQL originated from a dump of the old
environment's database.

## 5. Application architecture notes

- **Pages** live in `fmdb/FMDB-war/src/main/webapp/pages/` (Search, Details, Reports, Admin,
  seven `reports/*.xhtml`, admin CRUD pages). Backing beans are in
  `fmdb/FMDB-war/src/main/java/fmdbwar/pages/`.
- **Reports** are a two-step flow: a JSF page collects parameters into the session, then a
  plain servlet (`fmdbwar/servlet/*ReportServlet.java`, mapped under `/pages/reports/`)
  generates the CSV/output. See §8 for the PrimeFaces-15 quirks in this flow.
- **Security**: `web.xml` constraints send `/pages/admin/*` to `PSDADMIN` and everything else
  under `/pages/` to `MOHUSER`/`PSDADMIN`. Roles come from the Keycloak `fmdb_role` claim.
  Session timeout is 33 minutes (Keycloak SSO idle 30 + 3).
- **Persistence**: `FMDB-ejb/src/main/resources/META-INF/persistence.xml` lists ~35 entities
  explicitly with `exclude-unlisted-classes=true` — if you add an entity, you must add it there.
  The public WAR has its own persistence unit with a single view entity (`PublicAccessVw`).
- **Error handling**: exceptions route to `com.sun.errorhandler.ExceptionHandler` from the
  vendored `errorhandler-1.0.jar`.

## 6. AWS infrastructure

Everything runs in the BC Gov **AWS Secure Landing Zone (LZA)** in `ca-central-1`. Key concept:
each project/account combination is identified by a **license plate** — a short code (e.g.
`abc123`) that appears in account names, state-bucket names, and hostnames. The license plate
is *not* committed to the repo; it is injected as the `LICENSE_PLATE` environment variable
(GitHub secret `MY_LICENSE_PLATE`) and consumed by Terragrunt.

### Terraform / Terragrunt structure

- `Infrastructure/` is one Terraform root module shared by all environments.
- `Terraform/terragrunt.hcl` is the root Terragrunt config. It *generates* `backend.tf`
  (S3 state bucket `terraform-remote-state-<plate>-<env>`, DynamoDB lock table
  `terraform-remote-state-lock-<plate>`), `provider.tf`, and `terragrunt.auto.tfvars`
  (including `license_plate`). Nothing environment-specific is committed as `.tfvars`.
- `Terraform/{dev,test,prod,sandbox}/terragrunt.hcl` set per-env values: the public domain
  (`fmdbd`/`fmdbt`/`fmdb`.hlth.gov.bc.ca), app port (8181), and Fargate sizing. The
  environment name itself is derived from the directory name.

To work with it locally: export `LICENSE_PLATE` (and AWS credentials for the target account),
`cd Terraform/dev`, then `terragrunt plan`. **Always run `terragrunt plan` locally before
letting the pipeline apply** — that was the migration team's core workflow: plan, read the
errors, fix, repeat. The Terraform resources map essentially 1:1 to what you see in the AWS
console, so the console is your reference when a plan looks wrong.

A `resource` block creates/updates something; a `data` block only looks something up and is not
controlled by the deploy. This distinction matters here more than usual, because the landing
zone pre-provisions a lot (VPC, subnets, security groups, certificates) that we look up rather
than create. If a `resource` tries to create something that already exists outside Terraform
state (common after console experimentation), the apply fails — you either import it into state
or delete the console-created copy. Terragrunt's state locking can also get stuck fighting
resources created outside Terraform; check the lock table / `terragrunt.hcl` if applies hang.

### Network (all data lookups — LZA provides these)

`Infrastructure/network.tf` looks up, per environment (`Dev`/`Test`/`Prod`):

- The VPC by `tag:Name = <Env>` (old landing zone used `<Env>_vpc`).
- Subnets: `<Env>-Web-MainTgwAttach-{A,B}`, `<Env>-App-{A,B}`, `<Env>-Data-{A,B}`
  (old names were `Web_<Env>_az{a,b}_net` etc. — renaming these lookups was a big part of the
  migration).
- Security groups by plain name: `Web`, `App`, `Data` (previously `Web_sg` etc.).

### Traffic path: DNS → CloudFront → ALB → Fargate

```
fmdb.hlth.gov.bc.ca (DNS, managed outside this repo)
  → CloudFront distribution (cloudfront.tf, cert in us-east-1)
    → ALB "front_end" (alb.tf, internal, cert in ca-central-1,
      exposed through the LZA perimeter by the tag public = "true")
      → target group :8181 (HTTPS) → ECS Fargate service (2 tasks)
        → Aurora PostgreSQL Serverless v2 (aurora-v2.tf)
```

Details worth knowing:

- **CloudFront is the internet-facing edge** — it defines what is exposed publicly. The
  distribution has a single custom origin: the ALB's perimeter hostname
  `fmdb.<plate>-<env>.stratus.cloud.gov.bc.ca` (the old landing zone used
  `*.nimbus.cloud.gov.bc.ca`). It uses managed policies `CachingDisabled` +
  `AllViewerExceptHostHeader`, geo-restricts to Canada, and has `prevent_destroy` set.
  Behaviours (and request/response headers, e.g. security headers) are configured on the
  distribution — currently only the default behaviour exists in Terraform; if you need custom
  headers, that's where they go.
- **The ALB is created by this repo** (`aws_lb.front_end` in `Infrastructure/alb.tf`). Under
  the old landing zone we consumed a shared, pre-provisioned ALB via a `data` lookup; under the
  new LZA each project creates its own. It is `internal = true` — public reachability comes
  from tagging it `public = "true"`, which the LZA perimeter service picks up to publish it at
  the `stratus.cloud.gov.bc.ca` hostname. There is an HTTPS:443 listener (cert +
  host-header listener rule routing to the app) and an HTTP:80 listener forwarding straight to
  the target group.
- **Certificates are ACM data lookups, not resources.** The ALB cert must exist (status
  ISSUED) in `ca-central-1` and the CloudFront cert in `us-east-1` for `var.domain`, *before*
  `terragrunt apply` will succeed. Request Amazon-issued certificates through ACM (the old
  service used Entrust; that's gone). **Issue certificates early** — validation takes time and
  the deploy hard-fails without them. From knowledge-transfer notes: ACM's certificate status
  check can be toggled during setup if you need to sequence things.
- **Database**: Aurora PostgreSQL Serverless v2 (module `terraform-aws-modules/rds-aurora/aws`
  v7.7.1), cluster `fmdb-cluster-<env>`, 0.5–1 ACU, engine version pinned at `13.23`. Nothing
  disables auto-minor-version upgrades, so **AWS will bump the minor version on its own and
  drift the pin** — that already happened once (13.9 → 13.23, commit `1bda15d`); if a plan
  suddenly wants to "downgrade" the engine, update the version in `aurora-v2.tf` to match
  reality instead.
- **Secrets** (`Infrastructure/secretsmanager.tf`): `jdbc_setting`, `fmdb_user`,
  `fmdb_keycloak-client-secret`, `redirect_uri`, `fmdb_provider_uri`, `fmdb_siteminder_uri`.
  Terraform seeds them all with `"changeme"` and then ignores changes — **real values are set
  by hand in the console**. During the migration they were copied one by one from the old
  account. The Aurora module also creates its own master/api credential secrets
  (`fmdb-master-creds-*`, `fmdb-api-creds-*`) with generated passwords.
- **Fargate** (`Infrastructure/fargate.tf`): cluster `fmdb_cluster`, service
  `fmdb-<env>-service`, task family `fmdb-<env>-task`, image
  `<account>.dkr.ecr.ca-central-1.amazonaws.com/fmdb:latest`. Database and Keycloak settings
  are injected from Secrets Manager at task start. The **ECR repository itself is not managed
  by Terraform** — it's created through the platform's registry process (see §7).
- **Monitoring** (`Infrastructure/cloudwatch_alarms.tf`): SNS topic + alarms for ECS CPU/memory,
  task failures, Aurora CPU/connections/disk queue, and a billing alarm at $375.
- **Bastion host**: not in this repo, but the landing zone provides one per account — it's the
  convenient way to tunnel into the private network, e.g. to reach the database with `psql`
  (used heavily during the data migration, §9). The new LZA's bastion setup differs slightly
  from the old one; find the current instructions on the platform Confluence.

## 7. CI/CD and deployment

All deploy workflows are **manual** (`workflow_dispatch`) from the GitHub Actions tab. Pick the
workflow for the target environment, choose the environment and the branch to run from.

| Workflow | Purpose |
|---|---|
| `dev-workflow.yaml` | Build + deploy to dev. Most up to date (TF 1.9.8 / TG 0.68.7, runs `terragrunt run-all init -upgrade` before apply). |
| `test-workflow.yaml` | Build + deploy to test (still TF 1.2.2 / TG 0.37.1, no explicit init). |
| `prod-workflow.yaml` | Build + deploy to prod (still TF 1.2.2 / TG 0.37.1). |
| `zap-scan.yml` | OWASP ZAP baseline scan against `vars.APPLICATION_URL` of the chosen environment. |
| `rollback_to_tag.yaml` | Rolls back a **GitHub release/tag only** — it does not touch ECR or Terraform. |
| `tools-workflow.yaml`, `docker-image.yml` | **Legacy/stale** — reference old secrets, a deleted `MyFMDB/` path, and a `Terraform/tools` dir that doesn't exist. Don't use; safe to delete. |

Each env workflow runs two jobs:

1. **`docker_push`** — checks out `DESIGNATED_BRANCH` (the branch you dispatched from), builds
   `fmdb/Dockerfile`, pushes to ECR as `<AWS_ECR_URI_*>:latest`.
2. **`terraform_apply`** — re-checks out the same branch, assumes the deploy role, and runs
   `terragrunt run-all apply --terragrunt-non-interactive` in `Terraform/<env>`.

**Authentication is GitHub OIDC** — no stored AWS keys. Workflows request an ID token
(`permissions: id-token: write`) and assume an IAM role via
`aws-actions/configure-aws-credentials`. The roles and the ECR repositories are **not** defined
in this repo; they're set up per-account through the platform process documented in Confluence:
**Registries → "GitHub Actions Setup and Deployment" (HLTH AMS space)**. That process creates
the private ECR repository and the OIDC deploy role (named like `fmdb_OIDC_GitHub_Role`); all
you need from it are the repo URI and the license plate, which land in GitHub secrets.

GitHub secrets per environment (renamed during the migration because the accounts changed):

| Environment | ECR URI secret | Deploy role secret |
|---|---|---|
| dev | `AWS_ECR_URI_DEV` | `TERRAFORM_DEPLOY_ROLE_ARN_DEV` |
| test | `AWS_ECR_URI_TEST_2` | `TERRAFORM_DEPLOY_ROLE_ARN_TEST_2` |
| prod | `AWS_ECR_URI_PROD_2` | `TERRAFORM_DEPLOY_ROLE_ARN_PROD_2` |
| all | `MY_LICENSE_PLATE` (→ `LICENSE_PLATE` env var for Terragrunt) | |

The `_2` suffixes exist because the old-account secrets kept their original names; when in
doubt, the secret referenced in the workflow YAML is the source of truth — "just look at the
yaml file and see what is necessary."

**Recommended practice from the migration team:** run `terragrunt plan` locally against the
target environment before dispatching the workflow, so the pipeline apply holds no surprises.

## 8. History: the 2026 LZA migration and Payara upgrade

This project moved from the old BC Gov landing zone (`nimbus.cloud.gov.bc.ca`, old account) to
the new Secure Landing Zone (`stratus.cloud.gov.bc.ca`, new accounts/license plate) and
simultaneously upgraded the runtime. Knowing what changed — and why — will save you hours when
something old resurfaces. The full change set is `git diff main...LZA-Migration_Payara7.2026.4`
(58 commits).

### 8.1 Runtime upgrade chain

Upgrading Payara pulled a chain of upgrades behind it ("the rabbit hole"):

1. **Payara** → Community 7.2026.4 (`fmdb/Dockerfile`), which required
2. **Java** → JDK 25 (build image and runtime image), and
3. **Jakarta EE 10** — Payara 7 is Jakarta-namespace only. Every `javax.*` import, `web.xml`
   servlet class, and even the JSF resource URL pattern (`/javax.faces.resource/*` →
   `/jakarta.faces.resource/*` in the `web.xml` security constraint) was renamed to
   `jakarta.*`. The approach was literally search-and-replace on `javax.` followed by fixing
   what broke. Dependency swap: `javax:javaee-api:8.0` → `jakarta.platform:jakarta.jakartaee-api:10.0.0`.
4. **PrimeFaces** → 15.0.0 with the `jakarta` classifier. The Aristo theme is no longer
   published anywhere reachable, so `aristo-1.0.1.jar` is vendored in `fmdb/lib/` and installed
   into the local Maven repo during the Docker build.
5. **Artifactory removal** — `artifactory.hlth.gov.bc.ca` was dropped from `pom.xml`;
   `errorhandler-1.0.jar` became a vendored system-scope jar.

### 8.2 PrimeFaces 15 breakages and how they were fixed

These are the concrete app bugs the upgrade caused — the patterns matter because other pages
may still harbour the same issues:

- **Report pages froze on "Run Report"** — the loading dialog never closed and the redirect to
  the report servlet silently failed. Two fixes, applied to all seven report pages:
  - In the backing beans (`fmdbwar/pages/reports/*.java`), the JSF
    `externalContext.redirect(...)` inside an AJAX request stopped working; it was replaced
    with `PrimeFaces.current().executeScript("window.location.href='...'")` (client-side
    redirect). See commit `dae3927` — earlier attempts that didn't work were removed.
  - The `p:commandButton` on each report page got `oncomplete="location.reload();"` so the
    button resets, and the global spinner in `WEB-INF/templates/fmdbTemplate.xhtml` changed
    `p:ajaxStatus onsuccess=` → `oncomplete=` (in PrimeFaces 15, `onsuccess` no longer fires
    reliably for this flow).
- **Beans needed explicit CDI annotations** — e.g. `SingleDrugSubStatusReport` gained
  `@Named("SingleDrugSubStatusReport")` and `@RequestScoped` (jakarta.inject / 
  jakarta.enterprise.context). If a page's EL can't find its bean after an upgrade, check the
  bean is CDI-annotated.
- **`p:fileUpload` attribute rename**: `sizeLimit` → `maxFileSize`
  (`WEB-INF/fragments/drugUpdateStatus.xhtml`).
- **Content-Security-Policy / ZAP friction** *(from knowledge-transfer notes)*: PrimeFaces
  renders a lot of **inline styles and scripts**, and it takes CSP directives from the
  application's headers. A CSP whose `style-src` needs `unsafe-hashes`/inline allowances
  triggers OWASP ZAP findings, so header hardening is a balancing act — headers can be added
  at CloudFront (behaviours) or in the app. One symptom actually hit during the upgrade: the
  **sign-out button ended up with no code attached** (its PrimeFaces-generated inline handler
  didn't attach), which is worth checking whenever CSP or PrimeFaces versions change.

### 8.3 Infrastructure changes (old landing zone → LZA)

| Area | Old | New |
|---|---|---|
| Public hostname | `fmdb.<plate>-<env>.nimbus.cloud.gov.bc.ca` | `fmdb.<plate>-<env>.stratus.cloud.gov.bc.ca` |
| License plate | old account plate (`ynr9ed`, still visible in stale tfvars/comments) | new plate, in `MY_LICENSE_PLATE` secret only |
| ALB | shared, pre-provisioned; consumed via `data` lookup | **created by this repo**, internal + `public = "true"` tag for perimeter exposure; own 443 listener/cert/rule + 80 listener |
| CloudFront cert | `aws_acm_certificate` resource with DNS validation | `data` lookup of a pre-issued ACM cert in us-east-1 (`aliases` removed from the distribution) |
| VPC/subnet/SG names | `<Env>_vpc`, `Web_<Env>_aza_net`, `Web_sg` | `<Env>`, `<Env>-Web-MainTgwAttach-A`, `Web` |
| Aurora | 13.9, `engine-mode = serverless` filter | 13.23 (AWS auto-upgraded), filter removed |
| CI secrets | `AWS_ECR_URI`, `TERRAFORM_DEPLOY_ROLE_ARN` | per-env `_DEV` / `_TEST_2` / `_PROD_2` variants |
| CI checkout | hardcoded `ref: terraform` | `DESIGNATED_BRANCH = github.ref_name` (deploys the branch you dispatch from) |

The migration method was iterative: update the Terraform, run `terragrunt plan`, fix the next
error it reports, repeat until clean, then apply.

## 9. Operational runbook

### Requesting certificates (do this first for any new environment)

1. In ACM, request Amazon-issued certificates for the domain: one in **ca-central-1** (for the
   ALB listener) and one in **us-east-1** (for CloudFront). The old Entrust process is retired.
2. Complete validation and wait for status **ISSUED** — the Terraform `data` lookups filter on
   ISSUED and the apply fails until then. Issue them well before deployment day.
3. Terraform then attaches them automatically (`alb.tf`, `cloudfront.tf` look them up by domain).

### Database migration / backup-restore *(from knowledge-transfer notes)*

The data in the new environment came from a dump of the old environment:

1. Tunnel into the network via the account's **bastion host** (the standard way to reach Aurora
   with `psql`; note the LZA bastion setup differs slightly from the old landing zone's).
2. `pg_dump` the old environment's database; restore into the new Aurora cluster. All tables
   and constraints came across this way (a missing schema in the new cluster was resolved by
   exactly this backup-and-restore).
3. **Watch the database names: there is an `fmdb` and an `fmdb_db`.** Make sure the
   application's `JDBC_SETTING` secret and your restore target point at the right one.
4. Aurora may create its own credentials when the instance is spun up (the Terraform module
   generates master/api secrets) — reconcile these with the app's `fmdb_user`/`jdbc_setting`
   secrets rather than assuming they match.

### Secrets for a new environment

Terraform creates the secret *shells* seeded with `changeme`; the real values (Keycloak client
secret, provider URI, JDBC string, DB user) must be set manually in Secrets Manager. During the
migration they were copied one by one from the old account's Secrets Manager. Restart the ECS
service afterwards — secrets are injected at task start.

### Setting up GitHub deployment for a new account

Follow Confluence: **Registries → "GitHub Actions Setup and Deployment" (HLTH AMS space)**.
In short: create the private ECR repository, create the OIDC deploy role
(`fmdb_OIDC_GitHub_Role`-style) so GitHub can run `terragrunt apply` and push images, then put
the repo URI, role ARN, and license plate into the GitHub environment secrets listed in §7.

### Terragrunt tips

- `terragrunt plan` in `Terraform/<env>` tells you whether a change will work before anything
  is touched — use it constantly.
- If apply fails because something "already exists", it was probably created in the console:
  import it into state or remove the duplicate. Expect friction between Terraform state locks
  and resources created outside Terraform.
- The generated files (`backend.tf`, `provider.tf`, `*.auto.tfvars`) are overwritten on every
  run — never edit them; change the `terragrunt.hcl` that generates them.

## 10. Known issues and cleanup candidates

- `Terraform/sandbox/terragrunt.hcl` writes `dev.auto.tfvars` and omits required inputs
  (`domain`, `alb_origin_id`) — sandbox cannot plan as-is.
- `var.alb_origin_id` (the old nimbus hostname) is still set in dev/test/prod tfvars but no
  longer referenced by any `.tf` — dead input.
- The ALB target group is hardcoded `fmdb-test-target-group` in **every** environment
  (with `ignore_changes = [name]`) — confusing but harmless.
- `cloudwatch_alarms.tf` defaults `ecs_service_name` to `fmdb-dev-service`, so ECS alarms in
  test/prod may watch the wrong dimension — verify in the console.
- `fam_console_idp_name` is `DEV-IDIR` in all environments, including prod.
- Test/prod workflows lag dev on Terraform/Terragrunt versions and lack the
  `init -upgrade` step; align them with dev when convenient.
- `tools-workflow.yaml` and `docker-image.yml` are dead (old paths/secrets); delete when safe.
- README prerequisites still say JDK 21/11-era details; the compiler target in `fmdb/pom.xml`
  is still 11.
- `fmdb/ojdbc11.jar` is unused (Oracle-era leftover).
- Aurora auto-minor-upgrade is enabled by default and will drift the pinned engine version
  again (see §6).

## 11. Where to look for help

- `README.md` — detailed local build/run instructions.
- Confluence (HLTH AMS space) — **"GitHub Actions Setup and Deployment"** under Registries, for
  ECR/OIDC-role setup; plus the general LZA platform docs (bastion access, perimeter/`public`
  tag behaviour).
- `git log main...LZA-Migration_Payara7.2026.4` — the migration's full commit history; commit
  messages are descriptive and the diffs are the best record of *how* each problem was solved.
- The AWS console — the Terraform maps 1:1 to it; when a plan surprises you, compare with what
  actually exists.
