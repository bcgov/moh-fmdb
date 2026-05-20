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

To build the docker image, you can simply run the following command:

```shell
docker build . --file fmdb/Dockerfile -t fmdb:latest
```

For more information regarding cusotm Docker Payara images see the [docs](https://docs.payara.fish/community/docs/7.2025.1.Beta1/Technical%20Documentation/Payara%20Server%20Documentation/Payara%20Server%20Docker%20Image.html).

## Running

The project can either be run as a collection of docker containers or as seperate services. Docker is recommended as it makes reproducing the runtime environment easier but choose whichever options suites your use case best.

### Bare-metal

*Under maintenance*

1. First you will need to setup a PostgreSQL database. There are two simple options:
    - Setup PostgreSQL as a service on your machine: [Windows](https://www.postgresql.org/download/windows/) or [macOS](https://www.postgresql.org/download/macosx/)
    - Setup PostgreSQL as a docker container: [Docker PostgreSQL specific guide](https://docs.docker.com/guides/postgresql/)
2. 

### Docker

*Under maintenance*

Using docker compose all of the necessary services can be spun up automatically.

1. 