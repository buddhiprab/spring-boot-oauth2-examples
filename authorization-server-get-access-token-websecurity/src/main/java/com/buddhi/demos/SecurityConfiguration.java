package com.buddhi.demos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/account").permitAll().and()
                // default protection for all resources (including /oauth/authorize)
                .authorizeRequests()
                .anyRequest().hasRole("USER");
        // ... more configuration, e.g. for form login
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // outputs {bcrypt}$2a$10$oz72OLc5n/v2ej6rovd.WOt01S0v3Q2a.CGHRBJb6bzVSfS00j.Bi
        // remember the password that is printed out and use in the next step
//        System.out.println(encoder.encode("a"));
        return new InMemoryUserDetailsManager(
                User.withUsername("b")
                        .password("{bcrypt}$2a$10$oz72OLc5n/v2ej6rovd.WOt01S0v3Q2a.CGHRBJb6bzVSfS00j.Bi")
                        .roles("USER")
                        .build());
    }
}