package com.example.tlrabbitmq.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 13:30
 */
@Configuration
public class DirectConfig {
    //声明队列
    @Bean
    public Queue directQ1(){return new Queue("direct_sb_mq_q1");}
    @Bean
    public Queue directQ2(){return new Queue("direct_sb_mq_q2");}

    //声明交换机
    @Bean
    public DirectExchange directExchange(){return new DirectExchange("directExchange");}

    //声明binding,需要声明一个routingKey
    @Bean
    public Binding bindingDirect1(){
        return BindingBuilder.bind(directQ1()).to(directExchange()).with("china.changsha");
    }

    @Bean
    public Binding bindingDirect2(){
        return BindingBuilder.bind(directQ2()).to(directExchange()).with("china.beijing");
    }
}
