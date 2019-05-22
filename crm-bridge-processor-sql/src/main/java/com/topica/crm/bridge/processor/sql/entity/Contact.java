package com.topica.crm.bridge.processor.sql.entity;

import com.topica.crm.bridge.core.entity.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table
public class Contact extends BaseObject {
  @Id
  private String id;

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
