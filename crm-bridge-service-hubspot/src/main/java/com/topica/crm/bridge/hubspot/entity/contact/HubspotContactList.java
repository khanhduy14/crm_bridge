package com.topica.crm.bridge.hubspot.entity.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class HubspotContactList {

  private List<HubspotContact> contacts;

  @JsonProperty("has-more")
  private boolean hasMore = false;

  @JsonProperty("vid-offset")
  private long offset;
}
