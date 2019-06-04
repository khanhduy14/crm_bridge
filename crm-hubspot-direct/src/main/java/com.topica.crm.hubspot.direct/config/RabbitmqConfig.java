package com.topica.crm.hubspot.direct.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

  @Value("${rabbitmq.message.ttl}")
  private int messageTtl;

  @Value("${rabbitmq.concurrent.consumers}")
  private int concurrentConsumers;

  @Value("${rabbitmq.max.concurrent.consumers}")
  private int maxConcurrentConsumers;

  @Value("${rabbitmq.prefetch}")
  private int prefetch;

  @Value("${application.queue.contact}")
  private String queueContact;

  @Value("${application.queue.deal}")
  private String queueDeal;

  @Value("${application.queue.ticket}")
  private String queueTicket;

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(new Jackson2JsonMessageConverter());
    return template;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConcurrentConsumers(concurrentConsumers);
    factory.setMaxConcurrentConsumers(maxConcurrentConsumers);
    factory.setPrefetchCount(prefetch);
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(new Jackson2JsonMessageConverter());

    return factory;
  }

  @Bean
  public Queue queueContact() {
    return new Queue(queueContact);
  }

  @Bean
  public Queue queueDeal() {
    return new Queue(queueDeal);
  }

  @Bean
  public Queue queueTicket() {
    return new Queue(queueTicket);
  }

  private Queue initDelayQueue(String mainQueue, String delayQueue) {
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("x-message-ttl", messageTtl);
    arguments.put("x-dead-letter-exchange", "");
    arguments.put("x-dead-letter-routing-key", mainQueue);
    return new Queue(delayQueue, true, false, false, arguments);
  }
}
