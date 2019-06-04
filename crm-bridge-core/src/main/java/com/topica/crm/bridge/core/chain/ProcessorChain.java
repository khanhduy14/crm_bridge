package com.topica.crm.bridge.core.chain;

import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;

public interface ProcessorChain {

  void add(BaseProcessor processor);

  void process(BaseObject input);
}
