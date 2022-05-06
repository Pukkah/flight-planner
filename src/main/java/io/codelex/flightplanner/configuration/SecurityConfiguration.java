package io.codelex.flightplanner.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/testing-api/**").permitAll()
            .antMatchers("/api/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
        // Enabling iframe for H2 Console
        http.headers().frameOptions().sameOrigin();
    }

}
