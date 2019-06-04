package com.topica.crm.bridge.core.entity;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact extends BaseObject {

  private String email;

  private String firstName;

  private String lastName;

  private String fullName;
  private String phone;
  private String address;
  private String city;
  private String country;
  private String sex;
  private Date birthDay;
  private String lastModified;

  /** Id of contact in CRM system (ex: Hubspot, Odoo, ...) */
  private String externalId;

  public Contact() {
    this.setId(UUID.randomUUID().toString());
  }
}
