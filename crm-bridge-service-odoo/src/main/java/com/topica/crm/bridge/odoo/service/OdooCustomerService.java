package com.topica.crm.bridge.odoo.service;

import com.topica.crm.bridge.odoo.entity.contact.OdooContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Slf4j
@Service
public class OdooCustomerService extends OdooBaseService {
  public List<OdooContact> getCustomer() {
    List<Object> objects =
        getObject("res.partner", "search_read", asList(asList(asList("customer", "=", true))));
    return null;
  }
}
