package com.webapp.cybersecurityforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

  @GetMapping(value="/")
  public String home() {
    return new String("pages/home/index");
  }


}