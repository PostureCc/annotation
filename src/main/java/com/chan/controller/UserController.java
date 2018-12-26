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

    @UserAnnotation(value = "test2")
    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public String test2(String name, Integer age) {
        return "test2";
    }

    @UserAnnotation(value = "test3")
    @RequestMapping(value = "test3", method = RequestMethod.GET)
    public String test3(String name, Integer age) {
        int i = 5 / 0;
        return "test3";
    }
}
