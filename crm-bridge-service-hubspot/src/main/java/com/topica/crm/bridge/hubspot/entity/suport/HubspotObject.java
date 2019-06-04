package com.topica.crm.bridge.hubspot.entity.suport;

import com.topica.crm.bridge.core.entity.BaseObject;
import java.util.LinkedHashMap;
import lombok.Data;

@Data
public class HubspotObject extends BaseObject {

  protected LinkedHashMap<String, HubspotProperty> properties;

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
