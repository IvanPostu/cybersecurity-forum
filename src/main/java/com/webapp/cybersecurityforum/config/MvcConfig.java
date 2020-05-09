package com.webapp.cybersecurityforum.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**")
      .addResourceLocations("classpath:/static/js/");
    registry.addResourceHandler("/css/**")
      .addResourceLocations("classpath:/static/css/");  
    registry.addResourceHandler("/images/**")
      .addResourceLocations("classpath:/static/images/"); 
  }

}