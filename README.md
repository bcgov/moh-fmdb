# FMDB

The Formulary Management DataBase application is a database of drugs which have been reviewed for inclusion as benefits under the PharmaCare program. It consists of two applications:

1. A secure application to add drugs and to search or run reports based on the uploaded data.
2. A public webpage where the general public can see which drugs have been reviewed.

FMDB provides information on:

* All drugs reviewed by the national Common Drug Review (CDR) and subsequently submitted to the Ministry for consideration.
* All drug reviews completed by the Ministry on or after January 1, 2005.
* Basic information on the brand name drugs currently under review by the Ministry and the CDR, including:
* The drug's brand name, chemical name and manufacturer.
* The drug's indication - which condition it will be used to treat.
* The date when the drug was submitted to the Ministry.
* Whether the drug was reviewed by the Common Drug Review (CDR).
* The date when the Common Drug Review (CDR) recommendation was made.
* The drug's PharmaCare program status (e.g. under review, non-benefit, etc.).
* The date on which the Ministry's decision was made Formulary Management Database.
 
FMDB's Confluence page can be found here: [FMDB Confluence](https://hlth.atlassian.net/wiki/x/ZJy7Hg)

# Local Development

## Introduction

This section describes how to setup your local development environment. It provides details including but not limited to the preferred IDE, extensions, applications, and other development related items.

## Prerequisites

