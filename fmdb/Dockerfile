FROM maven:3.9-eclipse-temurin-25-noble AS build-stage
RUN mkdir -p /home/fmdb

# Add custom error handling library.
COPY fmdb/errorhandler-1.0.jar /home/fmdb
RUN mvn install:install-file \
-Dfile=/home/fmdb/errorhandler-1.0.jar \
-DgroupId=com.cgi.fmdb \
-DartifactId=errorhandler \
-Dversion=1.0 \
-Dpackaging=jar \
-DgeneratePom=true

# Add custom styling library.
COPY fmdb/lib /home/fmdb/lib
RUN mvn install:install-file \
-Dfile=/home/fmdb/lib/aristo-1.0.1.jar \
-DgroupId=org.primefaces.themes \
-DartifactId=aristo \
-Dversion=1.0.1 \
-Dpackaging=jar \
-DgeneratePom=true

# Copy in build artifacts.
COPY fmdb/FMDB-ear /home/fmdb/FMDB-ear
COPY fmdb/FMDB-ejb /home/fmdb/FMDB-ejb
COPY fmdb/FMDB-war /home/fmdb/FMDB-war
COPY fmdb/FMDB-war-pub /home/fmdb/FMDB-war-pub
COPY fmdb/pom.xml /home/fmdb

# Build the project.
RUN mvn -f /home/fmdb/pom.xml clean package

FROM payara/server-full:7.2026.4-jdk25

# For more information regarding custom Payara docker images see the docs:
# https://docs.payara.fish/community/docs/7.2025.1.Beta1/Technical%20Documentation/Payara%20Server%20Documentation/Payara%20Server%20Docker%20Image.html
COPY fmdb/pre-boot-commands.asadmin $PREBOOT_COMMANDS
COPY --from=build-stage /home/fmdb/FMDB-ear/target/FMDB-ear.ear $DEPLOY_DIR
COPY fmdb/postgresql-42.4.0.jar /opt/payara/appserver/glassfish/domains/domain1/lib