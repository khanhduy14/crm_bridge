package com.topica.crm.bridge.hubspot.entity.suport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotVersions {

  private String value;

  @JsonProperty("source-type")
  private String sourceType;

  @JsonProperty("source-id")
  private String sourceId;

  @JsonProperty("source-label")
  private String sourceLabel;

  private Long timestamp;
}
