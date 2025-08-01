package com.yxz.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc TODO
 * @Date 2025-07-23
 * @Created by Yolo
 */
public class AQSConditionTest {


    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

//        condition.await();




    }

}
