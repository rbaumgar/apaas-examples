#!/bin/sh
. config.properties

scale_command="\
  curl -X PUT \
    -u ${USER_ID}:${USER_PASSWORD} \
    -H \"X-ID-TENANT-NAME:${ID_DOMAIN}\" \
    -H \"Content-Type: application/json\" \
    -d '{\"instances\" : \"2\"}' \
    https://${APAAS_HOST}/paas/service/apaas/api/v1.1/apps/${ID_DOMAIN}/${APP_NAME}/instances"

echo ${scale_command}
eval ${scale_command}
