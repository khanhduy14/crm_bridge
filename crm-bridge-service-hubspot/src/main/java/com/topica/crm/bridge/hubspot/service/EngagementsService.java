package com.topica.crm.bridge.hubspot.service;

import com.github.icovn.http.client.HttpMethod;
import com.github.icovn.http.client.HttpResult;
import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.hubspot.entity.engagement.HubspotEngagement;
import com.topica.crm.bridge.hubspot.entity.engagement.HubspotEngagementList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EngagementsService extends BaseService {

  public List<HubspotEngagement> getEngagements(String objectType, String objectId) {
    String uriTemplate =
        "/engagements/v1/engagements/associated/"
            + objectType
            + "/"
            + objectId
            + "?hapikey={KEY}&vidOffset={OFFSET}&offset={OFFSET}&count=100";
    List<HubspotEngagement> result = new ArrayList<>();

    long offset = 0;
    boolean willQuery = true;
    while (willQuery) {
      HubspotEngagementList engagementList = null;
      try {
        String url =
            uriTemplate.replace("{KEY}", apiKey).replace("{OFFSET}", Long.toString(offset));
        HttpResult httpResult = httpClient.query(HttpMethod.GET, apiBase + url, false, headers);

        if (httpResult.getStatusCode() == 200) {
          engagementList =
              MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotEngagementList.class);
        } else {
          log.error("(getEngagements)httpResult: {}", httpResult);
        }
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        engagementList = null;
      }

      if (engagementList != null) {
        result.addAll(engagementList.getResults());

        if (engagementList.isHasMore()) {
          offset = engagementList.getOffset();
        } else {
          willQuery = false;
        }
      } else {
        willQuery = false;
      }
    }

    return result;
  }
}
