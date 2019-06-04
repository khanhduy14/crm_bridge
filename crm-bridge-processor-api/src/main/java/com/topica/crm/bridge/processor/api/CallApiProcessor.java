package com.topica.crm.bridge.processor.api;

import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import org.springframework.web.client.RestTemplate;

public class CallApiProcessor implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.postForObject("http://tmp.com", input, String.class);
    return BaseObject.of(result);
  }
}
