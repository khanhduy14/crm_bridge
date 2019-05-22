package com.topica.crm.bridge.core.entity.odoo;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OdooContact {

  private String phone;

  private String city;

  private String street;

  private String name;

  private Integer externalId;

  private String contactAddress;

  private String email;

  public OdooContact(HashMap<String, Object> property) {
    this.phone = property.get("phone").toString();
    this.city = property.get("city").toString();
    this.street = property.get("street").toString();
    this.name = property.get("name").toString();
    this.externalId = (Integer) property.get("id");
    String temp = property.get("contact_address").toString();
    this.contactAddress = temp.replaceAll("\n", ", ");
    this.email = property.get("email").toString();
  }
}
