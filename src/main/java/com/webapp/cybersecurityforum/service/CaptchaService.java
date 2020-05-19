package com.webapp.cybersecurityforum.service;

import java.util.Random;

import com.github.cage.GCage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CaptchaService {

  @Autowired
  private GCage gCage;

  @Value("${captcha.alphabet}")
  private String alphabet;

  @Value("${captcha.length.min}")
  private Integer minCaptchaLength;

  @Value("${captcha.length.max}")
  private Integer maxCaptchaLength;

  public String generateToken(){
    StringBuilder resultToken = new StringBuilder();
    Random random = new Random();
    int captchaLength = random.nextInt( maxCaptchaLength - minCaptchaLength + 1) + minCaptchaLength;
    
    for(int i=0; i<captchaLength; i++){
      int charIndex = random.nextInt(alphabet.length());
      resultToken.append(alphabet.charAt(charIndex));
    }

    return resultToken.toString();
  }

  public Byte[] generateCaptcha(String token){

    byte[] imgCaptcha = gCage.draw(token);
    Byte[] byteObject = new Byte[imgCaptcha.length];
    for(int i=0; i<imgCaptcha.length; i++){
      byteObject[i] = imgCaptcha[i];
    }

    return byteObject;
  }

}