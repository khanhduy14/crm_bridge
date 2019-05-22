package com.topica.crm.bridge.processor.sql.entity;

import com.topica.crm.bridge.core.entity.BaseObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class JsonContact extends BaseObject {
  @Id public String id;
  public static final String TYPE = "CONTACT";
}
