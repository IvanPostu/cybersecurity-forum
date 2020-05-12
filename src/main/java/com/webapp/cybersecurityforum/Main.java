package com.webapp.cybersecurityforum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  @Value("${spring.profiles.active}")
  private String profiles;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if(profiles.contains("DEV")){
      logger.info("Application is running in DEBUG mode.");
    }
    if(profiles.contains("PROD")){
      logger.info("Application is running in PRODUCTION mode.");
    }
  }

}
