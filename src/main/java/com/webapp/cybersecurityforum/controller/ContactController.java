package com.webapp.cybersecurityforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

  @GetMapping(value="/contact")
  public String contact() {
    return new String("pages/contact/index");
  }

}