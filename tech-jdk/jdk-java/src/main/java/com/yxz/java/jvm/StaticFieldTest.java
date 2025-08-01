package com.yxz.java.jvm;

/**
 *  * 静态引用对应的对象实体始终都存在堆空间
 *  * jdk7：-Xms200m -Xmx200m -XX:PermSize=300m -XX:MaxPermSize=300m -XX:+PrintGCDetails
 *  * jdk8：-Xms200m -Xmx200m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class StaticFieldTest {

    private static byte[] arr = new byte[1024 * 1024 * 100];

    public static void main(String[] args) {
        System.out.println(StaticFieldTest.arr);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
