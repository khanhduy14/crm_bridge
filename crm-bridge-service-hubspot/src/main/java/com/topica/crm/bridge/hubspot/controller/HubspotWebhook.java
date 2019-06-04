package com.topica.crm.bridge.hubspot.controller;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.core.entity.BaseObject;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HubspotWebhook {

  @Qualifier("hookChain")
  private ProcessorChain processorChain;

  @PostMapping("/webhook/hubspot/workflow")
  public void hubspotWebHookWorkflow(HttpServletRequest httpServletRequest) {
    try {
      String body = getBody(httpServletRequest);
      log.info("(hubspotWebHookWorkflow)body: {}", body);
//      processorChain.process(BaseObject.of(body));
    } catch (IOException e) {
      log.error("(hubspotWebHookWorkflow)ex: {}", ExceptionUtil.getFullStackTrace(e, true));
    }
  }

  private String getBody(HttpServletRequest httpServletRequest) throws IOException {
    return httpServletRequest
        .getReader()
        .lines()
        .collect(Collectors.joining(System.lineSeparator()));
  }
}
