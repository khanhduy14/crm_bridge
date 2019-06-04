package com.topica.crm.bridge.processor.es;

import static com.github.icovn.util.ExceptionUtil.getFullStackTrace;

import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.xcontent.XContentType;

@Slf4j
public class SaveObjectToElasticSearchProcessor implements BaseProcessor {

  private String index;
  private ElasticSearchTransportClient transport;

  public SaveObjectToElasticSearchProcessor(String index, ElasticSearchTransportClient transport) {
    this.index = index;
    this.transport = transport;
  }

  @Override
  public BaseObject process(BaseObject input) {
    log.info("(process)id: {}, input: {}", input.getId(), MapperUtil.toJson(input));
    try {
      transport
          .getClient()
          .prepareIndex(index, "_doc", input.getId())
          .setSource(MapperUtil.toJson(input), XContentType.JSON)
          .get();
    } catch (UnknownHostException ex) {
      log.error("(process)index: {}, input: {}, ex: {}", index, input, getFullStackTrace(ex, true));
      throw new RuntimeException(ex.getMessage());
    }
    return input;
  }
}
