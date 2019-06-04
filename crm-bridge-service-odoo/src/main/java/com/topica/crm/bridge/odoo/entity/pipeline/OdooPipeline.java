package com.topica.crm.bridge.odoo.entity.pipeline;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OdooPipeline {
    private String partnerName;
    private String type;
    private String partnerAddressEmail;
    private String dateOpen;
    private String dateClosed;
    private String createDate;
    private String userEmail;
    private String phone;
    private String name;
    private String city;
    private String partnerAddressPhone;
    private String website;
    private String emailFrom;
}
