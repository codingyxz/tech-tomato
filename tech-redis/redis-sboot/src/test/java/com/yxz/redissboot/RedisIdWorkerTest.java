package com.yxz.redissboot;

import com.yxz.redissboot.util.RedisIdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisIdWorkerTest {


    @Autowired
    private RedisIdWorker idWorker;

    @Test
    public void testId(){

        for (int i = 0; i < 20; i++) {
            System.out.println(idWorker.nextId("test"));
        }

    }


}
