package com.topica.crm.hubspot.direct.listener;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import com.topica.crm.bridge.hubspot.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContactListener {

  @Autowired
  @Qualifier("contactChain")
  private ProcessorChain contactChain;

  @Autowired private ContactService contactService;

  @RabbitListener(queues = "${application.queue.contact}")
  public void process(HubspotContact object) {
    try{
      log.info("(process)object: {}", object.getId());
      HubspotContact contact = contactService.getContact(object.getId());
      contactChain.process(contact);
    }catch (Exception ex){
      log.error("(process)ex: {}", ExceptionUtil.getFullStackTrace(ex, true));
    }
  }
}
