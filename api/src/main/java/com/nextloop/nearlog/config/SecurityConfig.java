package com.nextloop.nearlog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService userService;
    private final HttpCookieOAuth2AuthorizationRequestRepository authorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/h2-console/**","/api/v1").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().disable()
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .oauth2Login()
                .authorizationEndpoint()
                    .authorizationRequestRepository(authorizationRequestRepository)
                    .and()
                .userInfoEndpoint()
                    .userService(userService)
                    .and()
                .successHandler(oAuth2AuthenticationSuccessHandler);

    }
}