|Name|Type|Version|Description|URL|
|-----|-----|-----|-----|-----|
|VSCode|IDE|Latest|Preferred IDE of the development team.|[Site](https://code.visualstudio.com/)|
|Java Development Kit (JDK)|Java Toolkit|21|Contains the java compiler, debugger, packager, and runtime.|[Site](https://openjdk.org/projects/jdk/11/)|
|Docker|Containerization Platform|Latest|Contains the Docker CLI and other Docker utlities|Installing [Rancher Desktop](https://rancherdesktop.io/) also installs Docker. This is the simplest as Docker requires a commercial license.|
|Java|VSCode Extension|Latest|Development support for Java in VSCode.|[VSCode Marketplace](https://marketplace.visualstudio.com/items?itemName=Oracle.oracle-java)|
|Debugger for Java|VSCode Extension|Latest|Debugger for Java applications.|[VSCode Marketplace](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-debug)|
|Extension Pack for Java|VSCode Extension|Latest|Collection of popular Java extensions.|[VSCode Marketplace](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)|
|Maven for Java|VSCode Extension|Latest|Provides shortcuts and a project explorer for maven projects.|[VSCode Marketplace](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)|
|Project Manager for Java|VSCode Extension|Latest|Provides additional project explorer features for Java.|[VSCode Marketplace](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-dependency)|
|Test Runner for Java|VSCode Extension|Latest|Run and debug Java test cases|[VSCode Marketplace](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test)|

## Building

There are two methods of building the project locally:

1. Building the project on "bare-metal" which prepares the application to be run on your host machine: [Bare-metal](#bare-metal)
2. Creating a docker image which can then be deployed using docker or kubernetes: [Docker](#docker)


### Bare-metal

The following steps are for building the appliction to be run on your host OS:

1. First, ensure you are in the root project directory.

2. Then install the `errorhandler-1.0.jar`:
```shell
mvn install:install-file \
-Dfile=fmdb/errorhandler-1.0.jar \
-DgroupId=com.cgi.fmdb \
-DartifactId=errorhandler \
-Dversion=1.0 \
-Dpackaging=jar \
-DgeneratePom=true
```

3. Install the `aristo-1.0.1.jar` library:
```shell
mvn install:install-file \
-Dfile=fmdb/lib/aristo-1.0.1.jar \
-DgroupId=org.primefaces.themes \
-DartifactId=aristo \
-Dversion=1.0.1 \
-Dpackaging=jar \
-DgeneratePom=true
```

4. Finally, build the jar package:
```shell
mvn -f fmdb/pom.xml clean package
```

### Docker

A docker-compose file has been provided to simplify running the FMDB application in a docker container. If you are trying to build the docker image so that you can test the application locally, feel free to skip to [Running > Docker](#docker-1) below. Continue reading if your goal is to build the image itself.

To build the docker image, run the following command:

```shell
docker build --build-arg CA_ROOT=<your enterprise certificate> . --file fmdb/Dockerfile -t fmdb:latest
```

The variable `<your enterprise certificate>` only needs to be set if your organization uses TLS/SSL certificate replacement to inspect https traffic. For more information regarding custom Docker Payara images see the [docs](https://docs.payara.fish/community/docs/7.2025.1.Beta1/Technical%20Documentation/Payara%20Server%20Documentation/Payara%20Server%20Docker%20Image.html).

## Running

The project can either be run as a collection of docker containers or as seperate services. Docker is recommended as it makes reproducing the runtime environment easier but choose whichever options suits your use case best.

Keep in mind this section assumes you have already completed the steps from the cooresponding section under [Building](#building) above.

### Bare-metal

#### Prerequisites

1. First you will need to setup a PostgreSQL database. There are two simple options:
    - Setup PostgreSQL as a service on your machine: [Windows](https://www.postgresql.org/download/windows/) or [macOS](https://www.postgresql.org/download/macosx/)
    - Setup PostgreSQL as a docker container: [Docker PostgreSQL specific guide](https://docs.docker.com/guides/postgresql/)

2. You will then need to populate the database with the necessary data for FMDB. You can do so by running the script `"Database Scripts/setup_db.sh`. Note that currently none of the tables are created. It just creates the FMDB database and `fmdb_owner` user.

3. **(Optional)** If your organization implements SSL/TLS Interception to listen to https traffic then you will need to add your organization's root certificate to the [JVM Truststore](https://docs.oracle.com/cd/E19509-01/820-3503/ggffo/index.html).

    Perform the following steps:

    1. Extract your organization's root certificate using [`macos-setup.sh`](./macos-setup.sh). You will need to find the name of the root certificate and provide it as an argument:
        ```shell
        ./macos-setup.sh "Root CA"
        ```
        The script will extract the certificate as a `.pem` bundle.

    2. Run the following command (${name} indicates a variable)
        ```shell
        keytool -import -trustcacerts \
        -alias ${name-of-pem-bundle} \
        -file ${path-to-pem-file} \
        -keystore $JAVA_HOME/lib/security/cacerts \
        -storepass changeit
        ```

#### Running the application

1. Start by [downloading Payara server](https://payara.fish/products/payara-platform-community/). You should match the version number to the Docker image file.

2. Extract it to any directory you like. Then set the `PAYARA_HOME` variable to the payara directory.

    Let's say you put the Payara directory in your home folder. Then set `PAYARA_HOME` as follows:
    ```shell
    PAYARA_HOME=~/payara7
    ```
3. Copy the `postgresql` driver into the default domain:
    ```shell
    cp fmdb/postgresql-*.jar $PAYARA_HOME/glassfish/domains/domain1/lib
    ```
4. Export the variables defined in the `.env` file.
    ```shell
    set -a
    source ./.env
    set +a
    ```

5. Run the pre-boot commands for the payara server:
    ```shell
    $PAYARA_HOME/bin/asadmin start-domain --prebootcommandfile fmdb/pre-boot-commands.asadmin domain1
    ```

6. Deploy the application:
    
    ```shell
    $PAYARA_HOME/bin/asadmin deploy --force=true fmdb/FMDB-ear/target/FMDB-ear.ear
    ```

    The application should now be deployed. You can verify by running by visiting `http://localhost:8080/FMDB`.

To stop the Payara server you can run:
```shell
$PAYARA_HOME/bin/asadmin stop-domain domain1
```

### Docker

Using docker compose all of the necessary services can be spun up automatically. The tables requried 

1. First spin up the database.

    ```shell
    docker compose up db
    ```

2. Then run `Database Scripts/setup_db.sh`.

    ```shell
    # With Database Scripts as the pwd.
    ./setup_db.sh
    ```

3. Finally start all of the docker compose services:

    ```shell
    docker compose up
    ```

    If your organization uses TLS/SSL inspection you will likely need to set the `CA_ROOT` variable in the `.env` file.

The FMDB application should now be running in your environment at `http://localhost:8080/FMDB`.