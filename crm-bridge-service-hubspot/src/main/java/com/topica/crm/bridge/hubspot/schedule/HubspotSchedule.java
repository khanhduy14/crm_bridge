package com.topica.crm.bridge.hubspot.schedule;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import com.topica.crm.bridge.hubspot.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class HubspotSchedule {

  @Value("${application.hubspot.enable.schedule:false}")
  private boolean enableSchedule;

  @Autowired private ContactService contactService;

  @Qualifier("scheduleChain")
  private ProcessorChain processorChain;

  public void hubspotAutoScan() {
    if (enableSchedule) {
      ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
      executorService.scheduleAtFixedRate(
          () -> {
            try {
              List<HubspotContact> contactList = contactService.getContacts();
              for (HubspotContact contact : contactList) {
                processorChain.process(contact);
              }
            } catch (Exception ex) {
              log.error(ExceptionUtil.getFullStackTrace(ex, true));
            }
          },
          0,
          10,
          TimeUnit.SECONDS);
    }
  }
}
