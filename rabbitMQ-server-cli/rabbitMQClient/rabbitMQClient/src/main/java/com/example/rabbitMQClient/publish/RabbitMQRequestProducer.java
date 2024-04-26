package com.example.rabbitMQClient.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQRequestProducer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQRequestProducer.class);
    @Value("${rabbitmq.request.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.request.routing.key}")
    private String routingKey;
    private RabbitTemplate rabbitTemplate;

    public RabbitMQRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendRequest(String message){
        log.info(String.format("Sending Request{}->%s",message));
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }
}
