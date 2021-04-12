package com.example.tlrabbitmq.confirm;

import com.example.tlrabbitmq.utils.RabbitConstant;
import com.example.tlrabbitmq.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 10:47
 */
public class Sina {
    public static void main(String[] args) throws Exception{

        Connection connection = RabbitUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_SINA,false,false,false,null);
        channel.queueBind(RabbitConstant.QUEUE_SINA,RabbitConstant.EXCHANGE_WEATHER_TOPIC,"us.#");
        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SINA,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象数据："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
