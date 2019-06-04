package com.topica.crm.bridge.processor.sql.repository;

import com.topica.crm.bridge.core.entity.Contact;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
  Optional<Contact> findById(Long id);

  Optional<Contact> findByExternalId(String externalId);
}
