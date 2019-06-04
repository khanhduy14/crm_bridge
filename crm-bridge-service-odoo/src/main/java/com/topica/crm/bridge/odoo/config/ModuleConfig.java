package com.topica.crm.bridge.odoo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
  "com.topica.crm.bridge.odoo.controller",
  "com.topica.crm.bridge.odoo.schedule",
  "com.topica.crm.bridge.odoo.service"
})
@EntityScan("com.topica.crm.bridge.odoo.entity")
@Configuration
@Slf4j
public class ModuleConfig {}
