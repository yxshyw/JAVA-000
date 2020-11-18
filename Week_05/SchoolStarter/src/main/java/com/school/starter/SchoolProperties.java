package com.school.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "school")
public class SchoolProperties {

    private String config;
    
    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}