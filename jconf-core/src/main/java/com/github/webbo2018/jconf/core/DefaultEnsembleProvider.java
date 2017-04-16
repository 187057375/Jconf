package com.github.webbo2018.jconf.core;

import org.apache.curator.ensemble.EnsembleProvider;

import java.io.IOException;

/**
 * Created by shenwenbo on 2017/3/11.
 */
public class DefaultEnsembleProvider implements EnsembleProvider {

    private String defaultConnectionString;

    DefaultEnsembleProvider() {}

    public DefaultEnsembleProvider(String connectionString) {
        if(connectionString == null) {
            throw new NullPointerException("connection string is null");
        }
        this.defaultConnectionString = connectionString;
    }


    @Override
    public void start() throws Exception {

    }

    @Override
    public String getConnectionString() {
        return defaultConnectionString;
    }

    @Override
    public void close() throws IOException {

    }
}