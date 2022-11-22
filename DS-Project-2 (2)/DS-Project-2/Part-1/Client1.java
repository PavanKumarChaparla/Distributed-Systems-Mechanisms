import java.io.*;
import java.net.*;
import java.util.*;

class Client1 extends Thread
{

	private Thread startThread;
	private String nameOfThread;
	int logicalClock = 10;

	Client1(String name)
	{
		this.nameOfThread = name;
		if(this.startThread == null)
		{
		  	 this.startThread = new Thread(this, name);
		   	 this.startThread.start();
		}
	}
	void callCLient() throws IOException, InterruptedException
	{
		DatagramPacket connection = null;
		byte outputData[] = new byte[100];
		Thread.sleep(10000);
		int i;
		int size;
		String sendingArray[] = new String[] { "A", "B", "C" };
		size=sendingArray.length;
		String inputData;
		DatagramSocket client = new DatagramSocket();
		for(i = 0; i<size; i++)
		{
			String processName=" Process 1: ";
			logicalClock++;
			inputData = processName.concat(sendingArray[i]);
			inputData = inputData.concat("," + logicalClock);
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
		String receivedDataArray[] = new String[50];
		int logicalTimeStamp[] = new int[40];
		String timeStampsArray[] = new String[30];
			
		DatagramSocket server = new DatagramSocket(8080);
		for(i = 0; i<9; i++)
		{
			byte receive[] = new byte[400];
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
			map.put(time[i], receivedDataArray[i]);
			Map<String,String> treeMap = new TreeMap<String,String>(map);
			for(Map.Entry<String,String> entry : treeMap.entrySet()){
				int j = i+1;
				System.out.println("Key delivered between Process P1:"+"P"+j+"-"+entry.getKey());
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
		Client1 P1= new Client1("Client");
		Client1 P2= new Client1("Server");
	}	
}