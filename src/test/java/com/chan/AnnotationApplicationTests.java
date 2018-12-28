package com.chan;

import com.chan.controller.UserController;
import com.chan.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationApplicationTests {

    @Autowired
    private UserController userController;

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("com.chan.model.Student");
            log.info("clazz:{}", clazz);

            //创建对象的实例
            Object obj = clazz.newInstance();
            log.info("obj:{}", obj);

            // 反射获取User对象的公共构造器
            Constructor<Student>[] constructors = clazz.getConstructors();
            for (int i = 0; i < constructors.length; i++) {
                log.info("constructors:{}", constructors[i]);
            }


            Field[] fields = clazz.getFields();
            for (int i = 0; i < fields.length; i++) {
                log.info("fields:{}", fields[i]);
            }

            //获取对象的所有公共方法(包括继承的父类和实现的接口)
            Method[] methods = clazz.getMethods();
            for (int i = 0; i < methods.length; i++) {
                log.info("method:{}", methods[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queueTest1() {
        int cont = 20;
        LinkedBlockingQueue queue = new LinkedBlockingQueue(20);
        for (int i = 0; i < cont; i++) {
            queue.add("queue" + i);
        }
    }


    @Test
    public void annotionTest1() {
        userController.test2("name", 11);
    }

    @Test
    public void contextLoads() {
    }

}

