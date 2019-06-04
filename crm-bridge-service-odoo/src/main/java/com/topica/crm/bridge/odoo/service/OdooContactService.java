package com.topica.crm.bridge.odoo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topica.crm.bridge.odoo.entity.contact.OdooContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class OdooContactService extends OdooBaseService {
  @Autowired ConvertObject convertObject;

  public List<OdooContact> getContact() {
    List<Object> objects = getObject("res.partner", "search_read", Collections.emptyList());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    List<OdooContact> odooContacts = new LinkedList<>();
    objects.forEach(
        object -> {
          JsonNode jsonObject = convertObject.convertObject(object, objectMapper);
          try {
            OdooContact odooContact = objectMapper.treeToValue(jsonObject, OdooContact.class);
            odooContacts.add(odooContact);
          } catch (JsonProcessingException e) {
            log.info(e.getMessage());
          }
        });
    return odooContacts;
  }
}
