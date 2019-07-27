package com.hlex.lab.camundasrpringhelloworld.config;

import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 15)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .antMatcher("/app/**")
                .antMatcher("/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();// this is just an example, use any auth mechanism you like

    }

    @Bean
    public FilterRegistrationBean<ContainerBasedAuthenticationFilter> containerBasedAuthenticationFilter(){
        logger.info("register FilterRegistrationBean<ContainerBasedAuthenticationFilter> ");
        FilterRegistrationBean<ContainerBasedAuthenticationFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
        filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", "com.hlex.lab.camundasrpringhelloworld.filter.SpringSecurityAuthenticationCopyGroup"));
        // filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", "com.hlex.lab.camundasrpringhelloworld.filter.SpringSecurityAuthenticationProvider"));
        filterRegistration.setOrder(101); // make sure the filter is registered after the Spring Security Filter Chain
        // filterRegistration.addUrlPatterns("/app/*");
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
}
