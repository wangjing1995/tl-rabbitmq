package com.example.tlrabbitmq.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 14:59
 */
@Configuration
public class WorkConfig {
    @Bean
    public Queue workQ1(){return new Queue("work_sb_mq_q");}
}
