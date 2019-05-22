package com.topica.crm.hubspot.direct;

import com.github.icovn.util.ExceptionUtil;
import com.topica.crm.bridge.core.chain.ProcessorChainListImpl;
import com.topica.crm.bridge.core.processor.PrintToConsoleProcessor;
import com.topica.crm.bridge.hubspot.exception.HSExeption;
import com.topica.crm.bridge.hubspot.processor.ConvertHubspotContactToContactProcessor;
import com.topica.crm.bridge.hubspot.processor.ConvertStringToHubspotContactProcessor;
import com.topica.crm.bridge.hubspot.service.ContactService;
import com.topica.crm.bridge.hubspot.service.DealService;
import com.topica.crm.bridge.hubspot.service.EngagementsService;
import com.topica.crm.bridge.processor.sql.SaveContactToSqlProcessor;
import com.topica.crm.bridge.processor.sql.config.EnableSQLConfig;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.topica.crm.bridge.processor.sql")
@Slf4j
@ComponentScan("com.topica.crm.bridge.hubspot.service")
@EnableSQLConfig
@EntityScan("com.topica.crm.bridge.processor.sql.entity")
public class Application implements CommandLineRunner {

  @Autowired ContactService contactService;

  @Autowired DealService dealService;

  @Autowired
  SaveContactToSqlProcessor saveContactToSqlProcessor;

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      getContact();
    } catch (HSExeption hsExeption) {
      log.info(ExceptionUtil.getFullStackTrace(hsExeption, true));
    }
  }

  public void getContact() throws HSExeption {
    ConvertStringToHubspotContactProcessor convertStringToHubspotContactProcessor =
        new ConvertStringToHubspotContactProcessor();
    ConvertHubspotContactToContactProcessor convertHubspotContactToContactProcessor =
        new ConvertHubspotContactToContactProcessor();
    PrintToConsoleProcessor printToConsoleProcessor = new PrintToConsoleProcessor();

    SaveContactToSqlProcessor saveContactToSqlProcessor = new SaveContactToSqlProcessor();

    List<JSONObject> contactList = contactService.getAllJSON();
    for (JSONObject contact : contactList) {
      ProcessorChainListImpl chainList = new ProcessorChainListImpl();
      chainList.add(convertStringToHubspotContactProcessor);
      chainList.add(convertHubspotContactToContactProcessor);
      chainList.add(saveContactToSqlProcessor);
      chainList.add(printToConsoleProcessor);
      chainList.process(contact);
    }
  }
}
