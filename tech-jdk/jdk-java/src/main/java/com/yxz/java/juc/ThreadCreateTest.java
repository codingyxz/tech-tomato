package com.yxz.java.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @Desc TODO
 * @Date 2025-07-21
 * @Created by Yolo
 */
public class ThreadCreateTest {

    @Test
    public void test1() throws InterruptedException {

        new Thread(() -> {
            System.out.println("hello world");
        }, "t1").start();

        Thread thread = new Thread("t2") {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        thread.start();


        new CountDownLatch(1).await();

    }

}
