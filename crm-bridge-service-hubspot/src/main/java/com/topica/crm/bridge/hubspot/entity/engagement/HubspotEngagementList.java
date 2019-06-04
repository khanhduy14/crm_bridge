package com.topica.crm.bridge.hubspot.entity.engagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotEngagementList {

  private List<HubspotEngagement> results;

  @JsonProperty("hasMore")
  private boolean hasMore = false;

  @JsonProperty("offset")
  private long offset;
}
