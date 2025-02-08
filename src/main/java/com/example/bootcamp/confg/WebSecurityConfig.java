package com.example.bootcamp.confg;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Отключение CSRF защиты
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()  // Разрешить доступ ко всем страницам H2-консоли без авторизации
                .antMatchers("/api/users/register").permitAll()  // Разрешить доступ к регистрации без авторизации
                .antMatchers("/api/username/{username}").permitAll()  // Разрешить доступ по имени пользователя без авторизации
                .antMatchers("/api/authority/**").hasAuthority("ROLE_ADMIN")  // Доступ для ролей ADMIN
//                .antMatchers("/api/users/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")  // Доступ для ролей USER и ADMIN
                .antMatchers("/api/users/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .anyRequest().authenticated()  // Для остальных запросов требуется аутентификация
                .and()
                .httpBasic()  // Использование Basic Auth
                .and()
                .headers().frameOptions().disable();  // Отключение защиты от фреймов (для H2-консоли)
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
    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }


}
