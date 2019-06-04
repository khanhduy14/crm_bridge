package com.topica.crm.bridge.odoo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConvertObject {
  public JsonNode convertObject(Object object, ObjectMapper objectMapper) {
    JsonNode jsonObject = objectMapper.valueToTree(object);
    Map<String, Object> result = objectMapper.convertValue(jsonObject, Map.class);
    result.values().removeIf(value -> value.toString().equals("false"));
    jsonObject = objectMapper.valueToTree(result);
    return jsonObject;
  }
}
