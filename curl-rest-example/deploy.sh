#!/bin/sh
. ./config.properties

# CREATE STORAGE CONTAINER
create_container="\
  curl -i -X PUT \
    -u ${USER_ID}:${USER_PASSWORD} \
    https://${ID_DOMAIN}.storage.oraclecloud.com/v1/Storage-$ID_DOMAIN/$APP_NAME"

echo ${create_container}
eval ${create_container}

# PUT ARCHIVE IN STORAGE CONTAINER
put_in_container="\
  curl -i -X PUT \
    -u ${USER_ID}:${USER_PASSWORD} \
    https://${ID_DOMAIN}.storage.oraclecloud.com/v1/Storage-$ID_DOMAIN/$APP_NAME/$ARCHIVE_FILE \
        -T $ARCHIVE_LOCAL"
echo ${put_in_container}
eval ${put_in_container}

# CREATE APPLICATION WITH DEPLOYMENT.JSON
create_application=" \
  curl -i -X POST  \
    -u ${USER_ID}:${USER_PASSWORD} \
    -H \"X-ID-TENANT-NAME:${ID_DOMAIN}\" \
    -H \"Content-Type: multipart/form-data\" \
    -F \"name=${APP_NAME}\" \
    -F \"runtime=node\" \
    -F \"deployment=@deployment-1.json\" \
    -F \"subscription=Monthly\" \
    -F archiveURL=${APP_NAME}/${ARCHIVE_FILE} \
    https://${APAAS_HOST}/paas/service/apaas/api/v1.1/apps/${ID_DOMAIN}"
echo ${create_application}
eval ${create_application}
