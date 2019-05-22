package com.topica.crm.bridge.processor.api;

import com.topica.crm.bridge.core.processor.BaseProcessor;
import org.springframework.web.client.RestTemplate;

public class CallApiProcessor implements BaseProcessor {

  @Override
  public Object process(Object input) {
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.postForObject("http://tmp.com", input, String.class);
    return result;
  }
}
