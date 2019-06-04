package com.topica.crm.bridge.odoo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topica.crm.bridge.odoo.entity.pipeline.OdooPipeline;
import com.topica.crm.bridge.odoo.entity.pipeline.OdooPipelineV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class OdooPipelineService extends OdooBaseService {
    @Autowired ConvertObject convertObject;
  public List<OdooPipelineV2> getPipeline() {
    List<Object> objects = getObject("crm.lead", "search_read", Collections.emptyList());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    List<OdooPipelineV2> odooPipelines = new LinkedList<>();
    objects.forEach(
        object -> {
          JsonNode jsonObject = convertObject.convertObject(object, objectMapper);
          try {
            OdooPipelineV2 odooPipeline = objectMapper.treeToValue(jsonObject, OdooPipelineV2.class);
            odooPipelines.add(odooPipeline);
          } catch (JsonProcessingException e) {
            log.info(e.getMessage());
          }
        });
    return odooPipelines;
  }
}
