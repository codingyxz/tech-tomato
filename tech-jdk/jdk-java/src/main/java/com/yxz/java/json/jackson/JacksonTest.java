package com.yxz.java.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yxz.java.json.fastjson.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description TODO
 * @Date 2025-04-28
 * @Created by Yolo
 */
public class JacksonTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());

        /**
         * 序列化配置
         */
        // 全局配置：配置序列化时只包含非空属性
        // 单个bean配置，需要到实体类上通过注解配置 @JsonInclude(JsonInclude.Include.NON_NULL)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    /**
     * 序列化对象为json字符串
     * @throws JsonProcessingException
     */
    @Test
    public void test() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
//        user.setName("南京南京");
        user.setPwd("123");
        user.setAddr("南京路");
        user.setWebsiteUrl("http://www.baidu.com");
        user.setRegisterDate(new Date());
        user.setBirthday(LocalDateTime.now());

        String string = objectMapper.writeValueAsString(user);
        System.out.println(string);

    }

}
