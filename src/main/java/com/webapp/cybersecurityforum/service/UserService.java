package com.webapp.cybersecurityforum.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    UserDetails user = new UserDetails(){
    
      @Override
      public boolean isEnabled() {
        return true;
      }
    
      @Override
      public boolean isCredentialsNonExpired() {
        return true;
      }
    
      @Override
      public boolean isAccountNonLocked() {
        return true;
      }
    
      @Override
      public boolean isAccountNonExpired() {
        return true;
      }
    
      @Override
      public String getUsername() {
        return "u";
      }
    
      @Override
      public String getPassword() {
        return "p";
      }
    
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new GrantedAuthority(){@Override
        public String getAuthority() {
          return "USER";
        }});
      }
    };

    return user;
  }

  

}