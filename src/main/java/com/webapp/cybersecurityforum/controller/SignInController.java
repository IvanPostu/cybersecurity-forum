package com.webapp.cybersecurityforum.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

  @GetMapping(value = "/sign-in")
  public String signInGetPage(Model model){
    return "pages/sign-in/index";
  }
  
  @PostMapping(value = "/sign-in")
  public String signIn(
    @RequestParam(name = "email") String email,
    @RequestParam(name = "password") String password,
    Model model){

    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    GrantedAuthority grantedAuthority = new GrantedAuthority() {
        //anonymous inner type
        public String getAuthority() {
            return "ROLE_USER";
        }
    }; 
    grantedAuthorities.add(grantedAuthority);

    return "pages/sign-in/index";
  }



}