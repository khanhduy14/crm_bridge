package com.topica.crm.hubspot.direct.config;

import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.core.chain.ProcessorChainListImpl;
import com.topica.crm.bridge.hubspot.processor.ConvertStringToHubspotContactProcessor;
import com.topica.crm.bridge.processor.es.ElasticSearchTransportClient;
import com.topica.crm.bridge.processor.es.SaveObjectToElasticSearchProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Slf4j
public class ModuleConfig {

  @Value("${elastic.search.index.contact}")
  private String contactIndex;

  @Value("${elastic.search.index.deal}")
  private String dealIndex;

  @Value("${elastic.search.index.ticket}")
  private String ticketIndex;

  @Value("${elastic.search.host}")
  private String elasticSearchHost;

  @Bean
  public ElasticSearchTransportClient elasticSearchTransportClient() {
    return new ElasticSearchTransportClient(elasticSearchHost);
  }

  @Bean
  public ConvertStringToHubspotContactProcessor convertStringToHubspotContactProcessor() {
    return new ConvertStringToHubspotContactProcessor();
  }

  @Bean
  public SaveObjectToElasticSearchProcessor saveContactToElasticSearchProcessor() {
    return new SaveObjectToElasticSearchProcessor(contactIndex, elasticSearchTransportClient());
  }

  @Bean
  public SaveObjectToElasticSearchProcessor saveDealToElasticSearchProcessor() {
    return new SaveObjectToElasticSearchProcessor(dealIndex, elasticSearchTransportClient());
  }

  @Bean
  public SaveObjectToElasticSearchProcessor saveTicketToElasticSearchProcessor() {
    return new SaveObjectToElasticSearchProcessor(ticketIndex, elasticSearchTransportClient());
  }

  @Bean
  @Qualifier("contactChain")
  public ProcessorChain contactChain() {
    log.info("init contactChain");
    ProcessorChain chain = new ProcessorChainListImpl();
    chain.add(saveContactToElasticSearchProcessor());
    return chain;
  }

  @Bean
  @Qualifier("dealChain")
  public ProcessorChain dealChain() {
    log.info("init dealChain");
    ProcessorChain chain = new ProcessorChainListImpl();
    chain.add(saveDealToElasticSearchProcessor());
    return chain;
  }

  @Bean
  @Qualifier("ticketChain")
  public ProcessorChain ticketChain() {
    log.info("init ticketChain");
    ProcessorChain chain = new ProcessorChainListImpl();
    chain.add(saveTicketToElasticSearchProcessor());
    return chain;
  }

  @Bean
  @Primary
  @Qualifier("hookChain")
  public ProcessorChain hookChain() {
    log.info("init hookChain");
    ProcessorChain chain = new ProcessorChainListImpl();
    chain.add(convertStringToHubspotContactProcessor());
    chain.add(saveContactToElasticSearchProcessor());
    return chain;
  }
}
