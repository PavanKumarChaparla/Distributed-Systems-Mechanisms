import java.io.*;
import java.net.*;
import java.util.*;

class Client2 extends Thread
{

	private Thread startThread;
	private String nameOfThread;
	int logicalClockValue = 11;

	Client2(String name)
	{
			 
		this.nameOfThread = name;
		if(this.startThread == null)
		{
		      	 
		  	 this.startThread = new Thread(this, nameOfThread);
		     this.startThread.start();
		}
	}
	void callCLient() throws IOException, InterruptedException
	{
		DatagramPacket connection = null;
		Thread.sleep(10000);
		int i;
		int size = 3;
		byte outputData[] = new byte[100];
		DatagramSocket client = new DatagramSocket();
		String sendingArray[] = new String[] { "D", "E", "F" };
		String inputData;
		size=sendingArray.length;
		
		for(i = 0; i<size; i++)
		{
			String processName="Process 2: ";
			logicalClockValue++;
			inputData = processName.concat(sendingArray[i]);
			inputData = inputData.concat("," + logicalClockValue);
			outputData = inputData.getBytes();
			connection = new DatagramPacket(outputData, outputData.length, InetAddress.getByName("localhost"), 8080);
			client.send(connection);

			connection = new DatagramPacket(outputData,outputData.length,InetAddress.getByName("localhost"), 8081);
			client.send(connection);  
			
			connection = new DatagramPacket(outputData,outputData.length,InetAddress.getByName("localhost"),8082);
			client.send(connection);
			connection=null;
			
		}

	}
	void callServer() throws IOException
	{
			
			
		String dataString = "";
		int i=0;
		String receivedDataArray[] = new String[100];
		int logicalTimeStamp[] = new int[50];
		String timeStampsArray[] = new String[50];
			
		DatagramSocket server = new DatagramSocket(8081);
		for(i = 0; i<9; i++)
		{
			byte receive[] = new byte[500];
			DatagramPacket receivedData = new DatagramPacket(receive, receive.length);
			server.receive(receivedData);
			String line = new String(receivedData.getData(),"UTF-8");
			dataString = line.substring(0,line.indexOf(','));
			String timeStamp = line.substring(line.indexOf(',')+1);
			receivedDataArray[i] = dataString;
			timeStampsArray[i] = timeStamp;
		}
		displayData(receivedDataArray,timeStampsArray);
	}
	void displayData(String receivedDataArray[], String time[])
	{
		for(int i = 0; i<3; i++)
		{
			HashMap<String,String> map = new HashMap<String, String>();
			map.put(time[i],receivedDataArray[i]);
			Map<String,String> treeMap = new TreeMap<String,String>(map);
			for(Map.Entry<String,String> entry : treeMap.entrySet()){
				int j = i+1;
				System.out.println("Key delivered between Process P2:"+"P"+j+"-"+entry.getKey());
			}
		}	
	}
	public void run()
	{
			try{
			if(nameOfThread.equalsIgnoreCase("Client"))
			{
				callCLient();
			}
			else
			{
				callServer();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void main(String[] args) throws IOException
	{
				System.out.println("Starting the Processess and Communication. Please Wait While this might take a while....");
		Client2 P1= new Client2("Client");
		Client2 P2= new Client2("Sender");
	}	
}