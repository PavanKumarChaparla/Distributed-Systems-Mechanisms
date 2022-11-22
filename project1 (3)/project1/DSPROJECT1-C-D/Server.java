import java.io.*;
import java.net.*;

public class Server {

    private ServerSocket serverSocket = null;

    public Server(int port) throws IOException{

        serverSocket = new ServerSocket(port);

        while(true){
            System.out.println("Server Started");
            System.out.println("Waiting for the CLient");
            Socket socket = serverSocket.accept();
            System.out.println("Connection with client established");
            ThreadHandler serverThread = new ThreadHandler(socket);
            serverThread.start();
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}