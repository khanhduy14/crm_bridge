package com.topica.crm.bridge.processor.sql;

import com.topica.crm.bridge.core.processor.BaseProcessor;
import com.topica.crm.bridge.processor.sql.entity.Contact;
import com.topica.crm.bridge.processor.sql.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.topica.crm.bridge.processor.sql.repository")
@EntityScan("com.topica.crm.bridge.processor.sql.entity")
public class SaveContactToSqlProcessor implements BaseProcessor {

  @Autowired
  ContactRepository contactRepository;

  @Override
  public Object process(Object input) {
    Contact contact = (Contact) input;
    contactRepository.save(contact);
    return input;
  }
}
