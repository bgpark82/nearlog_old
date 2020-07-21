package com.nextloop.nearlog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .disable()
                .csrf()
                    .disable()
                .headers()
                    .frameOptions()
                    .disable()
                    .and()
                .authorizeRequests()
                    .antMatchers("/h2-console/**")
                    .permitAll()
                    .and()
                .oauth2Login();
    }
}
