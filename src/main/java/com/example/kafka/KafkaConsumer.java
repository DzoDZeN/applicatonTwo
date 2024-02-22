package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "test", groupId = "myConsumer")
    public void listener(String message){
        System.out.println(message);
    }
}
