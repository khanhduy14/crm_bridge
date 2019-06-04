package com.topica.crm.bridge.hubspot.entity.pipelines;

import lombok.Data;

@Data
public class HubspotPipelinesMetadata {

  private String ticketState;

  private String isClosed;
}
