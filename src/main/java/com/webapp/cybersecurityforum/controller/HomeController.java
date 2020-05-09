package com.webapp.cybersecurityforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

  @GetMapping(value="/")
  public String home() {
    return new String("pages/home/index");
  }

  @GetMapping(value="/about")
  public String about() {
    return new String("pages/home/about");
  }
  
  @GetMapping(value="/contact")
  public String contact() {
    return new String("pages/home/contact");
  }

}