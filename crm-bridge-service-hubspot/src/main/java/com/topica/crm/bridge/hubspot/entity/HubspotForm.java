package com.topica.crm.bridge.hubspot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HubspotForm {
  private String guid;
  private String name;
  private String action;
  private String method;
  private List<HubspotFormFields> fields;
}
