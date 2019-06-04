package com.topica.crm.bridge.hubspot.entity.engagement;

import com.topica.crm.bridge.hubspot.entity.suport.HubspotMeeting;
import lombok.Data;

@Data
public class HubspotEngagementMeeting extends HubspotEngagement {

  private HubspotMeeting metadata;
}
