package com.topica.crm.bridge.hubspot.service;

import com.github.icovn.http.client.HttpResult;
import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDeal;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDealList;
import com.topica.crm.bridge.hubspot.entity.engagement.HubspotEngagement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DealService extends BaseService {

  @Autowired private EngagementsService engagementsService;

  public HubspotDeal getDeal(String id) {
    String uriTemplate = "/deals/v1/deal/{ID}?hapikey={KEY}&includePropertyVersions=true";
    HttpResult httpResult = getObject(uriTemplate, id);

    if (httpResult.getStatusCode() == 200) {
      try {
        HubspotDeal deal =
            MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotDeal.class);
        List<HubspotEngagement> engagements =
            engagementsService.getEngagements("deal", deal.getId());
        deal.setEngagements(engagements);
        return deal;
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        return null;
      }
    } else {
      log.error("(getDeal)httpResult: {}", httpResult);
      return null;
    }
  }

  public HubspotDealList getDeals(long offset) {
    String uriTemplate =
        "/deals/v1/deal/paged?hapikey={KEY}&vidOffset={OFFSET}&offset={OFFSET}&count=100";

    HttpResult httpResult = getListObject(uriTemplate, offset);

    if (httpResult.getStatusCode() == 200) {
      try {
        return MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotDealList.class);
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        return null;
      }
    } else {
      log.error("(getDeals)httpResult: {}", httpResult);
      return null;
    }
  }
}
