package com.yxz.java.jvm;


import lombok.extern.slf4j.Slf4j;

/**
 * 伪共享、缓存行代码示例
 * @Date 2025-07-30
 * @Created by Yolo
 */

@Slf4j
public class CacheLineTest {

    public static void main(String[] args) throws InterruptedException {
        // 测试肉眼可见的并发伪共享问题
        // 注释掉新增属性：两个线程并发时的总耗时：3723
        // 放开新增属性：两个线程并发时的总耗时：596
        testPointer(new Pair());

    }

    private static void testPointer(Pair pair) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pair.a++;
            }
        }, "对 Pair 对象中的变量 a 进行 ++　操作");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                // 由于 b 和 a 在同一个缓存行，会导致线程t2 在读取缓存行会失效。需要重新在内存中重新加载进缓存。
                pair.b++;
            }
        }, "对 Pair 对象中的变量 b 进行 ++　操作");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("两个线程并发时的总耗时：" + (System.currentTimeMillis() - start));
    }


    // 前提知识:
    // 1. Java对象的相邻成员变量大概率也会加载到同一缓存行中
    // 2. 做一个循环计数, 会把计数变量放到缓存里,就不用每次循环都往内存存取数据了
    private static class Pair {
        // 一个缓存行64 字节，a属性 8个字节，所以在一个缓存行中，如果有一个线程在读取a时，会顺带把b带出
        volatile long a;

        // 新增的属性
        long p1, p2, p3, p4, p5, p6, p7;

        volatile long b;
    }

}
