package com.example.tlrabbitmq.workqueue;

import com.example.tlrabbitmq.utils.RabbitConstant;
import com.example.tlrabbitmq.utils.RabbitUtils;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月10日 14:57
 */
public class OrderSystem {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_SMS,false,false,false,null);
        for (int i=1;i<=20;i++){
            SMS sms = new SMS("乘客"+i,"135137"+i,"您的车票已预定成功");
            String jsonSMS = new Gson().toJson(sms);
            channel.basicPublish("",RabbitConstant.QUEUE_SMS,null,jsonSMS.getBytes("UTF-8"));

        }
        System.out.println("发送数据成功");
        channel.close();
        connection.close();
    }
}
