package com.example.tlrabbitmq.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 14:38
 */
@Configuration
public class HelloConfig {
    @Bean
    public Queue queue(){return new Queue("helloQueue");}
}
