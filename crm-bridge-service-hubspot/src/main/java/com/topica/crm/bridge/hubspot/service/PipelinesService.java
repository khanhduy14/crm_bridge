package com.topica.crm.bridge.hubspot.service;

import com.github.icovn.http.client.HttpResult;
import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.hubspot.entity.pipelines.HubspotListPipelines;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PipelinesService extends BaseService {
  public HubspotListPipelines getPipelines(String objectType) {
    String uriTemplate = "/crm-pipelines/v1/pipelines/{TYPE}?hapikey={KEY}";
    HttpResult httpResult = getObjectType(uriTemplate, objectType);
    if (httpResult.getStatusCode() == 200) {
      try {
        return MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotListPipelines.class);
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
