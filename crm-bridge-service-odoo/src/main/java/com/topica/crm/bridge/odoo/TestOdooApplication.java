package com.topica.crm.bridge.odoo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topica.crm.bridge.odoo.entity.contact.OdooContact;
import com.topica.crm.bridge.odoo.processor.ConvertOdooContactToContact;
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

  @Autowired
  ConvertOdooContactToContact convertOdooContactToContact;

  @Override
  public void run(String... args) throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    List<OdooContact> odooContacts = odooContactService.getContact();
    odooContacts.forEach(
        odooContact -> {
          HashMap<String, Object> map = objectMapper.convertValue(odooContact, HashMap.class);
          log.info(map.toString());
          odooBaseService.createObject(map, "res.partner");
        });
    //          log.info(odooBaseService.getObject("res.partner", "search_xread").toString());
  }

  public static void main(String[] args) {
    SpringApplication.run(TestOdooApplication.class);
  }
}
