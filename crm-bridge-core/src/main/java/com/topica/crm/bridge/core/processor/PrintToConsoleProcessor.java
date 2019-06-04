package com.topica.crm.bridge.core.processor;

import com.topica.crm.bridge.core.entity.BaseObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintToConsoleProcessor implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
    log.info("(process)input: {}", input);
    return input;
  }
}
