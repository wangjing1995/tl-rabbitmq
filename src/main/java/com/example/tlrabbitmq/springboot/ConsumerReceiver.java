package com.example.tlrabbitmq.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 16:42
 */
@Component
public class ConsumerReceiver {
    //直连的多个消费者，会分到其中一个消费者进行消费，类似task模式
    //通过注入RabbitContainerFactory对象，来设置一些属性，相当于task里的channel,basicQos
    @RabbitListener(queues = "helloQueue")
    public void helloReceiver(String message){
        System.out.println("hello模式received message : "+message);
    }

    //工作队列模式
    @RabbitListener(queues="work_sb_mq_q")
    public void wordQueueReceiveq1(String message) {

        System.out.println("工作队列模式1 received message : " +message);
    }

    @RabbitListener(queues="work_sb_mq_q")
    public void wordQueueReceiveq2(String message) {

        System.out.println("工作队列模式2 received message : " +message);
    }


    //pub/sub模式进行消息监听
    @RabbitListener(queues="fanout.q1")
    public void fanoutReceiveq1(String message) {

        System.out.println("发布订阅模式1received message : " +message);
    }
    @RabbitListener(queues="fanout.q2")
    public void fanoutReceiveq2(String message) {

        System.out.println("发布订阅模式2 received message : " +message);
    }


    //Routing路由模式
    @RabbitListener(queues="direct_sb_mq_q1")
    public void routingReceiveq1(String message) {

        System.out.println("Routing路由模式routingReceiveq11111 received message : " +message);
    }

    @RabbitListener(queues="direct_sb_mq_q2")
    public void routingReceiveq2(String message) {

        System.out.println("Routing路由模式routingReceiveq22222 received message : " +message);
    }


    //topic 模式
    //注意这个模式会有优先匹配原则。例如发送routingKey=hunan.IT,那匹配到hunan.*(hunan.IT,hunan.eco),之后就不会再去匹配*.ITd
    @RabbitListener(queues="topic_sb_mq_q1")
    public void topicReceiveq1(String message) {
        System.out.println("Topic模式 topic_sb_mq_q1 received message : " +message);
    }

    @RabbitListener(queues="topic_sb_mq_q2")
    public void topicReceiveq2(String message) {
        System.out.println("Topic模式 topic_sb_mq_q2 received  message : " +message);
    }


}
