package com.webapp.cybersecurityforum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // @Autowired
  // private UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers(
          "/",
          "/libs/**",
          "/js/**",
          "/css/**",
          "/images/**",
          "/sign-up",
          "/sign-in",
          "/api/sign-up/captcha-generate")
        .permitAll()
        .anyRequest()
        .authenticated()
      .and()
        .formLogin()
        .loginProcessingUrl("/login") //dummy url
        .loginPage("/sign-in")
        .defaultSuccessUrl("/", true) 
        .permitAll()
      .and()
        .logout()
        .logoutUrl("/sign-out")
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    /**
     * No config because I use custom login controller.
     */
  }

}