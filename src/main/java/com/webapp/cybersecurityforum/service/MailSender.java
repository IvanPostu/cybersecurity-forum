package com.webapp.cybersecurityforum.service;

public interface MailSender {

  public void send(String emailTo, String subject, String message);

}