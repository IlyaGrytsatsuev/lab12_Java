package Server;

import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter port: ");
        int port = new Scanner(System.in).nextInt();
        Server server = new Server(port);
        server.start();
    }
}
