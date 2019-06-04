package com.topica.crm.bridge.hubspot.entity.changelog;


import java.util.ArrayList;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class HubspotChangelogList {
  private List<HubspotChangelog> hubspotChangelogs = new ArrayList<>();

}
