import java.io.*;
import java.net.*;
public class Participant1 implements Runnable
{
    static Socket clientSocket = null;
    static PrintStream os = null;
    static BufferedReader is = null;
    static BufferedReader readInput = null;
    static boolean closed = false;
    public static void main(String[] args){
        String host="localhost";
        try{
            clientSocket = new Socket(host, 1111);
            readInput = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (Exception e){
            System.out.println("Exception occurred : "+e.getMessage());
        }
        if (clientSocket != null && os != null && is != null)
        {
            try
            {
                new Thread(new Participant1()).start();
                while (!closed){
                    os.println(readInput.readLine());
                }
                os.close();
                is.close();
                clientSocket.close();
            }
            catch (IOException e)
            {
                System.err.println("IOException: " + e);
            }
        }
    } 
    public void run(){
        String responseLine;
        try{
            while ((responseLine = is.readLine()) != null){
                System.out.println("\n"+responseLine);
                if (responseLine.equalsIgnoreCase("global_commit")==true || responseLine.equalsIgnoreCase("global_abort")==true ){
                    break;
                }
            }
            closed=true;
        }
        catch (IOException e){
            System.err.println("IOException: " + e);
        }
    }
}
