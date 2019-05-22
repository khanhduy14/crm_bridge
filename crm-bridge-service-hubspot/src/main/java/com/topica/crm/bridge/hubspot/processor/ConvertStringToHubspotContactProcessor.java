package com.topica.crm.bridge.hubspot.processor;

import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.core.entity.hubspot.HubspotContact;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ConvertStringToHubspotContactProcessor implements BaseProcessor {

  @Override
  public Object process(Object input) {
    try {
      return MapperUtil.getMapper().readValue(input.toString(), HubspotContact.class);
    } catch (IOException ex) {
      log.error("(process)ex: {}", ExceptionUtil.getFullStackTrace(ex, true));
      return null;
    }
  }
}
