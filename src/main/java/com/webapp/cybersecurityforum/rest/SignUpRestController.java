package com.webapp.cybersecurityforum.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp.cybersecurityforum.service.CaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sign-up")
public class SignUpRestController {

  @Autowired
  private CaptchaService captchaService;

  @GetMapping(value = "/captcha-generate", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getSignUpCaptcha(
    HttpServletRequest request,
    HttpServletResponse response
  ){

    String captchaToken = captchaService.generateToken();
    Byte[] captchaImage = captchaService.generateCaptcha(captchaToken);

    HttpSession session = request.getSession();
    session.setAttribute("SignUpCaptcha", captchaToken);

    Map<String, Object> result = new HashMap<>();
    result.put("captcha", captchaImage);

    return ResponseEntity
      .ok()
      .body(result);
  }

}