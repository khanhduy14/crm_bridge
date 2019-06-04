package com.topica.crm.bridge.core.service;

import com.topica.crm.bridge.core.constant.ObjectType;

public interface ConfigService {

  long getLastUpdatedTime(ObjectType type);

  void setLastUpdatedTime(ObjectType type, long timestamp);
}
