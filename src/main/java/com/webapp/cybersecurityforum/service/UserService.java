package com.webapp.cybersecurityforum.service;

import java.util.Optional;

import com.webapp.cybersecurityforum.domain.entity.UserEntity;
import com.webapp.cybersecurityforum.domain.entity.UserRole;
import com.webapp.cybersecurityforum.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    UserEntity userFromDb = userRepository.findByEmail(username);

    if(userFromDb == null){
      throw new UsernameNotFoundException("Email not correct!");
    }

    return (UserDetails)userFromDb;
  }

  public Optional<UserEntity> saveUser(UserEntity user){
    UserEntity savedUser = userRepository.save(user);
    return Optional.of(savedUser);
  }

  public void addRole(UserEntity user, UserRole role){
    
  }

}