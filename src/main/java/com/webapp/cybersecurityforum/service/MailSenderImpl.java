package com.webapp.cybersecurityforum.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@PropertySource(
  ignoreResourceNotFound = false,
  value = "classpath:secret.properties")
public class MailSenderImpl implements MailSender {

  @Value("${spring.mail.username}")
  private String username;

  @Autowired
  private JavaMailSender javaMailSender;

  public void send(String emailTo, String subject, String message)  {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    try {
      mimeMessage.setContent(message, "text/html");
      helper.setText(message, true);
      helper.setFrom(username);
      helper.setTo(emailTo);
      helper.setSubject(subject);
    }catch(MessagingException e){
      e.printStackTrace();
    }

    javaMailSender.send(mimeMessage);
  }

}