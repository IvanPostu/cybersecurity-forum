package com.webapp.cybersecurityforum.controller;

import com.webapp.cybersecurityforum.domain.entity.UserEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

  @GetMapping(value="/")
  public String home(@AuthenticationPrincipal UserEntity user) {
    return new String("pages/home/index");
  }


}