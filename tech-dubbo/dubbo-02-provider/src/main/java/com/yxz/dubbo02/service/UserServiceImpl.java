package com.yxz.dubbo02.service;

import com.yxz.dubbo01.pojo.User;
import com.yxz.dubbo01.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desc TODO
 * @Date 2025-07-09
 * @Created by Yolo
 */

@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public User login(String name, String pwd) {

        log.info("name : {}  ---   pwd : {} ", name,pwd);
        return new User().setName(name).setPwd(pwd).setAddress("xxxxx").setSex("male");
    }
}
