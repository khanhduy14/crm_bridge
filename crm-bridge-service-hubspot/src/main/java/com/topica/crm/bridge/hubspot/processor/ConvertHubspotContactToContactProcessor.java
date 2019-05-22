package com.topica.crm.bridge.hubspot.processor;

import com.topica.crm.bridge.core.entity.hubspot.HubspotContact;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.processor.sql.entity.Contact;

public class ConvertHubspotContactToContactProcessor implements BaseProcessor {

  @Override
  public Contact process(Object input) {
    if (input instanceof HubspotContact && input != null) {
      HubspotContact hubspotContact = (HubspotContact) input;
      Contact contact = new Contact();
      contact.setFirstName(hubspotContact.getStringProperty("firstname"));
      contact.setLastName(hubspotContact.getStringProperty("lastname"));
      contact.setEmail(hubspotContact.getStringProperty("email"));
      contact.setPhone(hubspotContact.getStringProperty("phone"));
      contact.setAddress(hubspotContact.getStringProperty("address"));
      contact.setLastModified(hubspotContact.getStringProperty("lastmodifieddate"));
      contact.setExternalId(Long.toString(hubspotContact.getCanonicalVid()));
      return contact;
    }

    return null;
  }
}
