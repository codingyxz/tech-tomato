package com.yxz.dubbo04;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubbo
public class Dubbo04BootProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Dubbo04BootProviderApplication.class, args);


        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

    }

}
