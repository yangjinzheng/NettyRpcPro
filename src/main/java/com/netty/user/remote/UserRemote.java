package com.netty.user.remote;

import com.netty.client.Response;
import com.netty.user.bean.User;
import com.netty.util.ResponseUtil;

import java.util.List;

public interface UserRemote {
    public Response saveUser(User user);
    public Response saveUsers(List<User> users);
}
