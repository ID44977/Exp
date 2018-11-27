package p20181128;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverWindow {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        Socket socket = new ServerSocket(port).accept();
        new Framework("Server", socket);
    }
}
