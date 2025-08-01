package com.yxz.java.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Desc TODO
 * @Date 2025-07-29
 * @Created by Yolo
 */


@Slf4j
public class CyclicBarrierTest {


    public static void main(String[] args) {

        final int totalWorker = 5; // 工人总数
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalWorker); // 创建一个CyclicBarrier实例，并指定总工作线程数

        for (int i = 0; i < totalWorker; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    log.info("工人" + Thread.currentThread().getId() + "已准备就绪");
                    // 线程在此等待，直到所有线程都达到这个屏障点
                    cyclicBarrier.await();
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.info("工人" + Thread.currentThread().getId() + "开始下一阶段的工作");
            }).start();
        }
    }

}
