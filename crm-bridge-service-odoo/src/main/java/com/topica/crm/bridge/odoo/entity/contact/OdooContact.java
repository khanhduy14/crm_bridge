package com.topica.crm.bridge.odoo.entity.contact;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.topica.crm.bridge.core.entity.BaseObject;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OdooContact extends BaseObject{


  private String phone;

  private String city;

  private String street;

  private String name;

  private Integer externalId;

  private String contactAddress;

  private String emailFormatted;

  private String displayName;

  private String image;

  private String createDate;

  private String imageSmall;

  private String email;

}
