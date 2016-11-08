#!/bin/sh
. config.properties

# PUT ARCHIVE IN STORAGE CONTAINER
put_in_container="\
  curl -i -X PUT \
    -u ${USER_ID}:${USER_PASSWORD} \
    https://${ID_DOMAIN}.storage.oraclecloud.com/v1/Storage-$ID_DOMAIN/$APP_NAME/$ARCHIVE_FILE \
        -T $ARCHIVE_LOCAL"
echo ${put_in_container}
eval ${put_in_container}

# UPDATE ARCHIVE FROM STORAGE
update_application="\
  curl -i -X PUT  \
    -u ${USER_ID}:${USER_PASSWORD} \
    -H \"X-ID-TENANT-NAME:${ID_DOMAIN}\" \
    -H \"Content-Type: multipart/form-data\" \
    -F archiveURL=${APP_NAME}/${ARCHIVE_FILE} \
    https://${APAAS_HOST}/paas/service/apaas/api/v1.1/apps/${ID_DOMAIN}/${APP_NAME}"
echo ${update_application}
eval ${update_application}
