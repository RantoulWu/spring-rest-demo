package com.mercury.SpringBootRESTDemo.controller;

import com.mercury.SpringBootRESTDemo.test.ATest;
import com.mercury.SpringBootRESTDemo.test.BTest;
import com.mercury.SpringBootRESTDemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 没写 requestmapping == requestmapping("/") all request will do through it
public class TestController {
    @Autowired
    UserDao userDao;
    @Autowired
    ATest aTest;
    @Autowired
    BTest bTest;

    @GetMapping(path = "/fetch") // 在这里加 mapping
    public String fetch() {
        // when we load user, will userDetails and profiles be loaded together?
        userDao.findAll();
        return "hello"; // if return users, jackson will user getters to generate JSON
    }

    @GetMapping(path = "/aop")
    public void aop() {

        aTest.a1();
        aTest.a2();
        aTest.a3();
        bTest.b1();
        bTest.b2();

    }

//    concern: which method is executed in ATest or BTest at what time?
//    advice: before any method executes, print its name and current time.
//            pointcut -> join point

}
