package com.topica.crm.bridge.odoo.processor;

import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.odoo.service.OdooBaseService;

public class AddContactToOdoo implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
    OdooBaseService odooBaseService = new OdooBaseService();
    odooBaseService.createObject(input, "res.partner");
    return null;
  }
}
