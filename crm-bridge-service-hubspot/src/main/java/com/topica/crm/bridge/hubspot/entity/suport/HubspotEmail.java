package com.topica.crm.bridge.hubspot.entity.suport;

import java.util.List;
import lombok.Data;

@Data
public class HubspotEmail {

  private HubspotEmailFrom from;

  private List<HubspotEmailTo> to;

  private List<String> cc;

  private List<String> bcc;

  private String subject;

  private String html;

  private String text;
}
