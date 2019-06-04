package com.topica.crm.bridge.hubspot.entity.engagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topica.crm.bridge.hubspot.entity.suport.HubspotAssociations;
import com.topica.crm.bridge.hubspot.entity.suport.HubspotEngagementDetail;
import java.util.Map;
import lombok.Data;

@Data
public class HubspotEngagement {

  private HubspotEngagementDetail engagement;

  private HubspotAssociations associations;

  @JsonProperty("metadata")
  private Map<String, Object> meta;
}
