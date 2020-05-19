package com.webapp.cybersecurityforum.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.webapp.cybersecurityforum.domain.entity.UserEntity;
import com.webapp.cybersecurityforum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

  @Autowired
  UserService userService;

  @GetMapping(value = "/sign-in")
  public String signInGetPage( Model model){
    return "pages/sign-in/index";
  }
  
  @PostMapping(value = "/sign-in")
  public String signIn(
    @AuthenticationPrincipal UserEntity user,
    HttpServletRequest request,
    @RequestParam(name = "email") String email,
    @RequestParam(name = "password") String password,
    Model model){

    UserDetails userFromDb = userService.loadUserByUsername(email);

    if(!userFromDb.getPassword().equals(password)){
      throw new RuntimeException("Password is not correct!");
    }

    Authentication auth = new UsernamePasswordAuthenticationToken(
      userFromDb, null, userFromDb.getAuthorities()
    );

    SecurityContextHolder.getContext().setAuthentication(auth);

    return "pages/sign-in/index";
  }

}