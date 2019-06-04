package com.topica.crm.bridge.hubspot.entity.suport;

import lombok.Data;

@Data
public class HubspotEngagementDetail {

  private long id;

  private long portalId;

  private boolean active;

  private long createdAt;

  private long lastUpdated;

  private long ownerId;

  private String type;

  private long timestamp;
}
