package com.yxz.java.jvm;

/**
 * @Description TODO
 * @Date 2025-04-03
 * @Created by Yolo
 */
public class VolatileTest {

    private static volatile int num = 1;

    public static void main(String[] args) {

        num = 2;

        int size = 5;

        int count = num;

        num = 3;


    }


}
