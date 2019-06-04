package com.topica.crm.bridge.hubspot.service;

import com.github.icovn.http.client.HttpResult;
import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContact;
import com.topica.crm.bridge.hubspot.entity.contact.HubspotContactList;
import com.topica.crm.bridge.hubspot.entity.engagement.HubspotEngagement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContactService extends BaseService {

  @Autowired private EngagementsService engagementsService;

  public HubspotContact getContact(String id) {
    String uriTemplate =
        "/contacts/v1/contact/vid/{ID}/profile?hapikey={KEY}&includePropertyVersions=true";
    HttpResult httpResult = getObject(uriTemplate, id);

    if (httpResult.getStatusCode() == 200) {
      try {
        HubspotContact contact =
            MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotContact.class);
        List<HubspotEngagement> engagements =
            engagementsService.getEngagements("contact", contact.getId());
        contact.setEngagements(engagements);
        return contact;
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        return null;
      }
    } else {
      log.error("(getContact)httpResult: {}", httpResult);
      return null;
    }
  }

  public List<HubspotContact> getContacts() {
    List<HubspotContact> result = new ArrayList<>();

    long offset = 0;
    boolean willQuery = true;
    while (willQuery) {
      HubspotContactList contacts = getContacts(offset);
      if (contacts == null) {
        return result;
      }

      for (HubspotContact contact : contacts.getContacts()) {
        List<HubspotEngagement> engagements =
            engagementsService.getEngagements("contact", contact.getId());
        contact.setEngagements(engagements);
        result.add(contact);
      }

      if (contacts.isHasMore()) {
        offset = contacts.getOffset();
      } else {
        willQuery = false;
      }
    }
    return result;
  }

  public HubspotContactList getContacts(long offset) {
    String uriTemplate =
        "/contacts/v1/lists/all/contacts/all?hapikey={KEY}&vidOffset={OFFSET}&offset={OFFSET}&count=100";
    HttpResult httpResult = getListObject(uriTemplate, offset);

    if (httpResult.getStatusCode() == 200) {
      try {
        return MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotContactList.class);
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        return null;
      }
    } else {
      log.error("(getContacts)httpResult: {}", httpResult);
      return null;
    }
  }
}
