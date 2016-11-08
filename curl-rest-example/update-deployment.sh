#!/bin/sh
. ./config.properties

# UPDATE DEPLOYMENT.JSON
update_deployment="\
  curl -i -X PUT  \
    -u ${USER_ID}:${USER_PASSWORD} \
    -H \"X-ID-TENANT-NAME:${ID_DOMAIN}\" \
    -H \"Content-Type: multipart/form-data\" \
    -F \"deployment=@deployment-2.json\" \
    https://${APAAS_HOST}/paas/service/apaas/api/v1.1/apps/${ID_DOMAIN}/${APP_NAME}"
echo ${update_deployment}
eval ${update_deployment}
