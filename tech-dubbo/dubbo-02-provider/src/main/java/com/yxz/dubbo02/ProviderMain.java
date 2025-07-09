package com.yxz.dubbo02;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @Desc Provider 启动类
 * @Date 2025-07-09
 * @Created by Yolo
 */
public class ProviderMain {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-provider.xml");

        applicationContext.start();

        new CountDownLatch(1).await();;

    }

}
