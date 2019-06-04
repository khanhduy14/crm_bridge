package com.topica.crm.bridge.hubspot.schedule;

import com.topica.crm.bridge.core.constant.ObjectType;
import com.topica.crm.bridge.core.service.ConfigService;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDeal;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicket;
import com.topica.crm.bridge.hubspot.service.ChangelogService;
import com.topica.crm.bridge.hubspot.service.ContactService;
import com.topica.crm.bridge.hubspot.service.DealService;
import com.topica.crm.bridge.hubspot.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class HubspotChangelogSchedule {
  @Autowired ChangelogService changelogService;

  @Autowired DealService dealService;

  @Autowired ContactService contactService;

  @Autowired TicketService ticketService;

  @Autowired ConfigService configService;

  @Autowired RabbitTemplate rabbitTemplate;

  @Value("${application.queue.contact}")
  private String queueContact;

  @Value("${application.queue.deal}")
  private String queueDeal;

  @Value("${application.queue.ticket}")
  private String queueTicket;

  @Scheduled(cron = "${application.schedule.cron.deal}")
  public void dealSchedule() {
    log.info("(dealSchedule)");
    String timestampDeal = String.valueOf(configService.getLastUpdatedTime(ObjectType.DEAL));
    Set<Long> dealIds = changelogService.getChangeLog("deal", timestampDeal);
    for (Long dealId : dealIds) {
      HubspotDeal deal = dealService.getDeal(Long.toString(dealId));
      rabbitTemplate.convertAndSend(queueDeal, deal);
    }
  }

  @Scheduled(cron = "${application.schedule.cron.contact}")
  public void contactSchedule() {
    log.info("(contactSchedule)");
    String timestampContact = String.valueOf(configService.getLastUpdatedTime(ObjectType.CONTACT));
    Set<Long> contactIds = changelogService.getChangeLog("contact", timestampContact);
    for (Long contactId : contactIds) {
      HubspotContact contact = contactService.getContact(Long.toString(contactId));
      rabbitTemplate.convertAndSend(queueContact, contact);
    }
  }

  @Scheduled(cron = "${application.schedule.cron.ticket}")
  public void ticketSchedule() {
    log.info("(ticketSchedule)");
    String timestampTicket = String.valueOf(configService.getLastUpdatedTime(ObjectType.TICKET));
    Set<Long> ticketIds = changelogService.getChangeLog("ticket", timestampTicket);
    for (Long ticketId : ticketIds) {
      HubspotTicket ticket = ticketService.getTicket(Long.toString(ticketId));
      rabbitTemplate.convertAndSend(queueTicket, ticket);
    }
  }
}
