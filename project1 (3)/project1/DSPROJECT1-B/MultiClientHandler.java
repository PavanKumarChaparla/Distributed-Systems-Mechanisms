import java.io.*;
import java.net.Socket;

public class MultiClientHandler extends Thread
{
	Socket socket;
	int clientNo;
	String requestCmd = "";

	MultiClientHandler(Socket insocket, int client)
	{
		socket = insocket;
		clientNo = client;
	}

	public void run()
	{
		try
		{
			String serverFile = "C:/Users/PavanChaparla/Desktop/java/DSPROJECT1-A/serverFiles/server.txt";
			String clientFile = "C:/Users/PavanChaparla/Desktop/java/DSPROJECT1-A/clientFiles/client.txt";
			
			// Create text output and input streams
			DataInputStream requestFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream responseToClient = new DataOutputStream(socket.getOutputStream());


			while(true)
			{
				
				requestCmd = requestFromClient.readUTF();

                if(requestCmd.equals("UPLOAD")){
					byte[] myByteArray = new byte[2025];
					InputStream is = socket.getInputStream();
					is.read(myByteArray, 0, myByteArray.length);
					FileOutputStream fos = new FileOutputStream("C:/Users/PavanChaparla/Desktop/java/DSPROJECT1-A/serverFiles/client.txt");
					fos.write(myByteArray, 0, myByteArray.length);
					fos.close();
					responseToClient.writeUTF("FILE SUCCESFULLY UPLOADED FROM CLIENT TO SERVER");
					responseToClient.flush();
				}
				else if(requestCmd.equals("DOWNLOAD")){
					byte[] myByteArray = new byte[2025];
					FileInputStream fis = new FileInputStream("C:/Users/PavanChaparla/Desktop/java/DSPROJECT1-A/serverFiles/server.txt");
					fis.read(myByteArray, 0, myByteArray.length);
					OutputStream os = socket.getOutputStream();
					os.write(myByteArray, 0, myByteArray.length);
					fis.close();
					responseToClient.writeUTF("FILE SUCCESFULLY DOWNLOADED FROM SERVER TO CLIENT");
					responseToClient.flush();
				}
				else if(requestCmd.equals("RENAME")){
					byte[] myByteArray = new byte[2025];
					responseToClient.writeUTF("ENTER THE NEW FILE NAME:");
					responseToClient.flush();
					String newFileName = requestFromClient.readUTF();
					FileInputStream fis2 = new FileInputStream(clientFile);
					fis2.read(myByteArray, 0, myByteArray.length);
					newFileName = "C:/Users/PavanChaparla/Desktop/java/DSPROJECT1-A/clientFiles/"+newFileName+".txt";
					FileOutputStream fos = new FileOutputStream(newFileName);
					fos.write(myByteArray, 0, myByteArray.length);
					fis2.close();
					fos.close();
					File file = new File(clientFile);
					file.delete();
					clientFile=newFileName;
					responseToClient.writeUTF("FILE SUCCESFULLY RENAMED");
					responseToClient.flush();
				}
				else if(requestCmd.equals("DELETE")){
					File deleteFile = new File(clientFile);
					deleteFile.delete();
					responseToClient.writeUTF("FILE SUCCESFULLY DELETED");
					responseToClient.flush();
				}
				else if(requestCmd.equals("EXIT")){
					break;
				}
			}
		}

		catch (Exception ex)
		{
			System.out.println(ex);
		}

		finally
		{
			System.out.println("Client: "+clientNo+" Exit");
		}
	}
}