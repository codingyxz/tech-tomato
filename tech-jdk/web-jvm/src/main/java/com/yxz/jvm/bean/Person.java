package com.yxz.jvm.bean;

import lombok.Data;

/**
 * @Desc TODO
 * @Date 2025-08-01
 * @Created by Yolo
 */

@Data
public class Person {

    private String name;
    private int age;
    private String sex;
    private String address;


    public void print() {
    }
}
