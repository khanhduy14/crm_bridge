package com.topica.crm.bridge.odoo.service;

import static java.util.Arrays.asList;

import com.topica.crm.bridge.core.entity.odoo.OdooContact;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OdooService {

  @Value("${odoo.URL}")
  private String url;

  @Value("${odoo.DBName}")
  private String db;

  @Value("${odoo.Username}")
  private String username;

  @Value("${odoo.Password}")
  private String password;

  private List<Object> configList() {
    List<Object> objects = new ArrayList<>();
    Map paramMap = new HashMap();

    objects.add(db);
    objects.add(username);
    objects.add(password);
    objects.add(paramMap);

    return objects;
  }

  private int getUid() throws MalformedURLException, XmlRpcException {
    List<Object> configList = configList();
    XmlRpcClient client = new XmlRpcClient();
    XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
    common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url)));
    int uid = (int) client.execute(common_config, "authenticate", configList);
    return uid;
  }

  private XmlRpcClient getModel() throws MalformedURLException {
    XmlRpcClient models =
        new XmlRpcClient() {
          {
            setConfig(
                new XmlRpcClientConfigImpl() {
                  {
                    setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
                  }
                });
          }
        };
    return models;
  }

  public List<Object> getContact() {
    try {
      int uid = getUid();
      XmlRpcClient models = getModel();

      List<Object> objects =
          asList(
              (Object[])
                  models.execute(
                      "execute_kw",
                      asList(
                          db,
                          uid,
                          password,
                          "res.partner",
                          "search_read",
                          asList(asList(asList("customer", "=", true))),
                          new HashMap() {
                            {
                              put(
                                  "fields",
                                  asList(
                                      "name",
                                      "comment",
                                      "email",
                                      "phone",
                                      "contact_address",
                                      "street",
                                      "city"));
                            }
                          })));

      return objects;
    } catch (XmlRpcException | MalformedURLException e) {
      log.info(e.toString());
      return null;
    }
  }

  public List<OdooContact> mapToModel(List<Object> objects) {
    List<OdooContact> odooContacts = new LinkedList<>();
    objects.forEach(
        o -> {
          OdooContact odooContact = new OdooContact((HashMap<String, Object>) o);
          odooContacts.add(odooContact);
        });
    return odooContacts;
  }
}
