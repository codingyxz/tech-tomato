package com.yxz.jvm.controller;

import com.yxz.jvm.bean.Person;
import com.yxz.jvm.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Desc TODO
 * @Date 2025-08-01
 * @Created by Yolo
 */

@Slf4j
@RestController
public class MemoryController {

    @Autowired
    private PersonService personService;


    /**
     * 模拟OOM错误
     *
     *  java8  -Xms40m -Xmx40M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./hprof/heap_oom_dump.hprof -XX:MetaspaceSize=64m -Xloggc:./logs/heap_oom_gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps
     *  java9  -Xms40m -Xmx40M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./hprof/heap_oom_dump.hprof -XX:MetaspaceSize=64m -Xlog:gc*=info:file=./logs/heap_oom_gc.log:time:filecount=0
     */
    @RequestMapping("/heapOOM")
    public void heapOOM() {
        log.info("addObject：" + personService);

        List<Person> list = new ArrayList<>();

        while (true) {
            list.add(new Person());
        }
    }


    /**
     * 模拟Metaspace空间溢出
     */
    @RequestMapping("/metaSpaceOOM")
    public void metaSpaceOOM() {

        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();

        while (true) {

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Person.class);
            enhancer.setUseCache(true);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                log.info("我是加强类，输出print之前的加强方法");
                return methodProxy.invokeSuper(o, objects);
            });

            Person person = (Person) enhancer.create();
            person.print();

            log.info(person.getClass().toString());
            log.info("totalClass：" + classLoadingMXBean.getTotalLoadedClassCount());
            log.info("activeClass：" + classLoadingMXBean.getLoadedClassCount());
            log.info("unloadedClass：" + classLoadingMXBean.getUnloadedClassCount());
        }
    }


    ExecutorService executorService = Executors.newFixedThreadPool(4);

    @RequestMapping("/cpuHigh")
    public void cpuHigh() {

        for (int i = 0; i < 4; i++) {
            executorService.submit(() -> {
                while (true) {
                    System.out.println(System.currentTimeMillis() + ": I am working..." + Thread.currentThread().getName());
                }
            });
        }
    }

}
