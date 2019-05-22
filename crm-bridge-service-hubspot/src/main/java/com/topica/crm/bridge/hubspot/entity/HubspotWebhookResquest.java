package com.topica.crm.bridge.hubspot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotWebhookResquest {

  private Long objectId;

  private String changeSource;

  private Long eventId;

  private Long subscriptionId;

  private Long portalId;

  private Long appId;

  private Long occurredAt;

  private String subscriptionType;

  private Long attemptNumber;
}
