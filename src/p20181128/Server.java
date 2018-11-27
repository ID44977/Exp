package p20181128;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 16888;
        @SuppressWarnings("resource")
        Socket socket = new ServerSocket(port).accept();

        //创建服务端界面应用程序
        new ChatFrame("Server", socket);

        //先开启服务端，再开启客户端
    }
}
