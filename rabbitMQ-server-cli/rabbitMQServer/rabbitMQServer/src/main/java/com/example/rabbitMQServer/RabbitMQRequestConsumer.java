package com.example.rabbitMQServer;

import com.example.rabbitMQServer.dto.User;
import com.example.rabbitMQServer.pubisher.RabbitMQResponseProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQRequestConsumer {
private RabbitMQResponseProducer rabbitMQResponseProducer;

    public RabbitMQRequestConsumer(RabbitMQResponseProducer rabbitMQResponseProducer) {
        this.rabbitMQResponseProducer = rabbitMQResponseProducer;
    }

    private static final Logger log = LoggerFactory.getLogger(RabbitMQRequestConsumer.class);
@RabbitListener(queues = {"${rabbitmq.request.queue.name}"})
    public void consume(String message){
    log.info(String.format("Received message request{}->%S",message));
   // User user= new User(11,message,"abc");

    rabbitMQResponseProducer.sendResponse(message);

}
}
