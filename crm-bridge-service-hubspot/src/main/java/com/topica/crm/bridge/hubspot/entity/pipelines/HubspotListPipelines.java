package com.topica.crm.bridge.hubspot.entity.pipelines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class HubspotListPipelines {
  @JsonProperty("results")
  private List<HubspotPipelines> hubspotPipelines = new LinkedList<>();
}
