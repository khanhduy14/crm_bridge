package com.topica.crm.bridge.hubspot.entity.suport;

import java.util.List;
import lombok.Data;

@Data
public class HubspotAssociations {

  private List<Long> contactIds;

  private List<Long> companyIds;

  private List<Long> dealIds;

  private List<Long> ownerIds;

  private List<Long> workflowIds;

  private List<Long> ticketIds;
}
