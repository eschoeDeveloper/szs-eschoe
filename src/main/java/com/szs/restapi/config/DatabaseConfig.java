package com.szs.restapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
   basePackages = "com.szs.restapi.domain"
)
@RequiredArgsConstructor
public class DatabaseConfig {

    private final Environment env;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

}
