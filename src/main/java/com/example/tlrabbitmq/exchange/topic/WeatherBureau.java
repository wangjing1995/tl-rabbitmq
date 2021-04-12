package com.example.tlrabbitmq.exchange.topic;

import com.example.tlrabbitmq.utils.RabbitConstant;
import com.example.tlrabbitmq.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 10:09
 */
public class WeatherBureau {
    public static void main(String[] args) throws Exception{

        Map area = new LinkedHashMap<String, String>();
        area.put("China.Henan.Nanyang.20210412","中国河南南阳20210412天气数据");
        area.put("China.Henan.Nanyang.20210411","中国河南南阳20210411天气数据");
        area.put("China.Henan.Kaifeng.20210412","中国河南开封20210412天气数据");
        area.put("China.Henan.Kaifeng.20210411","中国河南开封20210411天气数据");
        area.put("China.Shanxi.Xian.20210412","中国陕西西安20210412天气数据");
        area.put("China.Shanxi.Xian.20210411","中国陕西西安20210411天气数据");


        area.put("US.Cal.Lsa.20210412","美国加州洛杉矶20210412天气数据");
        area.put("US.Cal.Lsa.20210411","美国加州洛杉矶20210411天气数据");

        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_TOPIC,"topic",true);

        Iterator<Map.Entry<String, String>> iterator = area.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> me =iterator.next();
            //第一个参数交换机名字   第二个参数作为 消息的routing key
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_TOPIC,me.getKey(),null,me.getValue().getBytes("UTF-8"));

        }

        System.out.println("发送成功");

        channel.close();
        connection.close();
    }
}
