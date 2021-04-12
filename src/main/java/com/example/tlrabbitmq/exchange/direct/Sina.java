package com.example.tlrabbitmq.exchange.direct;

import com.example.tlrabbitmq.utils.RabbitConstant;
import com.example.tlrabbitmq.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 9:30
 */
public class Sina {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitConstant.QUEUE_SINA,false,false,false,null);
        channel.queueBind(RabbitConstant.QUEUE_SINA,RabbitConstant.EXCHANGE_WEATHER_ROUTING,"US.Cal.Lsa.20210411");
        channel.queueBind(RabbitConstant.QUEUE_SINA,RabbitConstant.EXCHANGE_WEATHER_ROUTING,"China.Shanxi.Xian.20210411");
        channel.basicQos(1);

        channel.basicConsume(RabbitConstant.QUEUE_SINA,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象数据:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
