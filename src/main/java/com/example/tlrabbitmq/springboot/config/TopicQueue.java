package com.example.tlrabbitmq.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 14:40
 */
@Configuration
public class TopicQueue {
    //声明队列
    @Bean
    public Queue topicQ1(){return new Queue("topic_sb_mq_q1");}
    @Bean
    public Queue topicQ2(){return new Queue("topic_sb_mq_q2");}

    //声明交换机
    @Bean
    public TopicExchange topicExchange(){ return new TopicExchange("topicExchange"); }

    //声明绑定，需要声明一个routingKey
    @Bean
    public Binding bindTopicHebei1(){
        return BindingBuilder.bind(topicQ1()).to(topicExchange()).with("changsha.*");
    }

    @Bean
    public Binding bindTopicHebei2(){
        return BindingBuilder.bind(topicQ2()).to(topicExchange()).with("#.beijing");
    }
}
