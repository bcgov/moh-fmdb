FROM maven:3.8.6-openjdk-11-slim as build-stage
RUN mkdir -p /home/fmdb
COPY errorhandler-1.0.jar /home/fmdb
RUN mvn install:install-file -Dfile=/home/fmdb/errorhandler-1.0.jar -DgroupId=com.cgi.fmdb -DartifactId=errorhandler -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
COPY FMDB-ear /home/fmdb/FMDB-ear
COPY FMDB-ejb /home/fmdb/FMDB-ejb
COPY FMDB-war /home/fmdb/FMDB-war
COPY FMDB-war-pub /home/fmdb/FMDB-war-pub
COPY pom.xml /home/fmdb

RUN mvn -f /home/fmdb/pom.xml clean package -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.insecure=true

FROM payara/server-full:5.2022.4-jdk11
COPY --from=build-stage /home/fmdb/FMDB-ear/target/FMDB-ear.ear $DEPLOY_DIR
COPY postgresql-42.4.0.jar /opt/payara/appserver/glassfish/domains/domain1/lib
ENV fmdb_keycloak_client_secret=76a79c40-6573-4e21-bca9-15f901f2c722
ENV JDBC_SETTING=jdbc:postgresql://postgresql.cgi.codefest.com:5432/fmdbpg?currentSchema=fmdb
ENV PG_USER=fmdb
ENV PG_PASSWORD=passwordcgi