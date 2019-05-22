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
public class HubspotDealWebhook {
  private Long objectId;
  private Map<String, HubspotProperty> properties = new HashMap<>();
}
