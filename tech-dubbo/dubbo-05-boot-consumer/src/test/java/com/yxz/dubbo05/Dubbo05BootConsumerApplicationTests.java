package com.yxz.dubbo05;

import com.yxz.dubbo01.pojo.User;
import com.yxz.dubbo01.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Dubbo05BootConsumerApplicationTests {

    @DubboReference(url = "dubbo://192.168.1.209:20880/com.yxz.dubbo01.service.UserService")
    private UserService userService;
    @Test
    void contextLoads() {
    }


    @Test
    void testDubboReference()
    {
        User user = userService.login("zhang3", "123456");
        System.out.println(user);
    }


}
