package com.yxz.java.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Desc TODO
 * @Date 2025-07-29
 * @Created by Yolo
 */
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {


        /**
         * 所有游客等待导游办理入住方可休息
         */
        CountDownLatch startSignal = new CountDownLatch(1);
        /**
         * 旅游大巴司机等待所有游客上车方可出发
         */
        CountDownLatch doneSignal = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new worker(startSignal, doneSignal)).start();
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("准备完毕，开始执行。。。。");

        startSignal.countDown();

        System.out.println("子任务开始执行........");

        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    static class worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (Exception e) {

            }
        }

        private void doWork() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " worker done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
