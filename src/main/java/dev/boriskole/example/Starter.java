package dev.boriskole.example;

import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Starter {

    private static final Logger LOGGER = LogManager.getLogger(Starter.class);
    private static final int TOMCAT_PORT = 8080;

    public static void main(String... args) {
        try {
            LOGGER.info("Initializing Tomcat server...");
            Tomcat tomcat = new Tomcat();

            tomcat.setPort(TOMCAT_PORT); // 8080 is the default port.
            tomcat.getConnector();

            File webAppDirectory = new File("src/main/webapp");
            tomcat.addWebapp("", webAppDirectory.getAbsolutePath());

            tomcat.start();
            LOGGER.info("Successfully started server on port {}.", TOMCAT_PORT);

            tomcat.getServer().await(); // This makes sure the server stays running.
        } catch (Exception exception) {
            LOGGER.error("Something went wrong while starting server", exception);
        }
    }

}
