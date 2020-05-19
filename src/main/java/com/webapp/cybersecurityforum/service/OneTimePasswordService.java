package com.webapp.cybersecurityforum.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OneTimePasswordService {

  public String generateOneTimePassword(){

    Random random = new Random();
    StringBuilder result = new StringBuilder();

    for(int i=0; i<6; i++){
      result.append(random.nextInt(10));
    }


    return result.toString();
  }

}