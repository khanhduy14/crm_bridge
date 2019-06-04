package com.topica.crm.bridge.odoo.processor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class ConvertOdooObjectToMap implements OdooBaseProcess {
  @Override
  public Object process(Object input) {
    ObjectMapper oMapper = new ObjectMapper();
    oMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    HashMap<String, Object> map = oMapper.convertValue(input, HashMap.class);
    return map;
  }
}
