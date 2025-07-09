package com.yxz.java.juc;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 线程池工作原理测试
 * @Date 2025-04-21
 * @Created by Yolo
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        /**
         * int corePoolSize                       核心线程数
         * int maximumPoolSize                    最大线程数
         * long keepAliveTime                     保持存活的时间
         * TimeUnit unit                          时间单位
         * BlockingQueue<Runnable> workQueue      任务队列
         * RejectedExecutionHandler handler       饱和策略
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 20; i++) {
            try {
                executor.execute(new MyRunnable("第" + (i + 1) + "号线程"));
            } catch (Throwable e) {
                System.out.println("丢弃任务 " + (i + 1));
            }
        }

    }

    static class MyRunnable implements Runnable {

        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(name);
            while (true) {
                Thread.sleep(100000000l);
            }
        }
    }

}
