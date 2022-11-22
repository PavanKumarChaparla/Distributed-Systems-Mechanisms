import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	
	private Socket socket = null;
	private Scanner terminalInput = null;
	private DataOutputStream request = null;
	private DataInputStream response = null;
	
	//Constructor
	public Client(String addr, int port) {
		
		try {
			socket = new Socket(addr,port);
			
			System.out.println("Connected to Server...");

			System.out.println("ENTER ONE OF THE COMMAND FROM BELOW");
			System.out.println("1. UPLOAD\n2. DOWNLOAD\n3. RENAME\n4. DELETE\n5. EXIT");
			
			//Reading the command.
			terminalInput = new Scanner(System.in);
			
			//Getting the Input & Output streams to server.
			request = new DataOutputStream(socket.getOutputStream());
			response = new DataInputStream(socket.getInputStream());
			
			//Sending the request to the server.
			while(true) {
				String requestCmd = terminalInput.nextLine();
				request.writeUTF(requestCmd);
				if(requestCmd.equals("UPLOAD")){
					byte[] myByteArray = new byte[2025];
					FileInputStream fis = new FileInputStream("C:/Users/NallavellySaiSampree/Desktop/java/DSPROJECT1-A/clientFiles/client.txt");
					fis.read(myByteArray, 0, myByteArray.length);
					OutputStream os = socket.getOutputStream();
					os.write(myByteArray, 0, myByteArray.length);
					fis.close();
					System.out.println(response.readUTF());
					System.out.println("ENTER ONE OF THE COMMAND FROM BELOW");
					System.out.println("1. UPLOAD\n2. DOWNLOAD\n3. RENAME\n4. DELETE\n5. EXIT");
				}
				else if(requestCmd.equals("DOWNLOAD")){
					byte[] myByteArray = new byte[2025];
					InputStream is = socket.getInputStream();
					is.read(myByteArray, 0, myByteArray.length);
					FileOutputStream fos = new FileOutputStream("C:/Users/NallavellySaiSampree/Desktop/java/DSPROJECT1-A/clientFiles/server.txt");
					fos.write(myByteArray, 0, myByteArray.length);
					fos.close();
					System.out.println(response.readUTF());
					System.out.println("ENTER ONE OF THE COMMAND FROM BELOW");
					System.out.println("1. UPLOAD\n2. DOWNLOAD\n3. RENAME\n4. DELETE\n5. EXIT");
				}
				else if(requestCmd.equals("RENAME")){
					System.out.println(response.readUTF());
					request.writeUTF(terminalInput.nextLine());
					request.flush();
					System.out.println(response.readUTF());
					System.out.println("ENTER ONE OF THE COMMAND FROM BELOW");
					System.out.println("1. UPLOAD\n2. DOWNLOAD\n3. RENAME\n4. DELETE\n5. EXIT");
				}
				else if(requestCmd.equals("DELETE")){
					System.out.println(response.readUTF());
//					response.flush();
					System.out.println("ENTER ONE OF THE COMMAND FROM BELOW");
					System.out.println("1. UPLOAD\n2. DOWNLOAD\n3. RENAME\n4. DELETE\n5. EXIT");
				}
				else if(requestCmd.equals("EXIT")){
					break;
				}
			}
			
			//Closing Connection.
			socket.close();
			terminalInput.close();
			request.close();
			response.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[])
	{
		//Initiating client.
		Client client = new Client("localhost", 5000);
	}

}
