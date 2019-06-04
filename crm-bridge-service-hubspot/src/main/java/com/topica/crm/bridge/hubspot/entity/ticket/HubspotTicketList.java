package com.topica.crm.bridge.hubspot.entity.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class HubspotTicketList {

  @JsonProperty("objects")
  private List<HubspotTicket> tickets;

  private boolean hasMore = false;

  private long offset;
}
