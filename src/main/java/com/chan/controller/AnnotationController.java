package com.chan.controller;

import com.chan.annotation.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chan
 * @Date: 2019/12/9 15:07
 * @Description:
 */
@RestController
public class AnnotationController {

    @Authorization
    @GetMapping("test1")
    public void test1() {
        System.err.println("test1...");
    }

}
