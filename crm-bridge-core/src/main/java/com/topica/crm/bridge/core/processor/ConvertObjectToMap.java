package com.topica.crm.bridge.core.processor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topica.crm.bridge.core.entity.BaseObject;

import java.util.HashMap;

public class ConvertObjectToMap implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
    ObjectMapper oMapper = new ObjectMapper();
    oMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    HashMap<String, Object> map = oMapper.convertValue(input, HashMap.class);
    return (BaseObject) map;
  }
}
