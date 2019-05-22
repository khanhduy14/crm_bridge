package com.topica.crm.bridge.hubspot.entity;

import com.topica.crm.bridge.core.entity.hubspot.HubspotProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotWordFlow {

  private Long vid;

  private Long canonicalVid;

  private Long portalId;

  private Map<String, HubspotProperty> properties = new HashMap<>();
}
