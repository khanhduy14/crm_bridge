package com.topica.crm.bridge.odoo.processor;

import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.entity.Contact;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.odoo.entity.contact.OdooContact;
import org.springframework.stereotype.Service;

@Service
public class ConvertOdooContactToContact implements BaseProcessor {

    @Override
    public BaseObject process(BaseObject input) {
        Contact contact = new Contact();
        OdooContact odooContact = (OdooContact) input;
        contact.setAddress(odooContact.getContactAddress());
        contact.setCity(odooContact.getCity());
        try {
            contact.setCountry(odooContact.getCountryId().get(1).toString());
        } catch (NullPointerException ex) {
            contact.setCountry("");
        }

        contact.setEmail(odooContact.getEmail());
        contact.setExternalId(String.valueOf(odooContact.getId()));
        contact.setFullName(odooContact.getName());
        contact.setPhone(odooContact.getPhone());
        contact.setLastModified(odooContact.getLastUpdate());
        return contact;
    }
}
