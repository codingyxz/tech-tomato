package com.yxz.java.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description 实体对象
 * @Date 2025-04-28
 * @Created by Yolo
 */

@ToString
@Data
public class Person {

    private Long id;
    /**
     * 指定字段是否参与序列化或者反序列化
     */
//    @JSONField(serialize = false, deserialize = false)
    private String name;
    private String pwd;

    /**
     * 指定属性名和json中key的对应关系
     */
    @JSONField(name = "address")
    private String addr;

    private String websiteUrl;
    /**
     * 日期格式化
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

}
