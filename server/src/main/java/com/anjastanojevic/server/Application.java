package com.anjastanojevic.server;

import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration> {

    @Override
    public void run(Configuration configuration, Environment environment) {

        final BookResource bookResource = new BookResource(
                configuration.getDatabaseUrl(),
                configuration.getUsername(),
                configuration.getPassword());

        environment.jersey().register(bookResource);
    }

}