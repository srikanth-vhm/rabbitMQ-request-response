package com.example.rabbitMQClient.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQRequestConfig {

    //creating response queue
    @Value("${rabbitmq.request.queue.name}")
    private String queue;
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
//creating exchange
    @Value("${rabbitmq.request.exchange.name}")
    private String exchange;
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    //Binding queue to exchange with routing key

    @Value("${rabbitmq.request.routing.key}")
    private String routingKey;
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
    //connection factory
    //rabbit templet
    //rabbit admin
}
