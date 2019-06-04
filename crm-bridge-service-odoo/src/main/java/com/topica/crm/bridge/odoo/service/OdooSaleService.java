package com.topica.crm.bridge.odoo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.topica.crm.bridge.odoo.entity.sale.OdooSale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class OdooSaleService extends OdooBaseService {

    @Autowired ConvertObject convertObject;

  public List<OdooSale> getSale() {
    List<Object> objects = getObject("sale.order", "search_read", Collections.emptyList());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    List<OdooSale> odooSales = new LinkedList<>();
    objects.forEach(
        object -> {
          JsonNode jsonObject = convertObject.convertObject(object, objectMapper);
          try {
            OdooSale odooSale = objectMapper.treeToValue(jsonObject, OdooSale.class);
//            odooSale.setWriteUid(jsonObject.get("write_uid").get(0).asInt());
//            odooSale.setPartnerId(jsonObject.get("partner_id").get(0).asInt());
//            odooSale.setCompanyId(jsonObject.get("company_id").get(0).asInt());
//            odooSale.setTeamId(jsonObject.get("team_id").get(0).asInt());
//            odooSale.setWarehouseId(setProperty("warehouse_id", jsonObject));
//            odooSale.setCurrencyId(setProperty("currency_id", jsonObject));
//            odooSale.setPartnerShippingId(setProperty("partner_shipping_id", jsonObject));
//            odooSale.setPartnerInvoiceId(setProperty("partner_invoice_id", jsonObject));
//            odooSale.setUserId(setProperty("user_id", jsonObject));
//            odooSale.setCreateUid(setProperty("create_uid", jsonObject));
//            odooSale.setPricelistId(setProperty("pricelist_id", jsonObject));
//            odooSale.setAnalyticAccountId(setProperty("analytic_account_id", jsonObject));
//            odooSale.setProcurementGroupId(setProperty("procurement_group_id", jsonObject));
            odooSales.add(odooSale);
          } catch (JsonProcessingException e) {
            log.info(e.getMessage());
          }
        });
    return odooSales;
  }

  private Integer setProperty(String fieldName, JsonNode jsonNode) {
      try {
          return jsonNode.get(fieldName).get(0).asInt();
      } catch (NullPointerException e) {
          log.error(e.getMessage());
          return null;
      }
  }
}
