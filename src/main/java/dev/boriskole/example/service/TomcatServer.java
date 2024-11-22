package dev.boriskole.example.service;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TomcatServer {

    private static final Logger logger = LoggerFactory.getLogger(TomcatServer.class);
    private static final int SERVER_PORT = 8080;

    private static TomcatServer instance;

    private TomcatServer() { }

    public void start() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        logger.info("Initializing webserver...");
        Tomcat tomcat = new Tomcat();

        tomcat.getHost().setAppBase(".");
        tomcat.setPort(SERVER_PORT);
        tomcat.getConnector();

        Context context = tomcat.addWebapp("", ".");
        WebResourceRoot resourceRoot = new StandardRoot(context);
        resourceRoot.addPreResources(getResourceSet(resourceRoot));
        context.setResources(resourceRoot);

        tomcat.start();
        stopWatch.stop();
        logger.info("Webserver started in {} seconds.", stopWatch.getTime(TimeUnit.MILLISECONDS) / 1000.0);
        tomcat.getServer().await();
        tomcat.stop();
    }

    private WebResourceSet getResourceSet(WebResourceRoot resourceRoot) {
        String sourceFile = getSourceFile();

        if (sourceFile.endsWith(".jar")) {
            logger.debug("Jar file use detected. Using JAR resource set.");
            return new JarResourceSet(resourceRoot, "/", sourceFile, "/app");
        } else {
            logger.debug("Local development detected. Using directory resource set.");
            return new DirResourceSet(resourceRoot, "/", getClass().getResource("/app").getFile(), "/");
        }
    }

    private String getSourceFile() {
        try {
            return new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        } catch (Exception e) {
            logger.error("Error getting source file", e);
            return null;
        }
    }

    public static TomcatServer getInstance() {
        if (instance == null) {
            instance = new TomcatServer();
        }

        return instance;
    }

}
