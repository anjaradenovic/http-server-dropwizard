package com.anjastanojevic.server;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] arguments = {"server", "config.yml"};

        Application application = new Application();
        application.run(arguments);
    }
}
