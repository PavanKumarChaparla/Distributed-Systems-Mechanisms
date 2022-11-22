import java.io.*;
import java.net.*;


public class MultiClientServer implements Runnable{
	private ServerSocket server = null;
	
	static String filesList = "";
	
	public MultiClientServer(int port) {
		
		try {
			//Connecting Client.
			server = new ServerSocket(port);
//			String serverFile = "C:/Users/NallavellySaiSampree/Desktop/java/DSPROJECT1-A/serverFiles/server.txt";
//			String clientFile = "C:/Users/NallavellySaiSampree/Desktop/java/DSPROJECT1-A/clientFiles/client.txt";
			System.out.println("Server started");
			System.out.println("Waiting for a client ...");

			int counter = 0;
		while(true)
		{
			counter++;

			// Accept client's connection
			Socket s = server.accept();

			// Call thread
			MultiClientHandler st = new MultiClientHandler(s,counter);
			st.start();
            System.out.println("Client " + counter + " Connected");
		}
						
			
			//Closing Connection.
//			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[])
	{
		MultiClientServer multiClientServer = new MultiClientServer(5000);
	}

	@Override
	public void run() {
		
	}
}
