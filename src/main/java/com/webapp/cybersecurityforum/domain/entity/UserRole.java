package com.webapp.cybersecurityforum.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Entity
@Table(name="user_role")
@Getter
public final class UserRole {

  private enum Role implements GrantedAuthority, Cloneable {
    USER,
    ADMIN;
  
    @Override
    public String getAuthority() {
      return this.name();
    }
  }

  private UserRole(){

  }

  private UserRole(Short id, Role role){
    this.id = id;
    this.role = role;
  }

  public static UserRole UserRoleFactory(){
    return new UserRole((short)Role.USER.ordinal(), Role.USER);
  }

  public static UserRole AdminRoleFactory(){
    return new UserRole((short)Role.ADMIN.ordinal(), Role.ADMIN);
  }

  @Id
  @GeneratedValue
  private Short id;

  @Enumerated(EnumType.STRING)
  @Column(name = "name")
  private Role role;

}

