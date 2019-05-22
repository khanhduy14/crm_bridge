package com.topica.crm.bridge.hubspot.service;

import com.google.common.base.Strings;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.topica.crm.bridge.hubspot.exception.HSExeption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

  @Value("${hubspot.ApiBase}")
  private String apiBase;

  @Value("${hubspot.ApiKey}")
  private String apiKey;

  public Object getRequest(String url, String offset) throws HSExeption {
    try {
      HttpResponse<JsonNode> resp =
          Unirest.get(apiBase + url)
              .queryString("hapikey", apiKey)
              .queryString("vidOffset", offset)
              .queryString("offset", offset)
              .queryString("count", "100")
              .queryString("includePropertyVersions", "true")
              .asJson();

      return checkResponse(resp);
    } catch (UnirestException e) {
      throw new HSExeption("Can not get data\n URL:" + url, e);
    }
  }

  public Object postRequest(String url, String properties) throws HSExeption {
    return postRequest(url, properties, "application/json");
  }

  public Object postRequest(String url, String properties, String contentType) throws HSExeption {
    if (Strings.isNullOrEmpty(contentType)) {
      contentType = "application/json";
    }
    try {
      HttpResponse<JsonNode> resp =
          Unirest.post(apiBase + url)
              .queryString("hapikey", apiKey)
              .header("accept", "application/json")
              .header("Content-Type", contentType)
              .body(properties)
              .asJson();

      return checkResponse(resp);
    } catch (UnirestException e) {
      throw new HSExeption("Cannot make a request: \n" + properties, e);
    }
  }

  public Object deleteRequest(String url) throws HSExeption {
    try {
      HttpResponse<JsonNode> resp =
          Unirest.delete(apiBase + url).queryString("hapikey", apiKey).asJson();

      return checkResponse(resp);
    } catch (UnirestException e) {
      throw new HSExeption("Cannot make delete request: \n URL: " + url, e);
    }
  }

  public Object putRequest(String url, String properties) throws HSExeption {
    try {
      HttpResponse<JsonNode> resp =
          Unirest.put(apiBase + url)
              .header("accept", "application/json")
              .header("Content-Type", "application/json")
              .queryString("hapikey", apiKey)
              .body(properties)
              .asJson();

      return checkResponse(resp);
    } catch (UnirestException e) {
      throw new HSExeption("Can not get data", e);
    }
  }

  private Object checkResponse(HttpResponse<JsonNode> resp) throws HSExeption {
    if (204 != resp.getStatus() && 200 != resp.getStatus() && 202 != resp.getStatus()) {
      String message = null;
      try {
        message =
            (resp.getStatus() == 404)
                ? resp.getStatusText()
                : resp.getBody().getObject().getString("message");
      } catch (Exception e) {
      }

      if (!Strings.isNullOrEmpty(message)) {
        throw new HSExeption(message, resp.getStatus());
      } else {
        throw new HSExeption(resp.getStatusText(), resp.getStatus());
      }
    } else {
      if (resp.getBody() != null) {
        return resp.getBody().isArray() ? resp.getBody().getArray() : resp.getBody().getObject();
      } else {
        return null;
      }
    }
  }
}
