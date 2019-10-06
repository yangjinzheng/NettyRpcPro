package com.netty.user.controller;

import com.netty.client.Response;
import com.netty.util.ResponseUtil;
import com.netty.user.bean.User;
import com.netty.user.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;
    public Response saveUser(User user){
        userService.save(user);
        return ResponseUtil.createSuccessResult(user);
    }
    public Response saveUsers(List<User> users){
        userService.saveList(users);
        return ResponseUtil.createSuccessResult(users);
    }
}
