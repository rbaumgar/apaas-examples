Edit config.properties
======================
Edit to user your config.properties file to point to your account:

    export ID_DOMAIN=jcsdemo215
    export USER_ID=demouser
    export USER_PASSWORD=ooW2015!215

Once you have done this you are ready to interact via REST with the Application
Container Cloud.

Create and Deploy an Application
================================

deploy.sh
---------

Run `./deploy.sh` to create an application with enviroment variables defined in
`deployment-1.json`.

If you open the service console you'll see the application being created and
provisioned.    

update-archive.sh
-----------------

Running `./update-archive.sh` will redeploy the application archive.  So if you
edit your application you can run this script to push this new revision to the
cloud.

update-deployment.sh
--------------------

Run `./update-deployment.json` to push changes to the environment without
updating the application binary.  This script will deploy `deployment-2.json`
which will change the GREETING environment variable.  Updating the environment
will result in a restart of all application instances.  Once restarted the new
GREETING will be displayed by the application.

scaleup.sh
----------

Increase the RAM allocated to the application to 2GB using `scaleup.sh`.

scaleout.sh
-----------

Add an instance of the application using `scaleout.sh`.
