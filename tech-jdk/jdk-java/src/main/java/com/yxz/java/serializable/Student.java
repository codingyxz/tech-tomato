package com.yxz.java.serializable;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description TODO
 * @Date 2025-04-28
 * @Created by Yolo
 */

@ToString
@Data
public class Student implements Serializable {

    private String name;
    private Integer age;
    public static String address;

}
