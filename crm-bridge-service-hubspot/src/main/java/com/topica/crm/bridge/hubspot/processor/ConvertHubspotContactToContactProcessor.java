package com.topica.crm.bridge.hubspot.processor;

import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.entity.Contact;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;

public class ConvertHubspotContactToContactProcessor implements BaseProcessor {

  @Override
  public BaseObject process(BaseObject input) {
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
