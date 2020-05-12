package com.webapp.cybersecurityforum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**")
      .addResourceLocations("classpath:/static/js/");
    registry.addResourceHandler("/css/**")
      .addResourceLocations("classpath:/static/css/"); 

      /**
       * For static css and js libraries.
       */
    registry.addResourceHandler("/libs/**")
      .addResourceLocations("classpath:/static/libs/node_modules/");  
      /**
       * For static images.
       */
    registry.addResourceHandler("/images/**")
      .addResourceLocations("classpath:/static/images/"); 
  }

}