package com.topica.crm.bridge.hubspot.schedule;

import com.topica.crm.bridge.core.constant.ObjectType;
import com.topica.crm.bridge.core.service.ConfigService;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDeal;
import com.topica.crm.bridge.hubspot.entity.properties.HubspotProperties;
import com.topica.crm.bridge.hubspot.entity.suport.HubspotProperty;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicket;
import com.topica.crm.bridge.hubspot.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class HubspotChangelogSchedule {
  @Autowired ChangelogService changelogService;

  @Autowired DealService dealService;

  @Autowired ContactService contactService;

  @Autowired TicketService ticketService;

  @Autowired ConfigService configService;

  @Autowired RabbitTemplate rabbitTemplate;

  @Autowired PropertiesService propertiesService;

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
    List<HubspotProperties> hubspotProperties =
        propertiesService.getProperties("deal").getHubspotProperties();
    List<String> propertiesLabel =
        hubspotProperties.stream().map(HubspotProperties::getLabel).collect(Collectors.toList());
    for (Long dealId : dealIds) {
      HubspotDeal deal = dealService.getDeal(Long.toString(dealId));
      LinkedHashMap<String, HubspotProperty> mapProperties = deal.getProperties();
      modifiedKey(hubspotProperties, mapProperties);
      rabbitTemplate.convertAndSend(queueDeal, deal);
    }
  }

  @Scheduled(cron = "${application.schedule.cron.contact}")
  public void contactSchedule() {
    log.info("(contactSchedule)");
    String timestampContact = String.valueOf(configService.getLastUpdatedTime(ObjectType.CONTACT));
    Set<Long> contactIds = changelogService.getChangeLog("contact", timestampContact);
    List<HubspotProperties> hubspotProperties =
        propertiesService.getProperties("contact").getHubspotProperties();
    for (Long contactId : contactIds) {
      HubspotContact contact = contactService.getContact(Long.toString(contactId));
      LinkedHashMap<String, HubspotProperty> mapProperties = contact.getProperties();
      modifiedKey(hubspotProperties, mapProperties);
      rabbitTemplate.convertAndSend(queueContact, contact);
    }
  }

  @Scheduled(cron = "${application.schedule.cron.ticket}")
  public void ticketSchedule() {
    log.info("(ticketSchedule)");
    String timestampTicket = String.valueOf(configService.getLastUpdatedTime(ObjectType.TICKET));
    Set<Long> ticketIds = changelogService.getChangeLog("ticket", timestampTicket);
    List<HubspotProperties> hubspotProperties =
        propertiesService.getProperties("ticket").getHubspotProperties();
    for (Long ticketId : ticketIds) {
      HubspotTicket ticket = ticketService.getTicket(Long.toString(ticketId));
      LinkedHashMap<String, HubspotProperty> mapProperties = ticket.getProperties();
      modifiedKey(hubspotProperties, mapProperties);
      rabbitTemplate.convertAndSend(queueTicket, ticket);
    }
  }

  private HubspotProperties getProperties(String propertiesName, List<HubspotProperties> list) {
    HubspotProperties hubspotProperties =
        list.stream()
            .filter(customer -> propertiesName.equals(customer.getName()))
            .findAny()
            .orElse(null);

    return hubspotProperties;
  }

  private void modifiedKey(
      List<HubspotProperties> hubspotProperties,
      LinkedHashMap<String, HubspotProperty> mapProperties) {
    for (Map.Entry properties : mapProperties.entrySet()) {
      mapProperties.put(
          getProperties((String) properties.getKey(), hubspotProperties).getLabel(),
          (HubspotProperty) properties.getValue());
      mapProperties.remove(properties.getKey());
    }
  }
}
