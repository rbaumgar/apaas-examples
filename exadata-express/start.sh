#!/bin/sh

# Replace the default policy jars with the unlimited strength jars
sh ./replace_policy_jars.sh ${JAVA_HOME}

echo "starting Example..."

echo "JKS_PASSWORD=${JKS_PASSWORD}"

# Start the application referencing the security config files
java -Doracle.net.tns_admin=./security \
  -Djavax.net.ssl.trustStore=./security/truststore.jks \
  -Djavax.net.ssl.trustStorePassword=${JKS_PASSWORD} \
  -Djavax.net.ssl.keyStore=./security/keystore.jks \
  -Djavax.net.ssl.keyStorePassword=${JKS_PASSWORD} \
  -Doracle.net.ssl_server_dn_match=true \
  -Doracle.net.ssl_version=1.2 \
  -jar exadata-express-test*.jar
echo "exited Example"
