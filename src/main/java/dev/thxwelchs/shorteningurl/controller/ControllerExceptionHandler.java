package dev.thxwelchs.shorteningurl.controller;

import dev.thxwelchs.shorteningurl.domain.UnRegistrationUrlException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler{
  @ExceptionHandler(UnRegistrationUrlException.class)
  public void handleUnRegistrationUrl(UnRegistrationUrlException e) {
    System.out.println(e.getMessage());
  }
}

