About
=====

This sample:

1.  downloads JBoss Wildfly (first time)
2.  downloads Spring PetClinic
3.  builds PetClinic
4.  installs petclinic.war in JBoss
5.  zips up JBoss w/ PetClinic and manifest.json to build deployable application archive

Running JBoss Wildfly
=====================

There are three concerns when running JBoss Wildfly on ACC:

*  How to start
*  Host HTTP port
*  Host interface

How to Start
------------

Use `sh standalone.sh` to launch Wildfly in the foreground.


HTTP Port
---------

Wildfly's standalone.sh supports passing the HTTP port to listen on by defining the value of `jboss.http.port`.

Host interface
--------------

To bind to the specified PORT on the ACC container Wildfly must use 0.0.0.0

Sample Manifest.json
--------------------

    {
      "runtime": {
          "majorVersion": "8"
      },
      "command": "sh ./wildfly/bin/standalone.sh -b=0.0.0.0 -Djboss.http.port=$PORT"
    }
