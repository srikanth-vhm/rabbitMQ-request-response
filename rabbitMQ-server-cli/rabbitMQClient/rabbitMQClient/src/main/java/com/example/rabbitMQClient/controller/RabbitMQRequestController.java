package com.example.rabbitMQClient.controller;

import com.example.rabbitMQClient.publish.RabbitMQRequestProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RabbitMQRequestController {
    RabbitMQRequestProducer rabbitMQRequestProducer;

    public RabbitMQRequestController(RabbitMQRequestProducer rabbitMQRequestProducer) {
        this.rabbitMQRequestProducer = rabbitMQRequestProducer;
    }
    //http://localhost:8081/api/publish?message=demo
@GetMapping("/publish")
    public ResponseEntity<String>sendRequest(@RequestParam("message") String message){
        rabbitMQRequestProducer.sendRequest(message);
        return ResponseEntity.ok("Messege request sent to rabbitMQ...");

    }
}
