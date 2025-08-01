package com.yxz.java.juc;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Desc TODO
 * @Date 2025-07-21
 * @Created by Yolo
 */
public class ThreadPoolCustomTest {


    @Test
    public void test1() {
        //创建一个可缓存线程池
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        //创建一个定长线程池
        ExecutorService fixedPool = Executors.newFixedThreadPool(5);
        //创建一个周期性执行的线程池
        ExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
        //创建一个单线程化的线程池
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
    }


    /**
     * AbstractExecutorService 实现了 ExecutorService中的
     * 有关submit、invoke方法，封装为Future返回
     * 其余方法需子类自行实现
     */
    class CustomThreadPool extends AbstractExecutorService {

        @Override
        public void shutdown() {

        }

        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        @Override
        public boolean isShutdown() {
            return false;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void execute(Runnable command) {

        }
    }

}
