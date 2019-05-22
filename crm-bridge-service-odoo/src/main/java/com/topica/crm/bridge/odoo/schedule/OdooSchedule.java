package com.topica.crm.bridge.odoo.schedule;

import com.topica.crm.bridge.core.chain.ProcessorChain;
import com.topica.crm.bridge.core.entity.odoo.OdooContact;
import com.topica.crm.bridge.odoo.service.OdooService;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OdooSchedule {

  @Autowired
  private OdooService odooService;

  @Autowired
  private ProcessorChain processorChain;

  public void odooAutoScan() {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    executorService.scheduleAtFixedRate(
        () -> {
          List<OdooContact> odooContacts = odooService.mapToModel(odooService.getContact());
          odooContacts.forEach(
              odooContact -> {
                processorChain.process(odooContact);
              });
        },
        0,
        10,
        TimeUnit.SECONDS);
  }
}
