package com.topica.crm.bridge.hubspot.entity.engagement;

import com.topica.crm.bridge.hubspot.entity.suport.HubspotTask;
import lombok.Data;

@Data
public class HubspotEngagementTask extends HubspotEngagement {

  private HubspotTask metadata;
}
