package com.example.tlrabbitmq.exchange.fanout.pubsub;

import com.example.tlrabbitmq.utils.RabbitConstant;
import com.example.tlrabbitmq.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月10日 16:40
 */
public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        String input = new Scanner(System.in).next();
        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER,"fanout",true);
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"",null,input.getBytes("UTF-8"));

        channel.close();
        connection.close();
    }
}
