package com.topica.crm.bridge.transport.config;

import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.core.chain.ProcessorChainListImpl;
import com.topica.crm.bridge.core.processor.ConvertObjectToMap;
import com.topica.crm.bridge.hubspot.processor.ConvertHubspotContactToContactProcessor;
import com.topica.crm.bridge.hubspot.processor.ConvertStringToHubspotContactProcessor;
import com.topica.crm.bridge.odoo.processor.AddContactToOdoo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class BeanConfig {
  @Bean
  public AddContactToOdoo addContactToOdoo() {
    return new AddContactToOdoo();
  }

  @Bean
  public ConvertObjectToMap convertObjectToMap() {
    return new ConvertObjectToMap();
  }

  @Bean
  public ConvertStringToHubspotContactProcessor convertStringToHubspotContactProcessor() {
    return new ConvertStringToHubspotContactProcessor();
  }

  @Bean
  ConvertHubspotContactToContactProcessor convertHubspotContactToContactProcessor() {
    return new ConvertHubspotContactToContactProcessor();
  }

  @Bean
  @Primary
  @Qualifier("contactChain")
  public ProcessorChain contactChain() {
    log.info("init chain");
    ProcessorChain chain = new ProcessorChainListImpl();
    chain.add(convertHubspotContactToContactProcessor());
    chain.add(convertObjectToMap());
    chain.add(addContactToOdoo());
    return chain;
  }
}
