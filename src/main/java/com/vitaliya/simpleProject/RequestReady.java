package com.vitaliya.simpleProject;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "request")
public class RequestReady {
    private String request;
    private String name;

    public String getName() {
        return name;
    }

    public String getRequest() {
        return "Request: " + request + " for " + name;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public void setRequest(String request1) {
        this.request = request1;
    }
}
