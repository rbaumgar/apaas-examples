#!/bin/sh

if [ "$1" ]; then
   JAVA_HOME=${1};
fi

if [ -n "$JAVA_HOME" ]; then
  echo "JAVA_HOME not defined, pass as argument to ${0}."
  exit -1
fi

# Detect if JAVA_HOME is JDK or JRE and set correct security dir location
if [ -d "$JAVA_HOME/jre" ]; then
  echo "JDK detected in ${JAVA_HOME}"
  security_dir=${JAVA_HOME}/jre/lib/security
  ls -la ${JAVA_HOME}/jre/lib/ext
else
  echo "JRE detected in ${JAVA_HOME}"
  security_dir=${JAVA_HOME}/lib/security
fi

# Replace existing jars with Unlimited Strength Juristiction Policy jars
echo "Updating local_policy.jar in ${security_dir}"
cp -f ./policy_jars/local_policy.jar ${security_dir}
echo "Updating US_export_policy.jar in ${security_dir}"
cp -f ./policy_jars/US_export_policy.jar ${security_dir}
