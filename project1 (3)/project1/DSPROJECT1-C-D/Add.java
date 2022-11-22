import java.io.*;
import java.io.*;

public class Add {

    DataOutputStream dataOutput;
    double x;
    double y;

    public Add(String inputParameters, DataOutputStream dataOutput) throws IOException{

        this.dataOutput = dataOutput;
        serverStubForAdd(inputParameters);

    }

    private void serverStubForAdd(String inputParameters) throws IOException{
        System.out.println("Unpacking the parameters for add at server side stub")
        String numbersToAdd[] = inputParameters.split(",");
        x = Double.parseDouble(numbersToAdd[0]);
        y = Double.parseDouble(numbersToAdd[1]);
        add(x, y);
    }

    private void add(double x, double y) throws IOException{
        System.out.println("Operation of add at the Server side.");
        double sum = 0;
        sum = x + y;
        dataOutput.writeDouble(x+y);
    }
}