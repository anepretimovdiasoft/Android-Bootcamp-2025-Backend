package com.example.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/person/register").permitAll()  // Allow registration
                .antMatchers("/api/person/login").permitAll()     // Allow login
                .antMatchers("/api/person/username/**").permitAll()     // Allow check if user exists
                .antMatchers("/api/person/me").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER") // Auth only
                .antMatchers("/api/person/{id}").hasAuthority("ROLE_ADMIN") // Admin only
                .antMatchers(HttpMethod.POST, "/api/department/**").hasAuthority("ROLE_USER") // Admin only: create department
                .antMatchers(HttpMethod.PUT, "/api/department/{id}").hasAuthority("ROLE_ADMIN") // Admin only: update department
                .antMatchers(HttpMethod.DELETE, "/api/department/{id}").hasAuthority("ROLE_ADMIN") // Admin only: delete department
                .antMatchers("/api/department/{name}/persons/paginated").hasAuthority("ROLE_ADMIN") // Admin only: show persons attached to department
                .antMatchers("/api/authority/**").hasAuthority("ROLE_ADMIN") // Admin only: change user rights (authorities)
                .anyRequest().authenticated() // Any other request requires authentication
                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
