#!/bin/sh

if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]; then
  echo "usage: ${0} <db user> <db password> <trust and key store password>";
  exit -1;
fi

export EECS_USER=${1}
export EECS_PASSWORD=${2}
export STORE_PASSWORD=${3}

echo "starting Example..."

# Start the application referencing the security config files
java \
  -Doracle.net.tns_admin=./security \
  -Djavax.net.ssl.trustStore=./security/truststore.jks \
  -Djavax.net.ssl.trustStorePassword=${3} \
  -Djavax.net.ssl.keyStore=./security/keystore.jks \
  -Djavax.net.ssl.keyStorePassword=${3} \
  -Doracle.net.ssl_server_dn_match=true \
  -Doracle.net.ssl_version=1.2 \
  -jar target/exadata-express-test*.jar
echo "exited Example"
