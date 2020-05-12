package com.webapp.cybersecurityforum.controller;
 
import java.util.Collections;
import java.util.HashSet;

import javax.validation.Valid;

import com.webapp.cybersecurityforum.domain.dto.SaveUserDto;
import com.webapp.cybersecurityforum.domain.entity.UserEntity;
import com.webapp.cybersecurityforum.domain.entity.UserRole;
import com.webapp.cybersecurityforum.service.CaptchaService;
import com.webapp.cybersecurityforum.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class SignUpController {

  private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private CaptchaService captchaService;

  @GetMapping(value = "/sign-up")
  public String getSignUpPage(){
    return "pages/sign-up/index";
  }

  @PostMapping(value = "/sign-up")
  public String registrateUser(@Valid SaveUserDto userDto, BindingResult bindingResult, Model model){
    model.addAttribute("email", userDto.getEmail());

    if (bindingResult.hasErrors()) {
      bindingResult.getFieldErrors()
        .stream()
        .forEach(
          a -> {
            model.addAttribute(a.getField() + "Error", a.getDefaultMessage());
          }
        );
      
      logger.debug("Sign up dto is not valid", userDto);
      return "pages/sign-up/index";
    }else{
      UserEntity user = new UserEntity();
      user.setActive(true);
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setRoles(Collections.singleton(UserRole.UserRoleFactory()));

      UserEntity savedUser = userService.saveUser(user).orElseThrow(() -> {
        logger.debug("Repository method save return null");
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sign up error.");
      });

      logger.debug("User has been inserted into database", savedUser);
      return "redirect:/sign-in";
    }
  }


}