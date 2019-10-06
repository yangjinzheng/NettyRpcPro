import com.netty.client.ClientRequest;
import com.netty.client.Response;
import com.netty.client.TcpClient;
import com.netty.user.bean.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestTcp {
    @Test
    public void tcpTest(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setContent("测试tcp连接请求");
        Response response = TcpClient.send(clientRequest);
        System.out.println(response.getContent());
    }
    @Test
    public void testUser(){
        ClientRequest clientRequest = new ClientRequest();
        List<User> userList = new ArrayList<User>();
        User u = new User();
        u.setId(1);
        u.setName("张三");
        userList.add(u);
        clientRequest.setCommand("com.netty.user.controller.UserController.saveUsers");
        clientRequest.setContent(userList);
        Response response = TcpClient.send(clientRequest);
        System.out.println(response.getContent());
    }
}
