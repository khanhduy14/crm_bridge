package com.topica.crm.bridge.odoo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topica.crm.bridge.odoo.entity.contact.OdooContactV2;
import com.topica.crm.bridge.odoo.entity.pipeline.OdooPipelineV2;
import com.topica.crm.bridge.odoo.entity.sale.OdooSale;
import com.topica.crm.bridge.odoo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@Slf4j
public class TestOdooApplication implements CommandLineRunner {
  @Autowired OdooContactService odooContactService;

  @Autowired OdooBaseService odooBaseService;

  @Autowired OdooPipelineService odooPipelineService;

  @Autowired OdooCustomerService odooCustomerService;

  @Autowired OdooSaleService odooSaleService;

  @Override
  public void run(String... args) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    List<OdooPipelineV2> odooSales = odooPipelineService.getPipeline();
    odooSales.forEach(
        odooSale -> {
          HashMap<String, Object> map = objectMapper.convertValue(odooSale, HashMap.class);
          odooBaseService.createObject(map, "sale.order");
        });
    //          log.info(odooBaseService.getObject("res.partner", "search_xread").toString());
  }

  public static void main(String[] args) {
    SpringApplication.run(TestOdooApplication.class);
  }
}
