import com.netty.client.ClientRequest;
import com.netty.client.Response;
import com.netty.client.TcpClient;
import org.junit.Test;

public class TestTcp {
    @Test
    public void tcpTest(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setContent("测试tcp连接请求");
        Response response = TcpClient.send(clientRequest);
        System.out.println(response.getContent());
    }
}
