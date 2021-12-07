package com.lly.mq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    /**
     * 怎么处理数据，一帧为单位。rpc 查一下
     * @throws Exception
     */

    public static void getMessage() throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
//        channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, "UTF-8"));
                // Envelope  消息唯一标识过,确认消息消费掉
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        // 消息预取
        channel.basicQos(1);
        //开始消费，autoAck ： true ，消费完以后，队列中就会删除掉
        channel.basicConsume("testQueue",false, deliverCallback);
    }

    public static void main(String[] args) throws Exception {
        getMessage();
    }
}
