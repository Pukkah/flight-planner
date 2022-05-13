package io.codelex.flightplanner.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.naming.ConfigurationException;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {
    private final Environment env;

    @Bean
    public DataSource getDataSource() throws ConfigurationException {
        return switch (env.getProperty("flight-planner.store-type", "")) {
            case "in-memory" -> DataSourceBuilder.create()
                                                 .driverClassName("org.h2.Driver")
                                                 .url("jdbc:h2:mem:testdb")
                                                 .username("sa")
                                                 .password("")
                                                 .build();
            case "database" -> DataSourceBuilder.create()
                                                .driverClassName("org.postgresql.Driver")
                                                .url(env.getProperty("spring.datasource.url"))
                                                .username(env.getProperty("spring.datasource.username"))
                                                .password(env.getProperty("spring.datasource.password"))
                                                .build();
            default -> throw new ConfigurationException("Property flight-planner.store-type is not set correctly");
        };
    }

}
