package Server;

import Client.Client;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class ToClientSendThread extends Thread{
    private Socket ToClientSocket;
    private HashMap<String, Socket> ClientMap;
    private DataOutputStream out;
    private BufferedReader keyboard;
    private DataInputStream in;
    String input;
    String output;
    String TargetName;
    String name;

    public ToClientSendThread(Socket socket, HashMap<String,Socket> map) throws Exception{
        ToClientSocket = socket;
        ClientMap = map;
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        in = new DataInputStream(ToClientSocket.getInputStream());
    }

    public void run(){
        try{
            name = in.readUTF();
            System.out.println("User " + name + " has connected");
            synchronized (ClientMap) {
                ClientMap.put(name, ToClientSocket);
            }
          /*  for (Object key : ClientMap.keySet())
                System.out.println(key);*/


            while(true){

                input = in.readUTF();

                if(input.equals("@quit")){
                    synchronized (ClientMap) {
                        ClientMap.remove(name);
                        for (Object key : ClientMap.keySet()) {
                                out = new DataOutputStream(ClientMap.get(key).getOutputStream());
                                out.writeUTF("User " + name + " has left");
                        }
                    }
                    System.out.println("User " + name + " has left");
                    ToClientSocket.close();
                    break;
                }

                if(input.startsWith("@senduser")){
                    TargetName = input.substring(10);
                    input = in.readUTF();
                    synchronized (ClientMap) {
                        out = new DataOutputStream(ClientMap.get(TargetName).getOutputStream());
                        out.writeUTF(name+ ": " + input);
                    }
                }

                else {
                    synchronized (ClientMap) {
                        for (Object key : ClientMap.keySet()) {
                            if (!key.equals(name)) {
                                out = new DataOutputStream(ClientMap.get(key).getOutputStream());
                                out.writeUTF(name + ": " + input);
                            }
                        }
                    }
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
