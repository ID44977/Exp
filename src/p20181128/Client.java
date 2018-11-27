package p20181128;

import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        //指定一个端口号,如16888
        int port = 16888;
        String host = "localhost";
        Socket socket = new Socket(host, port);
        //创建客户端界面应用程序
        new ChatFrame("Client", socket);
    }
}
