package com.webapp.cybersecurityforum.controller.validator;

import java.util.HashMap;
import java.util.Map;

import com.webapp.cybersecurityforum.domain.dto.SaveUserDto;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

@Component
public class SignUpValidator {

  /**
   * 
   * @param userDto
   * @param bindingResult
   * @param model
   * @return empty map if not has errors, or else Map<ErrorName, ErrorMessage>
   */
  public Map<String, String> validate(
    SaveUserDto userDto, 
    BindingResult bindingResult, 
    String captchaFromSession,
    String captchaFromDto
  ){
    Map<String, String> result = new HashMap<>();

    if (bindingResult.hasErrors()) {
      bindingResult.getFieldErrors()
        .stream()
        .forEach(
          a -> {
            result.put(a.getField() + "Error", a.getDefaultMessage());
          }
        );
    }

    boolean captchaIsDefined = !StringUtils.isEmpty(captchaFromSession) 
      && !StringUtils.isEmpty(captchaFromDto);

    if(!captchaIsDefined || !captchaFromDto.equals(captchaFromSession)){
      result.put("captchaError", "Captcha is not valid!!!");
    }
    
    
    return result;
  }

}