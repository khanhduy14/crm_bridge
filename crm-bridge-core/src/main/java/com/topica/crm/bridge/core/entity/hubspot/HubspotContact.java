package com.topica.crm.bridge.core.entity.hubspot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topica.crm.bridge.core.entity.BaseObject;
import java.util.LinkedHashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotContact extends BaseObject {

  private long vid;

  @JsonProperty("canonical-vid")
  private long canonicalVid;

  @JsonProperty("portal-id")
  private long portalId;

  private LinkedHashMap<String, HubspotProperty> properties;

  public HubspotProperty getProperty(String key) {
    if (properties == null) {
      return null;
    }

    return properties.get(key);
  }

  public String getStringProperty(String key) {
    HubspotProperty output = getProperty(key);
    if (output != null) {
      return output.getValue();
    }

    return null;
  }
}
