package com.topica.crm.bridge.hubspot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotFormFields {
  private String name;
  private String label;
  private String type;
  private String description;
}
