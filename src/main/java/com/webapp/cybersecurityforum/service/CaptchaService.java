package com.webapp.cybersecurityforum.service;

import com.github.cage.GCage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptchaService {

  @Autowired
  private GCage gCage;

  public Byte[] generateCaptcha(){

    final String token = gCage.getTokenGenerator().next();

    byte[] imgCaptcha = gCage.draw(token);
    Byte[] byteObject = new Byte[imgCaptcha.length];
    for(int i=0; i<imgCaptcha.length; i++){
      byteObject[i] = imgCaptcha[i];
    }

    return byteObject;
  }

}