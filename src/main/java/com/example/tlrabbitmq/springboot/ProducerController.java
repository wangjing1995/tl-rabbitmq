package com.example.tlrabbitmq.springboot;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 15:03
 */
@RestController
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "hello发送接口",notes = "直接发送到队列")
    @GetMapping(value = "/helloSend")
    public Object helloSend(String message) throws UnsupportedEncodingException, AmqpException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        rabbitTemplate.send("helloQueue",new Message(message.getBytes("UTF-8"),messageProperties));
        return "mesage sended : "+message;
    }

    //工作队列模式
    @ApiOperation(value = "workqueue发送接口",notes = "发送到所有监听该队列的消费")
    @GetMapping(value = "workqueueSend")
    public Object workQueueSend(String message) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        for (int i=1;i<=10;i++){
            rabbitTemplate.send("work_sb_mq_q",new Message(message.getBytes("UTF-8"),messageProperties));
        }
        return "meaage sended : "+message;
    }

    @ApiOperation(value = "fanout发送接口",notes = "发送到fanoutExchange,消息将往该exchange下的所有queue转发")
    @GetMapping(value = "/fanoutSend")
    public Object  fanoutSend(String message) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send("fanoutExchange","",new Message(message.getBytes("UTF-8"),messageProperties));
        return "message sended : "+message;
    }

    @ApiOperation(value = "direct发送接口",notes = "发送到directExchange.exchange转发消息时，会往routingKey匹配的queue发送")
    @GetMapping(value = "/directSend")
    public  Object routingSend(String routingKey,String message) throws UnsupportedEncodingException {
        if (routingKey == null) {
            routingKey = "china.changsha";
        }
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //fanout模式只往exchange里发送消息，分发到exchange下的所有queue
        rabbitTemplate.send("directExchange",routingKey,new Message(message.getBytes("UTF-8"),messageProperties));
        return "message sended : routingKey >"+routingKey+";message > "+message;
    }

    @ApiOperation(value = "topic发送接口",notes = "发送到topicExchange。exchange转发消息时，会往routingKey匹配的queue发送，*代表一个单词，#代表0个或多个单词。")
    @GetMapping(value = "/topicSend")
    public Object topicSend(String routingKey,String message) throws UnsupportedEncodingException {
        if (routingKey == null) {
            routingKey = "changsha.kf";
        }
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.send("topicExchange",routingKey,new Message(message.getBytes("UTF-8"),messageProperties));
        return "message sended : routingKey > "+routingKey+";message > "+message;
    }
}
