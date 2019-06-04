package com.topica.crm.bridge.hubspot.entity.suport;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotProperty {

  private String value;

  private List<HubspotVersions> versions;
}
