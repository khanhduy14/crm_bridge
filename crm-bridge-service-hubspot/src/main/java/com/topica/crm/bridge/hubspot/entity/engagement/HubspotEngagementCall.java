package com.topica.crm.bridge.hubspot.entity.engagement;

import com.topica.crm.bridge.hubspot.entity.suport.HubspotCall;
import lombok.Data;

@Data
public class HubspotEngagementCall extends HubspotEngagement {

  private HubspotCall metadata;
}
