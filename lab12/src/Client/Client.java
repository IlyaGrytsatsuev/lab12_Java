package Client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;

public class Client {
    private Socket ClientSocket;
    private InetAddress ip;
    private int port;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String ip_, int port_) throws Exception{
        ip = InetAddress.getByName(ip_);
        port = port_;
        ClientSocket = new Socket(ip,port);
        ClientSendThread thread1 = new ClientSendThread(ClientSocket);
        ClientReceiveThread thread2 = new ClientReceiveThread(ClientSocket);
        thread1.start();
        thread2.start();

    }
}
