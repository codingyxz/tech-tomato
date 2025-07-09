package com.yxz.dubbo01.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Desc TODO
 * @Date 2025-07-09
 * @Created by Yolo
 */

@Data
@ToString
@Accessors(chain = true)
public class User implements Serializable {

    private String name;
    private String pwd;
    private String sex;
    private String address;

}
