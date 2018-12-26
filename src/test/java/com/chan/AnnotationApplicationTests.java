package com.chan;

import com.chan.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationApplicationTests {

    @Autowired
    private UserController userController;

    @Test
    public void annotionTest1() {
        userController.test2("name", 11);
    }

    @Test
    public void contextLoads() {
    }

}

