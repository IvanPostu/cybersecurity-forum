package com.webapp.cybersecurityforum.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.webapp.cybersecurityforum.domain.entity.UserEntity;
import com.webapp.cybersecurityforum.service.MailSender;
import com.webapp.cybersecurityforum.service.OneTimePasswordService;
import com.webapp.cybersecurityforum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

  @Autowired
  UserService userService;

  @Autowired
  MailSender mailSender;

  @Autowired
  OneTimePasswordService otpService;

  @GetMapping(value = "/sign-in")
  public String signInGetPage( Model model){
    return "pages/sign-in/index";
  }
  
  @PostMapping(value = "/sign-in")
  public String signIn(
    @AuthenticationPrincipal UserEntity user,
    HttpServletRequest request,
    @RequestParam(name = "email") String email,
    @RequestParam(name = "password", required = false) String password,
    @RequestParam(name = "codeEmail", required = false) String codeEmail,
    @RequestParam(
      name = "enterCodeWithPassword", 
      required = false,
      defaultValue = "false") Boolean enterCodeWithPassword,
    Model model){

    HttpSession session = request.getSession();
    UserDetails userFromDb = userService.loadUserByUsername(email);

    if(!userFromDb.getPassword().equals(password)){
      throw new RuntimeException("Password is not correct!");
    }

    if(!enterCodeWithPassword){
      model.addAttribute("email", email);
      model.addAttribute("enterCodeWithPassword", true);


      String oneTimePassword = otpService.generateOneTimePassword();
      session.setAttribute("otp", oneTimePassword);
      mailSender.send(email, "O.T.P.", oneTimePassword);

      return "pages/sign-in/index";
    }

    String otpFromSession = (String)session.getAttribute("otp");

    if(!StringUtils.isEmpty(otpFromSession)){
      if(otpFromSession.equals(codeEmail)){
        Authentication auth = new UsernamePasswordAuthenticationToken(
          userFromDb, null, userFromDb.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

      }
    }

    return "pages/sign-in/index";
  }


}