package com.yxz.dubbo03;

import com.yxz.dubbo01.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Desc TODO
 * @Date 2025-07-09
 * @Created by Yolo
 */
public class ConsumerMain {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-consumer.xml");

        UserService userService = (UserService) applicationContext.getBean("userService");

        System.out.println(userService.login("admin", "123456"));

        System.in.read();
    }

}
