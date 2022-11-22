import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SortArray {

    DataOutputStream dataOutput;
    double[] array;

    public SortArray(String inputs, DataOutputStream dataOutput) throws IOException{
        this.dataOutput = dataOutput;
        serverStubForSortArray(inputs);
    }

    private void serverStubForSortArray(String inputs) throws IOException{
        System.out.println("Server stub unpacking the client stub parameters.");
        String numbersInArray[] = inputs.split(",");
        System.out.println(numbersInArray);
        array = new double[numbersInArray.length];
        for(int i=0; i< numbersInArray.length ; i++){
            array[i] = Double.parseDouble(numbersInArray[i]);
        }
        sortArray(array);

    }

    private void sortArray(double[] arr) throws IOException{
        System.out.println("Sorting Service in the server side");
        Arrays.sort(arr);  
        System.out.println(arr[0]+'-'+arr[1]);      
        for(int i=0 ; i< array.length ; i++){
            dataOutput.writeDouble(array[i]);
        }
    }
}