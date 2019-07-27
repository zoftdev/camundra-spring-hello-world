package com.hlex.lab.camundasrpringhelloworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    // This is just a very simple Identity Management solution for demo purposes.
    // In real world scenarios, this would be replaced by the actual IAM solution
    @SuppressWarnings("deprecation")
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("employee").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("dosadmin1").password("password").roles("employee").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("john").password("john").roles("camunda-user","admin-group").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("password").roles("camunda-admin").build());
        
        return manager;


    }

}