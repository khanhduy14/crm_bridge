package com.topica.crm.bridge.hubspot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.icovn.http.client.HttpResult;
import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.hubspot.entity.changelog.HubspotChangelog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ChangelogService extends BaseService {
  public Set<Long> getChangeLog(String objectType, String timestamp) {
    String uriTemplate = "/crm-objects/v1/change-log/{TYPE}?hapikey={KEY}&timestamp={TIMESTAMP}";
    HttpResult httpResult = getChangelogObject(uriTemplate, objectType, timestamp);

    Set<Long> result = new HashSet<>();
    if (httpResult.getStatusCode() == 200) {
      try {
        List<HubspotChangelog> changelogs = MapperUtil.getMapper().readValue(httpResult.getBody(), new TypeReference<List<HubspotChangelog>>() {});
        for(HubspotChangelog changelog: changelogs){
          result.add(changelog.getObjectId());
        }
      } catch (IOException ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
      }
    } else {
      log.error("(getChangelog)httpResult: {}", httpResult);
    }
    return result;
  }
}
