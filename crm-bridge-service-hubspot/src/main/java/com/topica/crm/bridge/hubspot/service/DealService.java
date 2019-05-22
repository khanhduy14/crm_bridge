package com.topica.crm.bridge.hubspot.service;

import com.topica.crm.bridge.hubspot.exception.HSExeption;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DealService {

  @Autowired HttpService httpService;

  @Autowired EngagementsService engagementsService;

  private String offset = "";

  public List<JSONObject> getAll() throws HSExeption {
    String url = "/deals/v1/deal/paged";
    List<JSONObject> result = new ArrayList<>();
    try {
      boolean willQuery = true;
      while (willQuery) {
        JSONObject jsonObject = (JSONObject) httpService.getRequest(url, offset);
        //        log.debug("(getDeals){}", jsonObject.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("deals");
        for (int i = 0; i < jsonArray.length(); i++) {
          JSONObject temp = jsonArray.getJSONObject(i);
          JSONObject temp1 = getByDealId(String.valueOf(temp.getLong("dealId")));
          JSONObject engagements =
              engagementsService.getAssociatedRequest(
                  "deal", String.valueOf(temp.getLong("dealId")));
          temp1.append("engagements", engagements);
          log.info("(getDeals){}", temp1.toString());
          result.add(temp1);
        }
        if (jsonObject.get("hasMore").equals(true)) {
          offset = jsonObject.get("offset").toString();
        } else {
          willQuery = false;
        }
      }
      return result;
    } catch (HSExeption e) {
      if (e.getMessage().equals("Not Found")) {
        return result;
      } else {
        throw e;
      }
    }
  }

  private JSONObject getByDealId(String id) throws HSExeption {
    String url = "/deals/v1/deal/" + id;
    return (JSONObject) httpService.getRequest(url, "");
  }
}
