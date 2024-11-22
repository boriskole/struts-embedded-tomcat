package dev.boriskole.example;

import dev.boriskole.example.service.TomcatServer;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Starter {

    public static void main(String... args) throws Exception {
        // Redirect Tomcat logging to SLF4J
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        TomcatServer.getInstance().start();
    }

}