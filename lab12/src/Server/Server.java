package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;

public class Server {

    private ServerSocket server_socket;
    private Socket serverToClientSocket;
    private int port;
    private HashMap<String, Socket> ClientMap = new HashMap<>();


    public Server(int port_) throws Exception{
        port = port_;
        server_socket = new ServerSocket(port);

    }

    public void start() throws Exception{

        while(true){
                serverToClientSocket = server_socket.accept();
                ToClientSendThread ClientThread = new ToClientSendThread(serverToClientSocket, ClientMap);
                ClientThread.start();
        }
    }
}
