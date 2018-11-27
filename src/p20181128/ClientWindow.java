package p20181128;

import java.net.Socket;

public class ClientWindow {
    public static void main(String[] args) throws Exception {
        int port = 8000;
        String host = "localhost";
        Socket socket = new Socket(host,port);
        new Framework("Client",socket);
    }
}
