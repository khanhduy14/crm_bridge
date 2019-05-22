package com.topica.crm.bridge.hubspot.controller;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.hubspot.entity.HubspotWebhookResquest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class HubspotWebhook {

  @Autowired
  private ProcessorChain processorChain;

  @PostMapping("/webhook/hubspot")
  public void hubspotWebHook(@RequestBody List<HubspotWebhookResquest> list){
    log.info("(hubspotWebHook)count data: {}", list.size());
  }

  @PostMapping("/webhook/hubspot/workflow")
  public void hubspotWebHookWorkflow(HttpServletRequest httpServletRequest){
    try {
      String body = getBody(httpServletRequest);
      processorChain.process(body);
      log.info(body);
    } catch (IOException e) {
      log.error("(hubspotWebHookWorkflow)ex: {}", ExceptionUtil.getFullStackTrace(e, true));
    }
  }

  private String getBody(HttpServletRequest httpServletRequest) throws IOException{
    return httpServletRequest
        .getReader()
        .lines()
        .collect(Collectors.joining(System.lineSeparator()));
  }
}
