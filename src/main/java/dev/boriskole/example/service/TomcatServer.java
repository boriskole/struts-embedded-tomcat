package dev.boriskole.example.service;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TomcatServer {
    
    private static final Logger logger = LogManager.getLogger(TomcatServer.class);
    private static final int TOMCAT_PORT = 8080;
    private static final String TEMPORARY_DIRECTORY = System.getProperty("java.io.tmpdir");
    private static TomcatServer instance;

    private TomcatServer() { }

    public static TomcatServer getInstance() {
        if (instance == null) {
            instance = new TomcatServer();
        }

        return instance;
    }

    public void start() {
        try {
            // Redirect Tomcat's default logging provider to Log4j.
            System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");

            logger.info("Initializing Tomcat server...");
            Tomcat tomcat = new Tomcat();

            configureServer(tomcat);

            tomcat.start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown(tomcat)));
            logger.info("Successfully started server on port {}.", TOMCAT_PORT);

            tomcat.getServer().await();
        } catch (Exception exception) {
            logger.error("Something went wrong while starting server", exception);
        }
    }

    private void configureServer(Tomcat tomcat) {
        tomcat.setPort(TOMCAT_PORT);
        Path tomcatBasePath = Paths.get(TEMPORARY_DIRECTORY, "tomcat-base");
        File tomcatBaseDir = tomcatBasePath.toFile();

        if (!tomcatBaseDir.exists() && !tomcatBaseDir.mkdirs()) {
            logger.error("Failed to create the base directory for Tomcat at: {}", tomcatBasePath);
            throw new IllegalStateException("Cannot create Tomcat base directory.");
        }

        tomcat.setBaseDir(tomcatBaseDir.getAbsolutePath());
        tomcat.getConnector();

        File webAppDirectory = new File("src/main/webapp");
        tomcat.addWebapp("", webAppDirectory.getAbsolutePath());
    }

    private void shutdown(Tomcat tomcat) {
        try {
            if (tomcat != null) {
                tomcat.stop();
                tomcat.destroy();
                logger.info("Gracefully stopped Tomcat server.");
            }
        } catch (LifecycleException e) {
            logger.error("Something went wrong while stopping server", e);
        }
    }

}
