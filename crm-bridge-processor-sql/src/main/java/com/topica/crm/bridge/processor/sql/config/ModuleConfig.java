package com.topica.crm.bridge.processor.sql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
  "com.topica.crm.bridge.processor.sql",
  "com.topica.crm.bridge.processor.sql.repository",
})
@EntityScan("com.topica.crm.bridge.processor.sql.entity")
@Configuration
@Slf4j
public class ModuleConfig {}
