package com.topica.crm.bridge.hubspot.entity.engagement;

import com.topica.crm.bridge.hubspot.entity.suport.HubspotNote;
import lombok.Data;

@Data
public class HubspotEngagementNote extends HubspotEngagement {

  private HubspotNote metadata;
}
