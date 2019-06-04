package com.topica.crm.bridge.odoo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topica.crm.bridge.odoo.entity.sale.OdooSale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class OdooSaleService extends OdooBaseService {

  @Autowired ConvertObject convertObject;

  public List<OdooSale> getSale() {
    List<Object> objects = getObject("sale.order", "search_read", Collections.emptyList());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    List<OdooSale> odooSales = new LinkedList<>();
    objects.forEach(
        object -> {
          JsonNode jsonObject = convertObject.convertObject(object, objectMapper);
          try {
            OdooSale odooSale = objectMapper.treeToValue(jsonObject, OdooSale.class);
            odooSales.add(odooSale);
          } catch (JsonProcessingException e) {
            log.info(e.getMessage());
          }
        });
    return odooSales;
  }

  private Integer setProperty(String fieldName, JsonNode jsonNode) {
    try {
      return jsonNode.get(fieldName).get(0).asInt();
    } catch (NullPointerException e) {
      log.error(e.getMessage());
      return null;
    }
  }
}
