package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSendThread  extends Thread{
    private Socket ClientSocket;
    private DataOutputStream out;
    private String text;
    private String name;
    private BufferedReader keyboard;

    public ClientSendThread(Socket socket) throws Exception{
        ClientSocket = socket;
        out = new DataOutputStream(ClientSocket.getOutputStream());
        keyboard = new BufferedReader(new InputStreamReader(System.in));

    }

    public void run(){
        try{
            System.out.print("Enter your name: ");
            name = new Scanner(System.in).nextLine();
            out.writeUTF(name);
            while(true){
                text = keyboard.readLine();
                out.writeUTF(text);
                if(text.equals("@quit")) {
                    ClientSocket.close();
                    break;
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
