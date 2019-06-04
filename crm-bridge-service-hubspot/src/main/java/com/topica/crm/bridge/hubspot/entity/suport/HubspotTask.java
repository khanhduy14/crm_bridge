package com.topica.crm.bridge.hubspot.entity.suport;

import com.topica.crm.bridge.hubspot.constant.HubspotTaskStatus;
import lombok.Data;

@Data
public class HubspotTask {

  private String body;

  private String subject;

  private HubspotTaskStatus status;

  private String forObjectType;
}
