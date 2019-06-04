package com.topica.crm.bridge.odoo.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.odoo.entity.contact.OdooContact;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class ConvertObjectToOdooObject implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      JsonNode jsonObject = objectMapper.valueToTree(input);
      OdooContact odooContact = objectMapper.treeToValue(jsonObject, OdooContact.class);
      return odooContact;
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      return null;
    }
  }
}
