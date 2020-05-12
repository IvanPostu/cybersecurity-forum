package com.webapp.cybersecurityforum.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import com.webapp.cybersecurityforum.domain.entity.UserEntity;
import com.webapp.cybersecurityforum.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

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

  public Optional<UserEntity> saveUser(UserEntity user){
    UserEntity savedUser = userRepository.save(user);
    return Optional.of(savedUser);
  }

}