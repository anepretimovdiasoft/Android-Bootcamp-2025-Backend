package com.example.bootcamp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/volunteer/register").permitAll()
                .antMatchers("/api/volunteer/username/{username}").permitAll()
                .antMatchers("/api/volunteer/**").hasAnyAuthority("ROLE_VOLUNTEER", "ROLE_ADMIN")
                .antMatchers("/api/volunteercenter").permitAll()
                .antMatchers("/api/volunteercenter/{id}").permitAll()
                .antMatchers("/api/volunteercenter/create").hasAuthority("ROLE_ADMIN") /// *** работает некорректно -> не работает настройка доступа
                .antMatchers("/api/volunteercenter/update/{id}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/volunteercenter/delete/{id}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/volunteercenter/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/authority/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
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
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
