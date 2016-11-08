About
=====

This example:

1.  downloads Tomcat
2.  downloads Spring PetClinic
3.  builds PetClinic
4.  installs PetClinic in Tomcat at the root "/"
5.  zips up Tomcat w/ PetClinic and manifest.json to build deployable application archive.

Using the Example
=================

Building
--------

Run `./build.sh [TOMCAT 8 RELEASE NUMBER]`.  The build script will default to
the latest Tomcat 8 release at the time of writing but that is likely to be
out of date.  Run `build.sh` with the latest version, e.g. `./build.sh 8.5.4`

Running Locally
---------------

You can run the *command* from the *manifest.json* file to start Tomcat,
`sh ./apache-tomcat/bin/catalina.sh run`.  Open <http://localhost:8080> to
go to the PetClinic main page.

Running on ACCS
---------------

Deploy the *tomcat-petclinic-dist.zip* file to ACCS.  No other configuration
is necessary.
