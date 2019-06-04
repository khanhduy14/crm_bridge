package com.topica.crm.bridge.odoo.processor;

import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.entity.Contact;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.odoo.entity.contact.OdooContact;

public class ConvertOdooContactToContactProcessor implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
    if (input instanceof OdooContact && input != null) {
      OdooContact odooContact = (OdooContact) input;
      Contact contact = new Contact();
      contact.setExternalId(String.valueOf(odooContact.getExternalId()));
      contact.setCity(odooContact.getCity());
      contact.setPhone(odooContact.getPhone());
      contact.setFullName(odooContact.getName());
      contact.setEmail(odooContact.getEmail());
      return contact;
    }

    return null;
  }
}
