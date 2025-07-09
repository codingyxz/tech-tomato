package com.yxz.java.json.fastjson;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2025-05-22
 * @Created by Yolo
 */
public class FastjsonErrorTest {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        System.out.println(System.getProperty("user.dir"));


        URL resource = FastjsonErrorTest.class.getClassLoader().getResource("base64.txt");

        //
        FileInputStream fis = new FileInputStream(new File(resource.toURI()));

        byte[] buff = new byte[1024 * 1024];

        StringBuffer sb = new StringBuffer();

        int length = 0;


        System.out.println("读取。。。。");
        Thread.sleep(15000l);

        while ((length = fis.read(buff)) != -1){
            sb.append(new String(buff),0,length);
        }


        Map<String,String> map = new HashMap<>();

        map.put("key",sb.toString());

        System.out.println("等待。。。。");
        Thread.sleep(15000l);

        System.out.println("开始读取....");
        String jsonString = JSON.toJSONString(map);

        System.out.println(jsonString);
        Thread.sleep(100000000l);

    }
}
