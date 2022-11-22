import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileLock;


public class FileLocking extends Thread
{
    private static final File file = new File("lock.test");
 
    boolean isFileUnlocked = false;
    public static void main(String[] args) throws Exception
    {
        for (int i = 0; i < 5; i++) {
            FileLocking f = new FileLocking();
            f.start();
        }
    }

 
    public void run()
    {
    	
    	   int counter=0;
       synchronized(file) {
            FileOutputStream fos = null;
            try {
                System.out.println("Wait for a while till you receive the lock.......");
                fos = new FileOutputStream(file);
                FileLock lock = fos.getChannel().lock();
                System.out.println("Got a lock in " + getName());
                BufferedReader reader = new BufferedReader(new FileReader("lockCounters.test"));
               isFileUnlocked=true;
                counter++;
                sleep(10000);            
                reader.close();
                fos.close();
                System.out.println("Lock Released from the Thread -" + getName());
                isFileUnlocked=false;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                   } catch (IOException ex) {
                    }
                }
            }
        }
    }
            
}