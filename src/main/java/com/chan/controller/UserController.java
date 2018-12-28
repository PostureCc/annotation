package com.chan.controller;

import com.chan.annotation.user.UserAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String test1() {
        return "test1";
    }

    @UserAnnotation(token = "前端在包头中传过来的token")
    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public String test2(String name, Integer age) {
        return "test2";
    }


    @RequestMapping(value = "test3", method = RequestMethod.GET)
    public String test3(String name, Integer age) {
        int i = 5 / 0;
        return "test3";
    }
}
