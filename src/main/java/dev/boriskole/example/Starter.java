package dev.boriskole.example;

import dev.boriskole.example.service.TomcatServer;

public class Starter {

    public static void main(String... args) {
        TomcatServer.getInstance().start();
    }

}