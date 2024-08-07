resource "aws_secretsmanager_secret" "jdbc_setting" {
  name = "jdbc_setting"
}

resource "aws_secretsmanager_secret" "fmdb_proxy_user" {
  name = "fmdb_user"
}


resource "aws_secretsmanager_secret" "fmdb_keycloak-client-secret" {
  name = "fmdb_keycloak-client-secret"
}

resource "aws_secretsmanager_secret" "redirect_uri"{ 
  name = "redirect_uri"
}

resource "aws_secretsmanager_secret_version" "jdbc_setting" {
  secret_id     = aws_secretsmanager_secret.jdbc_setting.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret" "fmdb_provider_uri"{ 
  name = "${var.application}_provider_uri"
}

resource "aws_secretsmanager_secret" "fmdb_siteminder_uri"{ 
  name = "${var.application}_siteminder_uri"
}

resource "aws_secretsmanager_secret_version" "rds_credentials" {
  secret_id     = aws_secretsmanager_secret.fmdb_proxy_user.id
  secret_string = <<EOF
{
  "username": "fmdb_proxy_user",
  "password": "changeme",
  "host": "${module.aurora_postgresql_v2.cluster_endpoint}",
  "port": ${module.aurora_postgresql_v2.cluster_port},
  "dbClusterIdentifier": "${module.aurora_postgresql_v2.cluster_id}"
}
EOF
lifecycle {
  ignore_changes = [ secret_string  ]
  }
}

resource "aws_secretsmanager_secret_version" "fmdb_keycloak-client-secret" {
  secret_id     = aws_secretsmanager_secret.fmdb_keycloak-client-secret.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "redirect_uri" {
  secret_id     = aws_secretsmanager_secret.redirect_uri.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "fmdb_provider_uri" {
  secret_id     = aws_secretsmanager_secret.fmdb_provider_uri.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "fmdb_siteminder_uri" {
  secret_id     = aws_secretsmanager_secret.fmdb_siteminder_uri.id
  secret_string = "changeme"
}
