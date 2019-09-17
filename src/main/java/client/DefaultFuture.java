package client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultFuture {

    public static final ConcurrentHashMap<Long, DefaultFuture> allDefaultFuture = new ConcurrentHashMap<Long, DefaultFuture>();
    final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Response response;
    public DefaultFuture(ClientRequest request) {
        allDefaultFuture.put(request.getId(),this);
    }
//主线程获取数据首先要等待结果
    public Response get(){
        lock.lock();
        try{
            while (!done()){
                condition.await();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return this.response;
    }

    public static void receive(Response response){
        DefaultFuture df = allDefaultFuture.get(response.getId());
        if(df != null){
            Lock lock = df.lock;
            lock.lock();
            try {
                df.setResponse(response);
                df.condition.signal();
                allDefaultFuture.remove(df);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    private boolean done() {
        if(this.response != null) {
            return true;
        }
        return false;
    }
}
