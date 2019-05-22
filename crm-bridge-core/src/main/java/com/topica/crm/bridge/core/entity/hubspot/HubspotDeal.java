package com.topica.crm.bridge.core.entity.hubspot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HubspotDeal {
  private String dealId;
  private Boolean isDeleted;
}
