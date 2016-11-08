package com.oracle.cloud.javase.welcome;
import java.io.File;
import org.apache.catalina.startup.Tomcat;

public class Main {

    public static void main(String[] args) throws Exception {

        String webappDir = "target/webapp/";
        String port = System.getenv("PORT");
        if (null == port || port.isEmpty()) {
            port = "8080";
        }

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port));
        tomcat.addWebapp("/", new File(webappDir).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
