package com.topica.crm.bridge.hubspot.entity.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topica.crm.bridge.hubspot.entity.engagement.HubspotEngagement;
import com.topica.crm.bridge.hubspot.entity.suport.HubspotObject;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class HubspotContact extends HubspotObject {

  private long vid;

  @JsonProperty("canonical-vid")
  @Setter(AccessLevel.NONE)
  private long canonicalVid;

  @JsonProperty("portal-id")
  private long portalId;

  @Setter(AccessLevel.NONE)
  private List<HubspotEngagement> engagements = new ArrayList<>();

  public void setEngagements(List<HubspotEngagement> engagements) {
    this.engagements = engagements;
  }

  public void setCanonicalVid(long canonicalVid) {
    this.canonicalVid = canonicalVid;

    this.id = Long.toString(canonicalVid);
  }

  public Long getLastUpdatedAt() {
    if(this.properties != null && this.properties.get("lastmodifieddate") != null){
      this.lastUpdatedAt = Long.parseLong(this.properties.get("lastmodifieddate").getValue());
    }

    return this.lastUpdatedAt;
  }
}
