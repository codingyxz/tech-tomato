package com.yxz.dubbo04.service;

import com.yxz.dubbo01.pojo.User;
import com.yxz.dubbo01.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Desc TODO
 * @Date 2025-07-10
 * @Created by Yolo
 */
@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public User login(String name, String pwd) {

        return new User().setName(name).setPwd(pwd).setAddress("xxxxx").setSex("male");
    }
}
