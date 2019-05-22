package com.topica.crm.bridge.core.chain;

import com.topica.crm.bridge.core.processor.BaseProcessor;
import java.util.ArrayList;
import java.util.List;

public class ProcessorChainListImpl implements ProcessorChain {

  private List<BaseProcessor> chain = new ArrayList<>();

  @Override
  public void add(BaseProcessor processor) {
    chain.add(processor);
  }

  @Override
  public void process(Object input) {
    Object output;
    for (BaseProcessor processor : chain) {
      output = processor.process(input);
      input = output;
    }
  }
}
