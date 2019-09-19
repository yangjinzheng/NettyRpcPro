package com.netty.client;


import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class ClientRequest {
    private final long id;
    private Object content;
    private String command;
    private final AtomicLong aid = new AtomicLong(1);
    public ClientRequest(){
        id = aid.incrementAndGet();
    }
}
