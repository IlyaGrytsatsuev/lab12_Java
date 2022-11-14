package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws Exception {
        System.out.print("Enter ip: ");
        String ip = new Scanner(System.in).nextLine();
        System.out.print("Enter port: ");
        int port = new Scanner(System.in).nextInt();
        Client client = new Client(ip,port);

    }
}
