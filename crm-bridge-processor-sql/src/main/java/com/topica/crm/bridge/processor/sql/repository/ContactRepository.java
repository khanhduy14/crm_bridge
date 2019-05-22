package com.topica.crm.bridge.processor.sql.repository;

import com.topica.crm.bridge.processor.sql.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
  Optional<Contact> findById(String id);

  Optional<Contact> findByExternalId(String externalId);
}
