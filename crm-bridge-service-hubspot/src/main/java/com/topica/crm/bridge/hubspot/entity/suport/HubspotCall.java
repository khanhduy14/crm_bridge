package com.topica.crm.bridge.hubspot.entity.suport;

import lombok.Data;

@Data
public class HubspotCall {

  private String toNumber;

  private String fromNumber;

  private String status;

  private String externalId;

  private long durationMilliseconds;

  private String externalAccountId;

  private String recordingUrl;

  private String body;

  private String disposition;
}
