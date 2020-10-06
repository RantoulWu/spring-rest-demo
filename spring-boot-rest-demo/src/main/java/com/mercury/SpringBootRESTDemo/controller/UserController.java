package com.mercury.SpringBootRESTDemo.controller;

import com.mercury.SpringBootRESTDemo.bean.User;
import com.mercury.SpringBootRESTDemo.dao.SampleDao;
import com.mercury.SpringBootRESTDemo.dao.UserDao;
import com.mercury.SpringBootRESTDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired // Autowired 的是实现interface的对象
            UserDao userDao;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getSamples() {
        //return fakeSampleDao.getAllSamples();
        return userDao.findAll();
    }

    //POST/users
    //request body: {username,password} -> json -> java
    @PostMapping
    public boolean postUsers(@RequestBody User user) {
        return userService.register(user);
    }

    //DELETE /users/5 -- delete User bob2 with id = 5
    @DeleteMapping("/{id}")
    public boolean deleteUsers(@PathVariable(name = "id") Long id) {
        return userService.deleteUserById(id);
    }

    // PUT(replace or create) - idempotent or PATCH(partial update)
    //PUT/users/7
    //request body : complete user object
    @PutMapping("/{id}")
    public boolean putUsers(@PathVariable(name = "id") Long id,
                            @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
