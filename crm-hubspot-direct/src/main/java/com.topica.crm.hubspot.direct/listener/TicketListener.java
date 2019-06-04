package com.topica.crm.hubspot.direct.listener;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicket;
import com.topica.crm.bridge.hubspot.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TicketListener {

  @Autowired
  @Qualifier("ticketChain")
  private ProcessorChain ticketChain;

  @Autowired private TicketService ticketService;

  @RabbitListener(queues = "${application.queue.ticket}")
  public void process(HubspotTicket object) {
    try{
      log.info("(process)object: {}", object.getId());
      HubspotTicket ticket = ticketService.getTicket(object.getId());
      ticketChain.process(ticket);
    }catch (Exception ex){
      log.error("(process)ex: {}", ExceptionUtil.getFullStackTrace(ex, true));
    }
  }
}
