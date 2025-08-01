package com.yxz.java.jvm.str;

import org.junit.jupiter.api.Test;

/**
 * @Desc str 的 intern
 * @Date 2025-07-24
 * @Created by Yolo
 */
public class StrInternTest {


    @Test
    public void test1() {
        String newH = new String("hello");  // 堆

        String internH = newH.intern();           // 字符串常量池

        String strH = "hello";                    // 字符串常量池

        System.out.println(newH == strH);         // false
        System.out.println(newH == internH);      // false
        System.out.println(internH == strH);      // true

    }

    @Test
    public void test2() {
        String strH = "hello";                   // 字符串常量池

        String newH = new String(strH);          // 堆

        String internH = newH.intern();          // 字符串常量池

        System.out.println(strH == newH);        // false
        System.out.println(strH == internH);     // true
        System.out.println(newH == internH);     // false
    }

    @Test
    public void test3() {
        String s = new String("1");
        String si = s.intern();
        String s2 = "1";
        System.out.println(s == s2);  // false
        System.out.println(si == s2); // true

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); //false
    }

    @Test
    public void test4() {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2); //false

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        String s5 = s3.intern();
        System.out.println(s3 == s4); //false
        System.out.println(s4 == s5); //true
    }
}
