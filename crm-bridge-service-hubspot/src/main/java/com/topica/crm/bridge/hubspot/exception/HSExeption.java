package com.topica.crm.bridge.hubspot.exception;

import lombok.Data;

@Data
public class HSExeption extends Exception {

  private int code;

  private String rawMessage;

  public HSExeption(String message) {
    super(message);
  }

  public HSExeption(String message, Throwable cause) {
    super(message, cause);
  }

  public HSExeption(String message, int code) {
    super(message);
    this.code = code;
  }

  public HSExeption(String message, String rawMessage) {
    super(message);
    this.rawMessage = rawMessage;
  }
}
