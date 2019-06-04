package com.topica.crm.bridge.hubspot.entity.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class HubspotListProperties {
  @JsonProperty("results")
  private List<HubspotProperties> hubspotProperties = new LinkedList<>();
}
