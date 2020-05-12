package com.webapp.cybersecurityforum.config;

import com.github.cage.GCage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptchaConfig {

  @Bean
  public GCage getGCage(){
    return new GCage();
  }

}