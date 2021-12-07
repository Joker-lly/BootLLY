package com.lly.business.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component

public class DirectReceiver {

    @RabbitListener(queues = "TestDirectQueue")
    public void process(String testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());

     }
}
