package com.netty.client;

import lombok.Data;

@Data
public class Response {
    private long id;
    private Object content;
    private String status = "00000";//00000表示成功
    private String msg;
}
