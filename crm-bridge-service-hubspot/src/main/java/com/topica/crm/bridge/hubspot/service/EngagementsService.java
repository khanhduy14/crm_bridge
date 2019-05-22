package com.topica.crm.bridge.hubspot.service;

import com.topica.crm.bridge.hubspot.exception.HSExeption;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EngagementsService {
  @Autowired HttpService httpService;

  private String offset = "";

  public JSONObject getAssociatedRequest(String objectType, String objectId) throws HSExeption {
    String url = "/engagements/v1/engagements/associated/" + objectType + "/" + objectId;
    return getAssociated(url);
  }

  private JSONObject getAssociated(String url) throws HSExeption {
    JSONObject temp = new JSONObject();
    try {
      boolean willQuery = true;
      while (willQuery) {
        JSONObject jsonObject = (JSONObject) httpService.getRequest(url, offset);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        jsonArray.forEach(i -> temp.append("results", i));
        if (jsonObject.get("hasMore").equals(true)) {
          offset = jsonObject.get("offset").toString();
        } else {
          willQuery = false;
        }
      }
      return temp;
    } catch (HSExeption e) {
      if (e.getMessage().equals("Not Found")) {
        return null;
      } else {
        throw e;
      }
    }
  }
}
