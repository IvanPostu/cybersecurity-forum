package com.webapp.cybersecurityforum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

  @Value("${debug}")
  private Boolean isDebug;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if(isDebug){
      logger.info("Application is running in DEBUG mode.");
    }else{
      logger.info("Application is running in PRODUCTION mode.");
    }
  }
  
  

}
