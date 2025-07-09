package com.yxz.java.json.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;
    /**
     * 指定字段是否参与序列化或者反序列化
     */
    private String name;
    private String pwd;

    /**
     * 指定属性名和json中key的对应关系
     */
    private String addr;

    private String websiteUrl;
    /**
     * 日期格式化
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registerDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime birthday;

}
