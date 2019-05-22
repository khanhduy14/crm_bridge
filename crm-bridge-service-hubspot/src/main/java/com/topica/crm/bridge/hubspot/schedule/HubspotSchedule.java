package com.topica.crm.bridge.hubspot.schedule;

import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.hubspot.exception.HSExeption;
import com.topica.crm.bridge.hubspot.service.ContactService;
import com.topica.crm.bridge.hubspot.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class HubspotSchedule {

  @Autowired private ContactService contactService;

  @Autowired private DealService dealService;

  @Autowired private ProcessorChain processorChain;

  public void hubspotAutoScan() {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    executorService.scheduleAtFixedRate(
        () -> {
          try {
            List<JSONObject> contactList = contactService.getAllJSON();
            List<JSONObject> dealList = dealService.getAll();
            contactList.forEach(
                hubspotContact -> {
                  processorChain.process(hubspotContact);
                });
            dealList.forEach(
                hubspotDeal -> {
                  processorChain.process(hubspotDeal);
                });
          } catch (HSExeption hsExeption) {
            log.info(hsExeption.getRawMessage());
          }
        },
        0,
        10,
        TimeUnit.SECONDS);
  }
}
