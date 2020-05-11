package com.webapp.cybersecurityforum.controller;

import javax.validation.Valid;

import com.webapp.cybersecurityforum.domain.dto.SaveUserDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

  @GetMapping(value = "/sign-up")
  public String getSignUpPage(){
    return "pages/sign-up/index";
  }

  @PostMapping(value = "/sign-up")
  public String registrateUser(@Valid SaveUserDto user, BindingResult bindingResult, Model model){

    if (bindingResult.hasErrors()) {
      bindingResult.getFieldErrors()
        .stream()
        .forEach(
          a -> {
            model.addAttribute(a.getField() + "Error", a.getDefaultMessage());
          }
        );
    }


    return "pages/sign-up/index";
  }


}