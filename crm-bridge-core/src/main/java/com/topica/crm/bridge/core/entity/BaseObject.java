package com.topica.crm.bridge.core.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class BaseObject extends HashMap {

  protected String id;

  protected String data;

  protected Long lastUpdatedAt;

  public static BaseObject of(String data) {
    BaseObject baseObject = new BaseObject();
    baseObject.setData(data);
    return baseObject;
  }
}
