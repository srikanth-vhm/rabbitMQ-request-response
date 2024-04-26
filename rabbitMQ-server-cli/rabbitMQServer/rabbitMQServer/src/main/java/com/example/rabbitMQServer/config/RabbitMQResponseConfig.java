package com.example.rabbitMQServer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQResponseConfig {
    private String message="Response from Server";
    //config response queue
    @Value("${rabbitmq.response.queue.name}")
    private String queue;


    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //creating exchange
    @Value("${rabbitmq.response.exchange.name}")
    private String exchange;
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    //Binding queue to exchange with routing key

    @Value("${rabbitmq.response.routing.key}")
    private String routingKey;
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
  /*  @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
rabbitTemplate.convertAndSend(exchange,routingKey,message);

return rabbitTemplate;
    }*/
}
