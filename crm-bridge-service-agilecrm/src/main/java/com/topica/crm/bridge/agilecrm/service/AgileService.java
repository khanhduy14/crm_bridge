package com.topica.crm.bridge.agilecrm.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AgileService {
  private static String apiBaseAgile = "https://duykieu.agilecrm.com/dev";
  private static String email = "duykk@topica.edu.vn";
  private static String apiKey = "30vnrepecfdrmsm0obt3gjotd2";

  private WebResource getResource() {
    WebResource resource;

    ClientConfig config = new DefaultClientConfig();

    Client client = Client.create(config);
    client.addFilter(new LoggingFilter(System.out));

    resource = client.resource(apiBaseAgile);
    resource.addFilter(new HTTPBasicAuthFilter(email, apiKey));
    return resource;
  }

  public JSONObject getContactAgile(String contactId) {
    System.out.println("Getting contact by id : ");

    WebResource resource = getResource();
    ClientResponse clientResponse =
        resource
            .path("/api/contacts/" + contactId)
            .accept(MediaType.APPLICATION_JSON)
            .get(ClientResponse.class);

    return new JSONObject(clientResponse.getEntity(String.class));
  }

  public JSONArray getContacts(String page_size, String cursor) throws Exception {

    WebResource resource = getResource();
    System.out.println("Getting contacts by page and cursor : ");
    MultivaluedMap<String, String> params = new MultivaluedMapImpl();

    if (page_size == null || page_size.isEmpty()) page_size = "20";

    params.add("page_size", page_size);
    params.add("global_sort_key", "-created_time");

    if (!cursor.isEmpty() && !cursor.equalsIgnoreCase("first_page")) params.add("cursor", cursor);

    ClientResponse clientResponse =
        resource
            .path("/api/contacts/")
            .queryParams(params)
            .accept(MediaType.APPLICATION_JSON)
            .get(ClientResponse.class);

    return new JSONArray(clientResponse.getEntity(String.class));
  }
}
