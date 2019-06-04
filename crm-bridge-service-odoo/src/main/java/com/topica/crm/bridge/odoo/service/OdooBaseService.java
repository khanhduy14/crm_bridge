package com.topica.crm.bridge.odoo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static java.util.Arrays.asList;

@Slf4j
@Service
public class OdooBaseService {

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

  private int getUid() {
    try {
      List<Object> configList = configList();
      XmlRpcClient client = new XmlRpcClient();
      XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
      common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url)));
      int uid = (int) client.execute(common_config, "authenticate", configList);
      return uid;
    } catch (MalformedURLException | XmlRpcException e) {
      log.info(e.toString());
      return Integer.parseInt(null);
    }
  }

  private XmlRpcClient getModel() {
    try {
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
    } catch (MalformedURLException e) {
      log.info(e.toString());
      return null;
    }
  }

  public List<Object> getObject(String modelType, String mode, List list) {
    try {
      int uid = getUid();
      XmlRpcClient models = getModel();
      List<Object> objects =
          asList(
              (Object[])
                  models.execute(
                      "execute_kw",
                      asList(db, uid, password, modelType, mode, list, Collections.emptyList())));

      return objects;
    } catch (XmlRpcException e) {
      log.info(e.toString());
      return null;
    }
  }

  public void createObject(HashMap map, String modelType) {
    try {
      int uid = getUid();
      XmlRpcClient models = getModel();
      models.execute(
          "execute_kw",
          asList(
              db,
              uid,
              password,
              modelType,
              "create",
              asList(
                  new HashMap() {
                    {
                      putAll(map);
                    }
                  })));
    } catch (XmlRpcException e) {
      log.info(e.toString());
    }
  }

  public void updateObject(HashMap map, Integer id) {
    try {
      int uid = getUid();
      XmlRpcClient models = getModel();
      models.execute(
          "execute_kw",
          asList(
              db,
              uid,
              password,
              "res.partner",
              "write",
              asList(
                  asList(id),
                  new HashMap() {
                    {
                      putAll(map);
                    }
                  })));
    } catch (XmlRpcException e) {
      log.info(e.toString());
    }
  }
}
