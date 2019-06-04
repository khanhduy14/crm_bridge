package com.topica.crm.bridge.hubspot.entity.deal;

import com.topica.crm.bridge.hubspot.entity.engagement.HubspotEngagement;
import com.topica.crm.bridge.hubspot.entity.suport.HubspotObject;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class HubspotDeal extends HubspotObject {

  @Setter(AccessLevel.NONE)
  private String dealId;

  private List<HubspotEngagement> engagements = new ArrayList<>();

  public void setDealId(String dealId) {
    this.dealId = dealId;

    this.id = dealId;
  }

  public Long getLastUpdatedAt() {
    if(this.properties != null && this.properties.get("hs_lastmodifieddate") != null){
      this.lastUpdatedAt = Long.parseLong(this.properties.get("hs_lastmodifieddate").getValue());
    }

    return this.lastUpdatedAt;
  }
}
