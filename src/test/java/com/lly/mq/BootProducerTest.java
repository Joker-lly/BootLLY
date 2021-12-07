package com.lly.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class BootProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testProducer(){

        HashMap<String, String> map = new HashMap<>();
        map.put("user", "root");
        map.put("password", "root");

        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting1212", "hello world");
       // rabbitTemplate.convertAndSend("directExchange", "direct.key", "hello");
    }
}
