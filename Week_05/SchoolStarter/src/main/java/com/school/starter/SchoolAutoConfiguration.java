package com.school.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass({Student.class})
@AutoConfigureBefore(SchoolAutoConfiguration.class)
@EnableConfigurationProperties({SchoolProperties.class})
@ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
public class SchoolAutoConfiguration {

    @Autowired
    private SchoolProperties schoolProperties;

    public SchoolAutoConfiguration() {
    }

    @Bean
    public Student student() {
        return new Student(1, schoolProperties.getConfig());
    }
}