package com.anjastanojevic.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {

    @Override
    public void run(DemoConfiguration configuration, Environment environment) {

        final BookResource bookResource = new BookResource();
        environment.jersey().register(bookResource);


    }

}