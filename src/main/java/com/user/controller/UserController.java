package com.user.controller;

import com.user.bean.User;
import com.user.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;
    public void saveUser(User user){
        userService.save(user);
    }
}
