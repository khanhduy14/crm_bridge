package com.topica.crm.bridge.hubspot.service;

import com.topica.crm.bridge.hubspot.exception.HSExeption;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ComponentScan("com.topica.crm.bridge.core.service")
public class ContactService {

  @Autowired HttpService httpService;

  @Autowired EngagementsService engagementsService;

  private String offset = "";

  @Value("${hubspot.ApiBase}")
  private String apiBase;

  @Value("${hubspot.ApiKey}")
  private String apiKey;

  public JSONObject getByIDJSON(String id) throws HSExeption {
    String url = "/contacts/v1/contact/vid/" + id + "/profile";
    return (JSONObject) httpService.getRequest(url, "");
  }

  public JSONObject getByEmailJSON(String email) throws HSExeption {
    String url = "/contacts/v1/contact/email/" + email + "/profile";
    return (JSONObject) httpService.getRequest(url, "");
  }

  public List<JSONObject> getAllJSON() throws HSExeption {
    String url = "/contacts/v1/lists/all/contacts/all";
    List<JSONObject> result = new ArrayList<>();
    try {

      boolean willQuery = true;
      while (willQuery) {
        JSONObject jsonObject = (JSONObject) httpService.getRequest(url, offset);
        //        log.debug("(getContact){}", jsonObject.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("contacts");
        for (int i = 0; i < jsonArray.length(); i++) {
          JSONObject temp = jsonArray.getJSONObject(i);
          JSONObject temp1 = getByIDJSON(String.valueOf(temp.getLong("canonical-vid")));
          log.info("(getContact){}", temp1.toString());
          result.add(temp1);
        }

        if (jsonObject.get("has-more").equals(true)) {
          offset = jsonObject.get("vid-offset").toString();
        } else {
          willQuery = false;
        }
      }
      return result;
    } catch (HSExeption e) {
      if (e.getMessage().equals("Not Found")) {
        return null;
      } else {
        throw e;
      }
    }
  }
}
