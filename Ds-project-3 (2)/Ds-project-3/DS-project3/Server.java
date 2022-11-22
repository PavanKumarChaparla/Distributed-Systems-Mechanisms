
import java.io.*;
import java.net.*;
import java.util.*;
public class Server{
    boolean closed=false;
    boolean inputFromAll=false;
    List<clientThread> clientThread; 
    List<String> phase;
    Server(){
        clientThread = new ArrayList<clientThread>();
        phase= new ArrayList<String>();
    }
    public static void main(String args[]){
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        Server server=new Server();

        try{
            serverSocket = new ServerSocket(1111);
        }catch (IOException e){
            System.out.println(e);
        }
        while(!server.closed){
            try{
                clientSocket = serverSocket.accept();
                clientThread th=new clientThread(server,clientSocket);
                (server.clientThread).add(th);
                System.out.println("No. of ParticipantClients Connected: "+(server.clientThread).size());
                (server.phase).add("Initial_connection");
                th.start();
            }catch (Exception e){
                System.out.println(e);
                }
        }

        try{
            serverSocket.close();
        }catch(Exception e1){
            }
    }
}
    