import java.io.*;
import java.io.*;

public class CalculatePi {

    DataOutputStream dataOutput;
    public CalculatePi(DataOutputStream dataOutput) throws IOException{

        System.out.println("Server stub unpacking parameters.");
        this.dataOutput = dataOutput;
        calcultePi();
    }

    private void calcultePi() throws IOException{
        System.out.println("Operation :- calcultePi on server");
        double x;
        double y;
        int successCount = 0;
        for (int i = 0; i <= 100000; i++){
            x = Math.random();
            y = Math.random();
            if ((Math.pow(x, 2) + Math.pow(y, 2)) <= 1){
                successCount++;
            }
        }
	    double returnValue = (double) (4 * successCount) / 100000;
        dataOutput.writeDouble(returnValue);
    }
}