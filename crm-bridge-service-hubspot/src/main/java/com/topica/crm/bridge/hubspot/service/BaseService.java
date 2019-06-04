package com.topica.crm.bridge.hubspot.service;

import com.github.icovn.http.client.HttpMethod;
import com.github.icovn.http.client.HttpProperties;
import com.github.icovn.http.client.HttpResult;
import com.github.icovn.http.client.service.DefaultHttpClient;
import com.github.icovn.http.client.service.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseService {

  @Value("${hubspot.ApiBase}")
  protected String apiBase;

  @Value("${hubspot.ApiKey}")
  protected String apiKey;

  protected HttpClient httpClient;
  protected Map<String, String> headers;

  public BaseService() {
    httpClient = new DefaultHttpClient(new HttpProperties());
    headers = new HashMap<>();
    headers.put("Accept", "application/json");
  }

  protected HttpResult getObject(String uriTemplate, String id) {
    String url = uriTemplate.replace("{KEY}", apiKey).replace("{ID}", id);
    HttpResult httpResult = httpClient.query(HttpMethod.GET, apiBase + url, false, headers);
    log.debug(
        "(getObject)httpResult: {}, body: {}", httpResult.getStatusCode(), httpResult.getBody());
    return httpResult;
  }

  protected HttpResult getListObject(String uriTemplate, long offset) {
    String url = uriTemplate.replace("{KEY}", apiKey).replace("{OFFSET}", Long.toString(offset));
    HttpResult httpResult = httpClient.query(HttpMethod.GET, apiBase + url, false, headers);
    log.debug("(getListObject)httpResult: {}", httpResult.getStatusCode());
    return httpResult;
  }

  protected HttpResult getChangelogObject(String uriTemplate, String objectType, String timestamp) {
    String url =
        uriTemplate
            .replace("{KEY}", apiKey)
            .replace("{TYPE}", objectType)
            .replace("{TIMESTAMP}", timestamp);
    HttpResult httpResult = httpClient.query(HttpMethod.GET, apiBase + url, false, headers);
    log.debug(
        "(getObject)httpResult: {}, body: {}", httpResult.getStatusCode(), httpResult.getBody());
    return httpResult;
  }

  protected HttpResult getObjectType(String uriTemplate, String objectType) {
    String url =
            uriTemplate
                    .replace("{KEY}", apiKey)
                    .replace("{TYPE}", objectType);
    HttpResult httpResult = httpClient.query(HttpMethod.GET, apiBase + url, false, headers);
    log.debug(
            "(getObject)httpResult: {}, body: {}", httpResult.getStatusCode(), httpResult.getBody());
    return httpResult;
  }
}
