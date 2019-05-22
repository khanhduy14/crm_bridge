package com.topica.crm.bridge.hubspot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HubspotCompany {
  private String name;
  private String description;

  public HubspotCompany(Map<String, String> properties) {
    this.name = properties.get("name");
    this.description = properties.get("description");
  }
}
