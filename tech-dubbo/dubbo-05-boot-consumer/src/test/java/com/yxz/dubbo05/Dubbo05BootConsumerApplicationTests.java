package com.yxz.dubbo05;

import com.yxz.dubbo01.pojo.User;
import com.yxz.dubbo01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class Dubbo05BootConsumerApplicationTests {

    @DubboReference //(url = "dubbo://192.168.1.209:20880/com.yxz.dubbo01.service.UserService")
    private UserService userService;

    @Test
    void contextLoads() {
    }


    @Test
    void testDubboReference() {
        log.info(userService.toString());
        User user = userService.login("zhang3", "123456");
        log.info(user.toString());
    }


}
