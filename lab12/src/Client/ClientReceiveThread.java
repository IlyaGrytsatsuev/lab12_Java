package Client;
import java.net.*;
import java.io.*;


public class ClientReceiveThread extends Thread {
    private Socket ClientSocket;
    private DataInputStream in;
    private String text;
    private String name;


    public ClientReceiveThread(Socket socket) throws Exception{
        ClientSocket = socket;
        in = new DataInputStream(ClientSocket.getInputStream());

    }

    public void run() {
        try {
            while(true){
                text = in.readUTF();
                System.out.println(text);
            }

        } catch (Exception e) {
            try {
                ClientSocket.close();
            }
            catch (Exception a){
                a.printStackTrace();
            }
        }

    }
}
