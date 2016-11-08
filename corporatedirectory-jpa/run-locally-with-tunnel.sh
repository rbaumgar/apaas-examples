#!/bin/sh

if [ -z "$1" ]; then
  echo "usage: ${0} <id domain> ";
  exit -1;
fi

ID_DOMAIN=$1
DBAAS_DEFAULT_CONNECT_DESCRIPTOR=localhost:1521/PDB1.${ID_DOMAIN}.oraclecloud.internal
DBAAS_USER_NAME=employee
DBAAS_USER_PASSWORD=employee

java -jar target/corporatedirectory-1.0-SNAPSHOT-jar-with-dependencies.jar
