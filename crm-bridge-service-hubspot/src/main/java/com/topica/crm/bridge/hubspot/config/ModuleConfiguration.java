package com.topica.crm.bridge.hubspot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
  "com.topica.crm.bridge.hubspot.controller",
  "com.topica.crm.bridge.hubspot.schedule",
  "com.topica.crm.bridge.hubspot.service"
})
@Configuration
@Slf4j
public class ModuleConfiguration {}
