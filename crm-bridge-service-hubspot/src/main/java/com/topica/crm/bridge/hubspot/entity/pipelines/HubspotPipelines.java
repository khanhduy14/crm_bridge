package com.topica.crm.bridge.hubspot.entity.pipelines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class HubspotPipelines {

  private String pipelineId;

  @JsonProperty("createdAt")
  private String createdAt;

  @JsonProperty("updatedAt")
  private String updatedAt;

  private String objectType;

  private String objectTypeId;

  private String label;

  private String displayOrder;

  private Boolean active;

  private List<HubspotPipelinesStage> stages = new LinkedList<>();
}
