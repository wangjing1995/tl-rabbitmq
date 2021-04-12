package com.example.tlrabbitmq.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 14:24
 */
@Configuration
public class FanoutConfig {
    //声明队列
    @Bean
    public Queue fanoutQ1(){return new Queue("fanout.q1");}
    @Bean
    public Queue fanoutQ2(){return new Queue("fanout.q2");}

    //声明交换机
    @Bean
    public FanoutExchange fanoutExchange(){return new FanoutExchange("fanoutExchange");}

    //声明Binding,exchange与queue的绑定关系
    @Bean
    public Binding bindQ1(){
        return BindingBuilder.bind(fanoutQ1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindQ2(){
        return BindingBuilder.bind(fanoutQ2()).to(fanoutExchange());
    }
}
