package com.yxz.java.jvm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Desc TODO
 * @Date 2025-08-01
 * @Created by Yolo
 */
public class CPUTest {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                while (true) {
                    System.out.println(System.currentTimeMillis() + ": I am working..." + Thread.currentThread().getName());
                }
            });
        }


        new CountDownLatch(1).await();

    }
}
