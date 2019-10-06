package com.anjastanojevic.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Configuration extends io.dropwizard.Configuration {

    @NotEmpty
    private String databaseUrl;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @JsonProperty
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    @JsonProperty
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
