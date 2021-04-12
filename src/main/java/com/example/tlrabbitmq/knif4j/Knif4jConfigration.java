package com.example.tlrabbitmq.knif4j;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月12日 15:42
 */
@Configuration
@EnableSwagger2WebMvc
public class Knif4jConfigration {

    public Docket createResApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.tlrabbitmq"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring Boot中使用spring-boot-starter-amqp集成rabbitmq")
                .description("测试SpringBoot整合进行各种工作模式信息的发送")
/*
	                .termsOfServiceUrl("https://www.jianshu.com/p/c79f6a14f6c9")
*/
                .contact("roykingw")
                .version("1.0")
                .build();
    }
}
