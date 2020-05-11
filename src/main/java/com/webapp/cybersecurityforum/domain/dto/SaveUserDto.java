package com.webapp.cybersecurityforum.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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

  @Min(value = 6, message = "Minimum password length 6 characters.")
  @NotBlank(message = "Password cannot be empty.")
  private String password;

}