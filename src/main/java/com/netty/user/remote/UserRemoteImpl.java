package com.netty.user.remote;

import com.netty.annotion.Remote;
import com.netty.client.Response;
import com.netty.user.bean.User;
import com.netty.user.service.UserService;
import com.netty.util.ResponseUtil;

import javax.annotation.Resource;
import java.util.List;

@Remote
public class UserRemoteImpl implements UserRemote {
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
