package com.netty.util;

import com.netty.client.Response;

public class ResponseUtil {
    public static Response createSuccessResult(){
        return new Response();
    }
    public static Response createFailResult(String code,String msg){
        Response response = new Response();
        response.setMsg(msg);
        response.setStatus(code);
        return response;
    }
    public static Response createSuccessResult(Object content){
        Response response = new Response();
        response.setContent(content);
        return response;
    }
}
