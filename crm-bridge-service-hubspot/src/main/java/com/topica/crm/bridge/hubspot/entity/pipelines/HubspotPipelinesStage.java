package com.topica.crm.bridge.hubspot.entity.pipelines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HubspotPipelinesStage {

  private String stageId;

  @JsonProperty("createdAt")
  private String createdAt;

  @JsonProperty("updatedAt")
  private String updatedAt;

  private String label;

  private Integer displayOrder;

  private HubspotPipelinesMetadata metadata;

  private Boolean active;
}
