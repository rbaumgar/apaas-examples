Running (Full) Tomcat
=====================

There are two basic issues running full Tomcat on ACC:
* how to start
* HTTP port

How to Start
------------

Tomcat needs to be started as a foreground process which means you cannot
use `startup.sh` because that will background Tomcat and the container will exit.  Instead you must use `sh catalina.sh run` to launch Tomcat in the foreground.  A manifest may look like:

    {
      "runtime": {
          "majorVersion": "8"
      },
      "command": "sh bin/catalina.sh run"
    }

HTTP Port
---------

Tomcat does not support passing the HTTP port to listen on as a start parameter.  It must be configured in `settings.xml`.  Ideally we will
develop a helper script to update settings.xml prior to launch but for
now the default value of 8080 should work as this is the value of $PORT
(at least as of the type of writing).
