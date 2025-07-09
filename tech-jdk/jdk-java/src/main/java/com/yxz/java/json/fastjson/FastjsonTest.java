package com.yxz.java.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Date 2025-04-28
 * @Created by Yolo
 */
public class FastjsonTest {

    /**
     * 反序列化
     */
    @Test
    public void testDeSerializer() {
        String str = "{\"addr\":\"南京路\",\"birthday\":\"2025-04-28 16:20:08\",\"id\":1,\"name\":\"南京南京\",\"pwd\":\"123\",\"registerDate\":\"2025-04-28 16:20:08\",\"websiteUrl\":\"http://www.baidu.com\"}\n";
        // 反序列化为person对象
        Person person = JSON.parseObject(str, Person.class);
        System.out.println(person);

        ResultVO<Person> resultVO_1 = ResultVO.buildSuccess(person);
        String voJsonStr = JSON.toJSONString(resultVO_1);
        // 调用端需要把voJsonStr反序列化为对象
        // 反序列化后不能获取到泛型类型
//        ResultVO resultVO_2 = JSON.parseObject(voJsonStr, ResultVO.class);
//        System.out.println("resultVO_2 :" + resultVO_2);
//        Object data = resultVO_2.getData();

        // 需要反序列化为什么类型，就给TypeReference传入什么类型即可
        ResultVO<Person> resultVO_3 = JSON.parseObject(voJsonStr, new TypeReference<ResultVO<Person>>() {
        });
        System.out.println("resultVO_3：" + resultVO_3);
        Person data = resultVO_3.getData();
        System.out.println(data);

    }

    /**
     * SerializeFilter 定制处理
     * 比如要求：输出的json字符串key为大写
     */
    @Test
    public void testSerializeFilter() {
        Person person = new Person();
        person.setId(1L);
        person.setName("南京南京");
        person.setPwd("123");
        person.setAddr("南京路");
        person.setWebsiteUrl("http://www.baidu.com");
        person.setRegisterDate(new Date());
        person.setBirthday(LocalDateTime.now());

        /**
         * obj - person对象
         * name - 属性
         * value - 属性对应的值
         */
        NameFilter nameFilter = (obj, name, value) -> name.toUpperCase();
        String string = JSON.toJSONString(person, nameFilter);
        System.out.println(string);
    }

    /**
     * $ref 引用探测(对象中多次引用同一个其他对象时，序列化时就会出现$ref)
     * [{"id":1,"name":"南京南京"},{"$ref":"$[0]"},{"$ref":"$[0]"}]
     */
    @Test
    public void test$ref() {

        Person person = new Person();
        person.setId(1L);
        person.setName("南京南京");

        List<Person> list = Arrays.asList(person, person, person);
        /**
         * DisableCircularReferenceDetect 禁用引用探测功能
         */
        String string = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(string);
    }


    /**
     * 对象转为json
     */
    @Test
    public void test() {
        Person person = new Person();
        person.setId(1L);
//        person.setName("南京南京");
        person.setPwd("123");
        person.setAddr("南京路");
        person.setWebsiteUrl("http://www.baidu.com");
        person.setRegisterDate(new Date());
        person.setBirthday(LocalDateTime.now());

        /**
         * SerializerFeature.WriteMapNullValue  指定序列化时包含null值
         * SerializerFeature.PrettyFormat       美化输出
         */
        String string = JSON.toJSONString(person, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);
        System.out.println(string);
    }

}
