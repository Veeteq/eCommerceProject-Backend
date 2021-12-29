package com.wojnarowicz.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SpringEcommerceSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //protect endpoint /api/orders/**
    http.authorizeHttpRequests()
    .antMatchers("/api/orders/**")
    .authenticated()
    .and()
    .oauth2ResourceServer()
    .jwt();
    
    http.cors();
    
    //customized response on 401 error
    Okta.configureResourceServer401ResponseBody(http);
    
    //disable CSRF since we are not using cookies for session tracking
    http.csrf().disable();
  }

  
}
