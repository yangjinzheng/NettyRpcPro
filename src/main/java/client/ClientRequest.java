package client;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class ClientRequest {
    private final long id;
    private Object content;
    private final AtomicLong aid = new AtomicLong(1);
    public ClientRequest(){
        id = aid.incrementAndGet();
    }
}
