package com.yxz.dubbo01.service;

import com.yxz.dubbo01.pojo.User;

/**
 * @Desc TODO
 * @Date 2025-07-09
 * @Created by Yolo
 */
public interface UserService {

    User login(String name, String pwd);

}
