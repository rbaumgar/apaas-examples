#!/bin/sh

# Install/replace local jars in local Maven Repo 
mvn install:install-file -DgroupId=com.oracle.jdbc -DartifactId=ucp -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=lib/ucp.jar
mvn install:install-file -DgroupId=com.oracle.jdbc -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=lib/ojdbc7.jar

mvn clean package
