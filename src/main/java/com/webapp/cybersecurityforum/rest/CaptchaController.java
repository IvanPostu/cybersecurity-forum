package com.webapp.cybersecurityforum.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.cybersecurityforum.service.CaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/captcha")
public class CaptchaController {

  @Autowired
  private CaptchaService captchaService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getCaptcha(
    HttpServletRequest request,
    HttpServletResponse response
  ){

    Byte[] captchaImage = captchaService.generateCaptcha();
    Map<String, Object> result = new HashMap<>();
    result.put("captcha", captchaImage);

    return ResponseEntity
      .ok()
      .body(result);
  }

}