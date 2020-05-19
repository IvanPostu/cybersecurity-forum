package com.webapp.cybersecurityforum.controller;
 
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.webapp.cybersecurityforum.controller.validator.SignUpValidator;
import com.webapp.cybersecurityforum.domain.dto.SaveUserDto;
import com.webapp.cybersecurityforum.domain.entity.UserEntity;
import com.webapp.cybersecurityforum.domain.entity.UserRole;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class SignUpController {

  private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private SignUpValidator signUpValidator;

  @GetMapping(value = "/sign-up")
  public String getSignUpPage(){
    return "pages/sign-up/index";
  }

  @PostMapping(value = "/sign-up")
  public String registrateUser(
    HttpServletRequest request,
    HttpServletResponse response,
    @RequestParam(name = "captcha") String captcha,
    @Valid SaveUserDto userDto, 
    BindingResult bindingResult, 
    Model model
  ){
    model.addAttribute("email", userDto.getEmail());

    HttpSession session = request.getSession();
    String captchaFromSession = (String)session.getAttribute("SignUpCaptcha");
    session.removeAttribute("SignUpCaptcha");
    Map<String, String> validationErrors = signUpValidator
      .validate(userDto, bindingResult, captchaFromSession, captcha);

    if(!validationErrors.isEmpty()){
      model.mergeAttributes(validationErrors);
      logger.debug("Sign up dto is not valid", userDto);
      return "pages/sign-up/index";
    }

    UserEntity user = new UserEntity();
    user.setActive(true);
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setRoles(Collections.singleton(UserRole.RoleUser()));

    UserEntity savedUser = userService.saveUser(user).orElseThrow(() -> {
      logger.debug("Repository method save return null");
      return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sign up error.");
    });

    logger.debug("User has been inserted into database", savedUser);
    return "redirect:/sign-in";
  }


}