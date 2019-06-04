package com.topica.crm.bridge.odoo.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.topica.crm.bridge.odoo.entity.sale.OdooSale;

import java.io.IOException;

public class CustomDeserializer extends JsonDeserializer<OdooSale> {
    @Override
    public OdooSale deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        return null;
    }
}
