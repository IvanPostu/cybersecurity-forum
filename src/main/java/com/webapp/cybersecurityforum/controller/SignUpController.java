package com.webapp.cybersecurityforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {

  @GetMapping(value = "/sign-up")
  public String getRegosytation(){
    return "pages/sign-up/index";
  }

}