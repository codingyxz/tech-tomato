package com.yxz.java.serializable;

import java.io.*;

/**
 * @Description 序列化测试
 * @Date 2025-04-28
 * @Created by Yolo
 */
public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serial();
        unSerial();
    }

    /**
     * 系列化对象到磁盘
     * @throws IOException
     */
    private static void serial() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("student.out"));
        Student student = new Student();
        student.setAge(25);
        student.setName("jayWei");
        Student.address = "yang_nan";
        System.out.println("序列化对象：" + student);
        objectOutputStream.writeObject(student);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private static void unSerial() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("student.out"));
        Student student = (Student) objectInputStream.readObject();
        System.out.println("反序列化对象：" + student);
    }


}
