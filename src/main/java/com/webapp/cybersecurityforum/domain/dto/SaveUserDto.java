package com.webapp.cybersecurityforum.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveUserDto {

  private String firstName;
  private String lastName;

  @Email(message = "Email is not valid.")
  @NotBlank(message = "Email cannot be empty.")
  private String email;

  @Length(min = 6, max = 20, message = "Password length must be at least 6 and not more than 20")
  @NotBlank(message = "Password cannot be empty.")
  private String password;

}