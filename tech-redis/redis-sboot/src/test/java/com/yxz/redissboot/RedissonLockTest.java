package com.yxz.redissboot;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedissonLockTest {


    @Autowired
    private RedissonClient redissonClient;


    @Test
    public void testRedissonLock() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(4);


        for (int i = 0; i < 4; i++) {
            executorService.submit(() -> {
                RLock rLock = redissonClient.getLock("TEST");
                boolean tryLock = false;
                try {
                    while (!tryLock) {
//                        tryLock = rLock.tryLock(1, 30, TimeUnit.SECONDS);
                        tryLock = rLock.tryLock(1,TimeUnit.SECONDS);
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                    log.info("线程：" + Thread.currentThread().getName() + " 获取到锁.....");
                    TimeUnit.SECONDS.sleep(50);
                    log.info("线程：" + Thread.currentThread().getName() + " 睡眠结束.....");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (tryLock) {
                        rLock.unlock();
                    }
                }
            });
        }

        new CountDownLatch(1).await();

    }

}
