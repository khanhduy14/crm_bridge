package com.topica.crm.bridge.hubspot.service;

import com.github.icovn.http.client.HttpResult;
import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.hubspot.entity.properties.HubspotListProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PropertiesService extends BaseService {
  public HubspotListProperties getProperties(String objectType) {
    String uriTemplate = "/properties/v2/{TYPE}/properties?hapikey={KEY}";
    HttpResult httpResult = getObjectType(uriTemplate, objectType);
    if (httpResult.getStatusCode() == 200) {
      try {
        String temp = "{\"results\":" + httpResult.getBody() + "}";
        return MapperUtil.getMapper().readValue(temp, HubspotListProperties.class);
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        return null;
      }
    } else {
      log.error("(getDeal)httpResult: {}", httpResult);
      return null;
    }
  }
}
