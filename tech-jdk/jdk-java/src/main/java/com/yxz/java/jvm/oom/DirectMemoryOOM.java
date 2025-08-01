package com.yxz.java.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Desc TODO
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class DirectMemoryOOM {

    private static final int _1mb = 1024 * 1024;

    /**
     * 直接内存溢出
     * VM Args: -XX:MaxDirectMemorySize=10M
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
        unsafe.setAccessible(true);
        Unsafe u = (Unsafe) unsafe.get(null);
        while (true) {
            //分配直接内存
            u.allocateMemory(_1mb);
        }

    }

}
