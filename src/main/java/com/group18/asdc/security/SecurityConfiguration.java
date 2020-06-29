package com.group18.asdc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/public/**", "/forgot-password", "/registration", "/home", "/resetPassword", "/login")
                .permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
                .failureUrl("/login-error").defaultSuccessUrl("/login-success").permitAll().and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();

    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new CustomAuthenticationManager();
    }

}