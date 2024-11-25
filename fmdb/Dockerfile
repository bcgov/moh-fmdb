FROM maven:3.8.6-openjdk-11-slim as build-stage
RUN mkdir -p /home/fmdb
COPY fmdb/aristo-1.0.1.jar /home/fmdb
RUN mvn install:install-file -Dfile=/home/fmdb/aristo-1.0.1.jar -DgroupId=com.cgi.fmdb -DartifactId=errorhandler -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
COPY fmdb/errorhandler-1.0.jar /home/fmdb
RUN mvn install:install-file -Dfile=/home/fmdb/errorhandler-1.0.jar -DgroupId=com.cgi.fmdb -DartifactId=errorhandler -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
COPY fmdb/FMDB-ear /home/fmdb/FMDB-ear
COPY fmdb/FMDB-ejb /home/fmdb/FMDB-ejb
COPY fmdb/FMDB-war /home/fmdb/FMDB-war
COPY fmdb/FMDB-war-pub /home/fmdb/FMDB-war-pub
COPY fmdb/pom.xml /home/fmdb

# Artifactory repo for errorhandler-1.0.jar uses a self-signed certificate which requires SSL overrides for Maven
RUN mvn -f /home/fmdb/pom.xml clean package -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.insecure=true

FROM payara/server-full:5.2022.4-jdk11
COPY fmdb/pre-boot-commands.asadmin $PREBOOT_COMMANDS
COPY --from=build-stage /home/fmdb/FMDB-ear/target/FMDB-ear.ear $DEPLOY_DIR
COPY fmdb/postgresql-42.4.0.jar /opt/payara/appserver/glassfish/domains/domain1/lib
