package com.topica.crm.bridge.hubspot.processor;

import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertStringToHubspotContactProcessor implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
    try {
      return MapperUtil.getMapper().readValue(input.getData(), HubspotContact.class);
    } catch (IOException ex) {
      log.error("(process)ex: {}", ExceptionUtil.getFullStackTrace(ex, true));
      return null;
    }
  }
}
