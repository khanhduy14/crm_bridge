package com.topica.crm.bridge.processor.es;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchTransportClient {

  private String elasticSearchHost;

  private TransportClient client = null;

  public ElasticSearchTransportClient(String elasticSearchHost) {
    this.elasticSearchHost = elasticSearchHost;
  }

  public TransportClient getClient() throws UnknownHostException {
    if (client == null) {
      client =
          new PreBuiltTransportClient(Settings.EMPTY)
              .addTransportAddress(
                  new TransportAddress(InetAddress.getByName(elasticSearchHost), 9300));
    }

    return client;
  }
}
