package com.topica.crm.bridge.hubspot.entity.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HubspotProperties {

    private String name;

    private String label;

    private String description;

    private String groupName;

    private String type;

    private String fieldType;

    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("createdAt")
    private String createdAt;
}
