package com.topica.crm.hubspot.direct.listener;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDeal;
import com.topica.crm.bridge.hubspot.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DealListener {

  @Autowired
  @Qualifier("dealChain")
  private ProcessorChain dealChain;

  @Autowired private DealService dealService;

  @RabbitListener(queues = "${application.queue.deal}")
  public void process(HubspotDeal object) {
    try{
      log.info("(process)object: {}", object.getId());
      HubspotDeal deal = dealService.getDeal(object.getId());
      dealChain.process(deal);
    }catch (Exception ex){
      log.error("(process)ex: {}", ExceptionUtil.getFullStackTrace(ex, true));
    }
  }
}
