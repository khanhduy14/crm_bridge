package com.topica.crm.bridge.processor.sql.repository;

import com.topica.crm.bridge.processor.sql.entity.JsonDeal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealJsonStringRepository extends JpaRepository<JsonDeal, String> {
  Optional<JsonDeal> findById(String dealId);
}
