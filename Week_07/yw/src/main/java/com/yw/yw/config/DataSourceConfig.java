package com.yw.yw.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource oneDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource twoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSource() {
        DynamicRouteDataSource routeDataSource = new DynamicRouteDataSource();
        Map<Object, Object> map = new HashMap<>();
        map.put(DataSourceContext.DbType.ONE, oneDataSource());
        map.put(DataSourceContext.DbType.TWO, twoDataSource());
        routeDataSource.setDefaultTargetDataSource(twoDataSource());
        routeDataSource.setTargetDataSources(map);
        routeDataSource.afterPropertiesSet();

        return routeDataSource;
    }
}

