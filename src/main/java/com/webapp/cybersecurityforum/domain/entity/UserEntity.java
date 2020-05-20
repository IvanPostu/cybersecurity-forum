package com.webapp.cybersecurityforum.domain.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements UserDetails {

  private static final long serialVersionUID = -8427025120500985305L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true)
  private Long id;

  private String email;

  private String password;

  private Boolean active;

  private String activationCode;

  @ManyToMany
  @JoinTable(
    name = "app_user_user_role", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<UserRole> roles = new HashSet<>();

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_info_id")
  private UserInfoEntity userInfo;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || this.getClass() != o.getClass())
      return false;
    UserEntity user = (UserEntity) o;
    return user.getId().equals(this.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }

}