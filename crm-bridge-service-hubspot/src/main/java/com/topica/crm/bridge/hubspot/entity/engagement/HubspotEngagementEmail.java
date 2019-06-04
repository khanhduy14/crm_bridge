package com.topica.crm.bridge.hubspot.entity.engagement;

import com.topica.crm.bridge.hubspot.entity.suport.HubspotEmail;
import lombok.Data;

@Data
public class HubspotEngagementEmail extends HubspotEngagement {

  private HubspotEmail metadata;
}
