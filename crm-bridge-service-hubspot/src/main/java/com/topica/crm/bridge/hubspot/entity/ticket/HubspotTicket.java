package com.topica.crm.bridge.hubspot.entity.ticket;

import com.topica.crm.bridge.hubspot.entity.suport.HubspotObject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class HubspotTicket extends HubspotObject {

  @Setter(AccessLevel.NONE)
  private long objectId;

  public void setObjectId(long objectId) {
    this.objectId = objectId;

    this.id = Long.toString(objectId);
  }

  public Long getLastUpdatedAt() {
    if(this.properties != null && this.properties.get("hs_lastmodifieddate") != null){
      this.lastUpdatedAt = Long.parseLong(this.properties.get("hs_lastmodifieddate").getValue());
    }

    return this.lastUpdatedAt;
  }
}
