package com.yxz.java.jvm.oom;

/**
 * @Desc TODO
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class StackSOF {

    private int stackLength=1;
    public void doSomething(){
        stackLength++;
        doSomething();
    }


    /**
     * 栈溢出
     * VM Args: -Xss256k
     */
    public static void main(String[] args) {
        StackSOF stackSOF=new StackSOF();
        try {
            stackSOF.doSomething();
        }catch (Throwable e){//注意捕获的是Throwable
            System.out.println("栈深度："+stackSOF.stackLength);
            throw e;
        }
    }

    /**
     * 栈深度：1891
     * Exception in thread "main" java.lang.StackOverflowError
     * 	at com.yxz.java.jvm.oom.StackSOF.doSomething(StackSOF.java:12)
     * 	at com.yxz.java.jvm.oom.StackSOF.doSomething(StackSOF.java:13)
     * 	at com.yxz.java.jvm.oom.StackSOF.doSomething(StackSOF.java:13)
     * 	at com.yxz.java.jvm.oom.StackSOF.doSomething(StackSOF.java:13)
     * 	at com.yxz.java.jvm.oom.StackSOF.doSomething(StackSOF.java:13)
     * 	* 省略部分
     */

}
