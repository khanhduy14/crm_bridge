package com.topica.crm.bridge.hubspot.entity;

import com.topica.crm.bridge.core.entity.hubspot.HubspotContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotContactList {

  private List<HubspotContact> contacts;
}
