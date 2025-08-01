package com.yxz.java.jvm.str;

import org.junit.jupiter.api.Test;

/**
 * @Desc str 的不可变性
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class StrImmutableTest {

    /**
     *    1.通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。
     *    2.字符串常量池中是不会存储相同内容的字符串的。
     */

    @Test
    public void test1() {
        String s1 = "abc";//字面量的定义方式
        String s2 = "abc";
        s1 = "hello";

        System.out.println("s1 == s2:" + s1 == s2);//比较s1和s2的地址值
        System.out.println("s1:" + s1);//hello
        System.out.println("s2:" + s2);//abc

        System.out.println("*****************");

        String s3 = "abc";
        s2 += "def";
        System.out.println("s3:" + s3);//abcdef
        System.out.println("s2:" + s2);

        System.out.println("*****************");

        String s4 = "abc";
        String s5 = s4.replace('a', 'm');
        System.out.println("s4:" + s4);//abc
        System.out.println("s5:" + s5);//mbc

    }


    @Test
    public void test2() {
        StrImmutableTest ex = new StrImmutableTest();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str + " and "); //
        System.out.println(ex.ch);
    }

    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'b';
    }

}
