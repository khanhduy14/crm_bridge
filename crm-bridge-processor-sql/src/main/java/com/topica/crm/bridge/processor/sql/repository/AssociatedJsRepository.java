package com.topica.crm.bridge.processor.sql.repository;

import com.topica.crm.bridge.processor.sql.entity.JsonAssociated;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociatedJsRepository extends JpaRepository<JsonAssociated, String> {}
