package com.topica.crm.hubspot.direct.service;

import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.core.constant.ObjectType;
import com.topica.crm.bridge.core.service.ConfigService;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDeal;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicket;
import com.topica.crm.bridge.processor.es.ElasticSearchTransportClient;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigServiceEsImpl implements ConfigService {

  @Value("${application.default.force-use-config}")
  private boolean defaultForceUseConfig;

  @Value("${application.default.last-modified-at}")
  private Long defaultLastModifiedAt;

  @Value("${elastic.search.index.contact}")
  private String contactIndex;
  @Value("${elastic.search.index.deal}")
  private String dealIndex;
  @Value("${elastic.search.index.ticket}")
  private String ticketIndex;

  private ElasticSearchTransportClient transport;

  public ConfigServiceEsImpl(ElasticSearchTransportClient transport) {
    this.transport = transport;
  }

  @Override
  public long getLastUpdatedTime(ObjectType type) {
    log.info("(getLastUpdatedTime)type: {}", type);
    if(defaultForceUseConfig){
      return defaultLastModifiedAt;
    }

    try{
      SearchResponse searchResponse;
      switch (type){
        case CONTACT:
          searchResponse = getMax(contactIndex, "lastUpdatedAt");
          if (searchResponse.getHits().getTotalHits() > 0) {
            HubspotContact hubspotContact = MapperUtil.getMapper()
                .readValue(searchResponse.getHits().getAt(0).getSourceAsString(), HubspotContact.class);
            return hubspotContact.getLastUpdatedAt();
          }
          break;

        case DEAL:
          searchResponse = getMax(dealIndex, "lastUpdatedAt");
          if (searchResponse.getHits().getTotalHits() > 0) {
            HubspotDeal hubspotDeal = MapperUtil.getMapper()
                .readValue(searchResponse.getHits().getAt(0).getSourceAsString(), HubspotDeal.class);
            return hubspotDeal.getLastUpdatedAt();
          }
          break;

        case TICKET:
          searchResponse = getMax(ticketIndex, "lastUpdatedAt");
          if (searchResponse.getHits().getTotalHits() > 0) {
            HubspotTicket hubspotTicket = MapperUtil.getMapper()
                .readValue(searchResponse.getHits().getAt(0).getSourceAsString(), HubspotTicket.class);
            return hubspotTicket.getLastUpdatedAt();
          }
          break;
      }

      return defaultLastModifiedAt;
    }catch (Exception ex){
      log.error("(getLastUpdatedTime)ex: {}", ExceptionUtil.getFullStackTrace(ex, true));
      return defaultLastModifiedAt;
    }
  }

  @Override
  public void setLastUpdatedTime(ObjectType type, long timestamp) {

  }

  private SearchResponse getMax(String index, String field) throws UnknownHostException {
    log.debug("(search)index: {}, field: {}", index, field);
    return transport
        .getClient()
        .prepareSearch(index)
        .addAggregation(AggregationBuilders.max("agg").field("field"))
        .setSize(1) // only get one record
        .execute()
        .actionGet();
  }
}
