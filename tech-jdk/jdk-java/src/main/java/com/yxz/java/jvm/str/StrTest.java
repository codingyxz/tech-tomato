package com.yxz.java.jvm.str;

import org.junit.jupiter.api.Test;

/**
 * @Desc 基本操作
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class StrTest {

    /**
     * 常量优化机制
     */
    @Test
    public void test1() {
        String s1 = "a" + "b" + "c";//常量优化机制,编译的时候就已经是abc
        String s2 = "abc";
        /*
         * 最终.java编译成.class,再执行.class
         * String s1 = "abc";
         * String s2 = "abc"
         */
        System.out.println(s1 == s2); //true
        System.out.println(s1.equals(s2)); //true
    }

    /**
     * 字符串拼接
     */
    @Test
    public void test2() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;//new StringBuilder().append("a").append("b").toString() --> new String("ab")
        System.out.println(s3 == s4);
    }

    @Test
    public void test3() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);
    }

}
