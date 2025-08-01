package com.yxz.java.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Date 2025-03-21
 * @Created by Yolo
 */
public class FutureTaskTest {

    ExecutorService executorService = Executors.newFixedThreadPool(3);


    /**
     * 声明callable，并提交给线程池执行
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {

        Future<?> future = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "--------");
            return "xxx";
        });

        System.out.println("future：" + future.get());
        Thread.sleep(5000l);
    }

    /**
     * 创建FutureTask，并提交给线程池执行
     */
    @Test
    public void test1() throws InterruptedException, ExecutionException {

        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "--------");
            return "xxx";
        });

        Future<?> future = executorService.submit(task);
        System.out.println("task：" + task.get());
        System.out.println("future：" + future.get());

        Thread.sleep(5000l);
    }

}
