package com.topica.crm.bridge.processor.queue;

import com.topica.crm.bridge.core.entity.BaseObject;
import com.topica.crm.bridge.core.processor.BaseProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class PushObjectToRabbitmqProcessor implements BaseProcessor {

  private final String queue;
  private final RabbitTemplate rabbitTemplate;

  public PushObjectToRabbitmqProcessor(String queue, RabbitTemplate rabbitTemplate) {
    this.queue = queue;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public BaseObject process(BaseObject input) {
    rabbitTemplate.convertAndSend(queue, input);
    return input;
  }
}
