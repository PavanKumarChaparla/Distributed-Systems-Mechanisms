import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunner extends FileLocking implements Runnable {
	protected Socket clientSocket =null;
	FileLocking fileLock;
	public ClientRunner(Socket clientSocket, FileLocking fileLock) {
	this.clientSocket= clientSocket;
	this.fileLock = fileLock;	
	}
}
