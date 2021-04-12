package com.example.tlrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
@Slf4j
@SpringBootApplication
public class TlRabbitmqApplication  {


    public static void main(String[] args) throws UnknownHostException {

        SpringApplication.run(TlRabbitmqApplication.class, args);

        log.info("Swagger文档: \thttp://" + "localhost" + ":" + "8080"  + "/doc.html\n");
    }

}
