package com.yxz.java.generic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 获取泛型参数测试类
 */
public class GenericTest {

    /**
     * 获取父类的泛型参数
     */
    @Test
    public void test1() {
        Type genericSuperclass = ChildGenericClass.class.getGenericSuperclass();
        printMsg(genericSuperclass);
        /**
         * parameterizedType：com.yxz.java.generic.GenericClass<java.lang.String, java.lang.Integer>
         * class java.lang.String
         * class java.lang.Integer
         * rawType：class com.yxz.java.generic.GenericClass
         * ownerType：null
         */
    }

    private GenericClass<String, Integer> InsGenericClass = new GenericClass<>();
    public static GenericClass<String, String> claGenericClass = new GenericClass<>();

    /**
     * 获取成员变量的泛型参数（通过反射获取到Field）
     *
     * @throws NoSuchFieldException
     */
    @Test
    public void test2() throws NoSuchFieldException {
        Field insGenericClass = new GenericTest().getClass().getDeclaredField("InsGenericClass");
        Type insGenericType = insGenericClass.getGenericType();
        printMsg(insGenericType);

        System.out.println("=========================================");

        Field claGenericClass = GenericTest.class.getDeclaredField("claGenericClass");
        Type claGenericType = claGenericClass.getGenericType();
        printMsg(claGenericType);

    }


    /**
     * 局部变量需要声明为内部类的形式，才可获取对应类型的泛型类型
     */
    @Test
    public void test3() {
        GenericClass genericClass = new GenericClass<String, Integer>() {
        };
        Type genericSuperclass = genericClass.getClass().getGenericSuperclass();
        printMsg(genericSuperclass);
        /**
         * parameterizedType：com.yxz.java.generic.GenericClass<java.lang.String, java.lang.Integer>
         * class java.lang.String
         * class java.lang.Integer
         * rawType：class com.yxz.java.generic.GenericClass
         * ownerType：null
         */
    }

    /**
     * 局部变量直接获取不到本类型的泛型类型
     */
    @Test
    public void test4() {
        ArrayList<String> arrayList = new ArrayList<>();
        Type genericSuperclass = arrayList.getClass().getGenericSuperclass();
        printMsg(genericSuperclass);
    }


    private void printMsg(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println("parameterizedType：" + parameterizedType);

            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type type1 : actualTypeArguments) {
                System.out.println(type1);
            }

            Type rawType = parameterizedType.getRawType();
            System.out.println("rawType：" + rawType);

            Type ownerType = parameterizedType.getOwnerType();
            System.out.println("ownerType：" + ownerType);
        } else {
            System.out.println("type：" + type);
        }


    }

}
