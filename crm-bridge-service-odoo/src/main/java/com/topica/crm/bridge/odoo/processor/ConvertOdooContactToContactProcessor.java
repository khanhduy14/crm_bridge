package com.topica.crm.bridge.odoo.processor;

import com.topica.crm.bridge.core.entity.odoo.OdooContact;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.processor.sql.entity.Contact;

public class ConvertOdooContactToContactProcessor implements BaseProcessor {

  @Override
  public Object process(Object input) {
    if (input instanceof OdooContact && input != null) {
      OdooContact odooContact = (OdooContact) input;
      Contact contact = new Contact();
      contact.setExternalId(String.valueOf(odooContact.getExternalId()));
      contact.setCity(odooContact.getCity());
      contact.setPhone(odooContact.getPhone());
      contact.setFullName(odooContact.getName());
      contact.setEmail(odooContact.getEmail());
      contact.setAddress(odooContact.getStreet());
      return contact;
    }

    return null;
  }
}
