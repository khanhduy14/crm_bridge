package com.topica.crm.bridge.core.processor;

import com.topica.crm.bridge.core.entity.BaseObject;

public interface BaseProcessor {

  BaseObject process(BaseObject input);
}
