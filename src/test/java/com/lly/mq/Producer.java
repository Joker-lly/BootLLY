package com.lly.mq;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;

public class Producer {

    public static void getMessage() throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // name ,持久化，是否单一队列，自动删除，map
       // channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
        // 声明一个交换机
        channel.exchangeDeclare("exchangeTest", ExchangeTypes.FANOUT);
        channel.queueBind(ConnectionUtil.QUEUE_NAME,"exchangeTest","");
        // 默认交换机
        channel.basicPublish("",ConnectionUtil.QUEUE_NAME,null,"word".getBytes());
        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        getMessage();
    }
}
