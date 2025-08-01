package com.yxz.java11.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Desc TODO
 * @Date 2025-07-31
 * @Created by Yolo
 */
public class GCLogTest {


    /**
     * -Xms 60m -Xmx60m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:./logs/gc.log
     * @param args
     */
    public static void main(String[] args) {


        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            byte[] arr = new byte[1024 * 100];
            list.add(arr);

            try {
                TimeUnit.MICROSECONDS.sleep(50);
            } catch (Exception e) {

            }
        }

    }

}
