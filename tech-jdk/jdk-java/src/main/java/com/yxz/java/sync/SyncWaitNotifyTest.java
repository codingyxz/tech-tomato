package com.yxz.java.sync;


/**
 * @Description sync线程通信
 * @Date 2025-04-11
 * @Created by Yolo
 */
public class SyncWaitNotifyTest {

    private static int total;//库存

    public static void main(String[] args) {
        Producer producer = new Producer();
        for (int i = 0; i < 2; i++) {
            new Thread(producer, "面包师傅-" + (i - 1)).start();
        }
        Consumer consumer = new Consumer();
        for (int i = 0; i < 10; i++) {
            new Thread(consumer, "消费者-" + (i - 1)).start();
        }
    }

    private static class Producer implements Runnable {
        private int num = 3; //生产者每次生产三个面包

        @Override
        public void run() {
            try {
                while (true) { //一直生产
                    synchronized (SyncWaitNotifyTest.class) {
                        while ((total + num) > 100) { //仓库满了，生产者等待
                            System.out.println(Thread.currentThread().getName() + "仓库满了，生产者等待。。。");
                            SyncWaitNotifyTest.class.wait();
                        }
                        //等待解除
                        total += num;
                        System.out.println(Thread.currentThread().getName() + "生产面包，库存：" + total);
                        Thread.sleep(500);
                        SyncWaitNotifyTest.class.notifyAll(); //唤醒生产
                    }
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer implements Runnable {
        private int num = 1; //消费者每次消费1个面包

        @Override
        public void run() {
            try {
                while (true) { //一直消费
                    synchronized (SyncWaitNotifyTest.class) {
                        while ((total - num) < 0) { //仓库空了，消费者等待
                            System.out.println(Thread.currentThread().getName() + "仓库空了，消费者等待。。。");
                            SyncWaitNotifyTest.class.wait();
                        }
                        //解除消费者等待
                        total -= num;
                        System.out.println(Thread.currentThread().getName() + "消费面包，库存：" + total);
                        Thread.sleep(500);
                        SyncWaitNotifyTest.class.notifyAll(); //唤醒消费
                    }
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
