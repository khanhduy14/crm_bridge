package com.topica.crm.bridge.transport;

import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.hubspot.config.EnableServiceHubspot;
import com.topica.crm.bridge.hubspot.service.ContactService;
import com.topica.crm.bridge.odoo.config.EnableOdooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOdooConfig
@EnableServiceHubspot
@Slf4j
public class TransportApplication implements CommandLineRunner {

  @Autowired
  @Qualifier("contactChain")
  ProcessorChain contactChain;

  @Autowired ContactService contactService;

  @Override
  public void run(String... args) throws Exception {}

  public static void main(String[] args) {
    SpringApplication.run(TransportApplication.class);
  }
}
