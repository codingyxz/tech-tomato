package com.yxz.java.jvm.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Desc TODO
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class MethodAreaOOM {

    /**
     * 测试方法区溢出
     * VM Args: -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     */
    public static void main(final String[] args) {
        try {
            while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(MethodAreaOOM.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, objects);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * java.lang.OutOfMemoryError: Metaspace
     * 	at org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:558)
     * 	at org.springframework.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:363)
     * 	at org.springframework.cglib.proxy.Enhancer.generate(Enhancer.java:585)
     * 	at org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:131)
     * 	at org.springframework.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:319)
     * 	at org.springframework.cglib.proxy.Enhancer.createHelper(Enhancer.java:572)
     * 	at org.springframework.cglib.proxy.Enhancer.create(Enhancer.java:387)
     * 	at com.yxz.java.jvm.oom.MethodAreaOOM.main(MethodAreaOOM.java:28)
     */

}
