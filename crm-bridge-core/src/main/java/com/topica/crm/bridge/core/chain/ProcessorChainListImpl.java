package com.topica.crm.bridge.core.chain;

import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessorChainListImpl implements ProcessorChain {

  private List<BaseProcessor> chain = new ArrayList<>();

  @Override
  public void add(BaseProcessor processor) {
    chain.add(processor);
  }

  @Override
  public void process(BaseObject input) {
    BaseObject output;
    for (BaseProcessor processor : chain) {
      log.info("(process)input: {}", MapperUtil.toJson(input));
      output = processor.process(input);
      input = output;
    }
  }
}
