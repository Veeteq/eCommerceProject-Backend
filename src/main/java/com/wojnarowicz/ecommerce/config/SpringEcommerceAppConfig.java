package com.wojnarowicz.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringEcommerceAppConfig implements WebMvcConfigurer {

  @Value(value = "${allowed.origins}")
  private String[] allowedOrigins;
  
  @Value(value = "${spring.data.rest.base-path}")
  private String basePath;

  @Override
  public void addCorsMappings(CorsRegistry cors) {
    cors.addMapping(basePath + "/**").allowedOrigins(allowedOrigins);
  }
  
}
