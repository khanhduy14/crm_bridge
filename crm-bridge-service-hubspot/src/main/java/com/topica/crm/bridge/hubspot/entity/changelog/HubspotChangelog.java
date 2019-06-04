package com.topica.crm.bridge.hubspot.entity.changelog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class HubspotChangelog {
  private Long timestamp;
  private String changeType;

  private Long objectId;

  @JsonProperty("changes")
  private Map<String, Object> changes;
}
