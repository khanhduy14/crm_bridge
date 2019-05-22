package com.topica.crm.bridge.processor.sql.repository;

import com.topica.crm.bridge.processor.sql.entity.JsonContact;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactJsonStringRepository extends JpaRepository<JsonContact, String> {
  Optional<JsonContact> findById(String vid);
}
