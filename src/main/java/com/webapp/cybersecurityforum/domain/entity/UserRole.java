package com.webapp.cybersecurityforum.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
  USER,
  ADMIN;

  @Override
  public String getAuthority() {
    return this.name();
  }
}