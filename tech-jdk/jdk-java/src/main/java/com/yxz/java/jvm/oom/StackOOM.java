package com.yxz.java.jvm.oom;

/**
 * @Desc TODO
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class StackOOM {

    private static int threadNum = 0;

    public void doSomething() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 栈内存溢出
     * VM Args: -Xss2m
     */
    public static void main(String[] args) {
        final StackOOM stackOOM = new StackOOM();
        try {
            while (true) {
                threadNum++;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        stackOOM.doSomething();
                    }
                });
                thread.start();
            }
        } catch (Throwable e) {
            System.out.println("目前活动线程数量：" + threadNum);
            throw e;
        }
    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
     * 	at java.lang.Thread.start0(Native Method)
     * 	at java.lang.Thread.start(Thread.java:719)
     * 	at com.yxz.java.jvm.oom.StackOOM.main(StackOOM.java:35)
     */

}
