package com.topica.crm.bridge.hubspot.entity.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class HubspotDealList {

  private List<HubspotDeal> deals;

  @JsonProperty("hasMore")
  private boolean hasMore = false;

  @JsonProperty("offset")
  private long offset;
}
