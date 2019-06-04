package com.topica.crm.bridge.hubspot.entity.suport;

import lombok.Data;

@Data
public class HubspotMeeting {

  private String body;

  private long startTime;

  private long endTime;

  private String title;
}
