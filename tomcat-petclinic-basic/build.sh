#!/bin/sh

if [ -z "$1" ]; then
  TOMCAT_VERSION=8.5.6
  echo "INFO: Tomcat 8 release not specified, defaulting to ${TOMCAT_VERSION}"
else
  TOMCAT_VERSION=${1}
  echo "INFO: Tomcat 8 release specified ${TOMCAT_VERSION}"
fi

TOMCAT_DIST=apache-tomcat-${TOMCAT_VERSION}

# Clean up any artifacts left from previous builds (except downloaded Tomcat)
rm -rf tomcat-petclinic-dist.zip
rm -rf apache-tomcat

if [ -n "$HTTP_PROXY" ]; then
  PROXY_ARG="--proxy ${HTTP_PROXY}"
fi

# Download Tomcat distribution if necessary
if [ ! -r ${TOMCAT_DIST}.tar.gz ]; then
  # Find the closest mirror
  MIRROR=`curl ${PROXY_ARG} 'https://www.apache.org/dyn/closer.cgi' |
      grep -o '<strong>[^<]*</strong>' |
      sed 's/<[^>]*>//g' |
      head -1`
  let httpCode=`curl -X GET \
     ${PROXY_ARG} \
     -o ${TOMCAT_DIST}.tar.gz \
     -sL -w "%{http_code}" \
     ${MIRROR}/tomcat/tomcat-8/v${TOMCAT_VERSION}/bin/${TOMCAT_DIST}.tar.gz`
  if [ $httpCode != 200 ]; then
    rm -rf ${TOMCAT_DIST}.tar.gz
    echo "Tomcat 8 release ${TOMCAT_VERSION} not found on mirror--specify latest version."
    echo "USAGE: ${0} [TOMCAT 8 RELEASE NUMBER]"
    exit -1
  fi
fi

# Unzip Tomcat distribution
tar -xf ${TOMCAT_DIST}.tar.gz
mv ${TOMCAT_DIST} apache-tomcat

# build Petclinic

# Download Spring Petclinic if necessary
if [ ! -r spring-petclinic ]; then
  git clone https://github.com/spring-projects/spring-petclinic.git
fi

# Build Petclinic
cd spring-petclinic
mvn clean package
cd ..

# Remove default webapps
rm -rf apache-tomcat/webapps/*

# Move petclinic.war to Tomcat webapps folder as ROOT webapp
mv spring-petclinic/target/petclinic.war apache-tomcat/webapps/ROOT.war

# Create application archive with Tomcat (with petclinic war) and manifest.json
zip -r tomcat-petclinic-dist.zip manifest.json apache-tomcat
