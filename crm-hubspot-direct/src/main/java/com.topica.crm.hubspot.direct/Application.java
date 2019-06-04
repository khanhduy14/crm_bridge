package com.topica.crm.hubspot.direct;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.core.constant.ObjectType;
import com.topica.crm.bridge.core.service.ConfigService;
import com.topica.crm.bridge.hubspot.config.EnableServiceHubspot;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContactList;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDeal;
import com.topica.crm.bridge.hubspot.entity.deal.HubspotDealList;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicket;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicketList;
import com.topica.crm.bridge.hubspot.service.ContactService;
import com.topica.crm.bridge.hubspot.service.DealService;
import com.topica.crm.bridge.hubspot.service.EngagementsService;
import com.topica.crm.bridge.hubspot.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableServiceHubspot
@EnableScheduling
public class Application implements CommandLineRunner {

  @Autowired ConfigService configService;
  @Autowired ContactService contactService;
  @Autowired DealService dealService;
  @Autowired EngagementsService engagementsService;
  @Autowired RabbitTemplate rabbitTemplate;
  @Autowired TicketService ticketService;

  @Autowired
  @Qualifier("contactChain")
  ProcessorChain contactChain;

  @Autowired
  @Qualifier("dealChain")
  ProcessorChain dealChain;

  @Value("${application.enable.load-all-data:false}")
  private boolean enableLoadAllData;
  @Value("${application.enable.load-ticket:false}")
  private boolean enableLoadTicket;

  @Value("${application.queue.contact}")
  private String queueContact;
  @Value("${application.queue.deal}")
  private String queueDeal;
  @Value("${application.queue.ticket}")
  private String queueTicket;

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      long lastUpdate = configService.getLastUpdatedTime(ObjectType.TICKET);
      log.info("(run)TICKET last update: {}", lastUpdate);
      if (enableLoadAllData) {
        loadContacts();
        loadDeals();
      }

      if (enableLoadTicket) {
        loadTickets();
      }
    } catch (Exception ex) {
      log.info(ExceptionUtil.getFullStackTrace(ex, true));
    }
  }

  private void loadContacts() {
    log.info("(loadContacts)load contacts");

    long offset = 0;
    boolean willQuery = true;
    while (willQuery) {
      log.info("(loadContacts)load contacts, offset: {}", offset);
      HubspotContactList contacts = contactService.getContacts(offset);

      if (contacts != null) {
        log.info("(loadContacts)load contacts, size: {}", contacts.getContacts().size());
        for (HubspotContact contact : contacts.getContacts()) {
          rabbitTemplate.convertAndSend(queueContact, contact);
        }

        if (contacts.isHasMore()) {
          offset = contacts.getOffset();
        } else {
          willQuery = false;
        }
      } else {
        willQuery = false;
      }
    }
  }

  private void loadDeals() {
    log.info("(loadDeals)load deals");
    long offset = 0;
    boolean willQuery = true;
    while (willQuery) {
      log.info("(loadDeals)load deals, offset: {}", offset);
      HubspotDealList deals = dealService.getDeals(offset);

      if (deals != null) {
        log.info("(loadDeals)load deals, size: {}", deals.getDeals().size());
        for (HubspotDeal deal : deals.getDeals()) {
          rabbitTemplate.convertAndSend(queueDeal, deal);
        }

        if (deals.isHasMore()) {
          offset = deals.getOffset();
        } else {
          willQuery = false;
        }
      } else {
        willQuery = false;
      }
    }
  }

  private void loadTickets() {
    log.info("(loadTickets)load tickets");

    long offset = 0;
    boolean willQuery = true;
    while (willQuery) {
      log.info("(loadTickets)load tickets, offset: {}", offset);
      HubspotTicketList tickets = ticketService.getTickets(offset);

      if (tickets != null) {
        log.info("(loadTickets)load tickets, size: {}", tickets.getTickets().size());
        for (HubspotTicket ticket : tickets.getTickets()) {
          rabbitTemplate.convertAndSend(queueTicket, ticket);
        }

        if (tickets.isHasMore()) {
          offset = tickets.getOffset();
        } else {
          willQuery = false;
        }
      } else {
        willQuery = false;
      }
    }
  }
}
