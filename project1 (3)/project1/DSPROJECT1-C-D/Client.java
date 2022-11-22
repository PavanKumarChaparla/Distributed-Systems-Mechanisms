import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket = null;
    private DataInputStream dataInput = null;
    private DataOutputStream dataOutput = null;
    private int selectedOption = 0;
    private String clientDataForOperation = "";
    private int n=0, r1=0, c1=0, r2=0, c2=0, r3=0, c3=0;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to Server......");
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());
            displayOptions();
        } catch (IOException i) {
            System.out.println(i);
        } finally {
            try {
                dataInput.close();
                dataOutput.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayOptions() throws IOException{

        Scanner terminalInput = new Scanner(System.in);
        System.out.println("Choose a number in the following options:");
        System.out.println("1.calculate_pi()");
        System.out.println("2.add(a , b)");
        System.out.println("3.sort(arr)");
        System.out.println("4.matrix_multiply(m1, m2, m3)");

        selectedOption = Integer.parseInt(terminalInput.nextLine());

        switch (selectedOption){
            case 1:
                    System.out.println("You Chose Option 1");
                    calculatePiClientStub("calculate_pi");
                    System.out.println("Result obtained from server: ");
                    System.out.println(dataInput.readDouble());
                    break;

            case 2:
                    System.out.println("You Chose Option 2");
                    System.out.println("Number 1: ");
                    double a = terminalInput.nextDouble();
                    System.out.println("Number 2: ");
                    double b = terminalInput.nextDouble();
                    AddClientStub("add", a, b);
                    System.out.println("Sum = ");
                    System.out.println(dataInput.readDouble());
                    break;

            case 3:
                    System.out.println("You Chose Option 3");
                    System.out.println("Enter no.of elements to be inserted in the Array");
                    int arrayLength = terminalInput.nextInt();
                    double[] array = new double[arrayLength];
                    System.out.println("Enter " + arrayLength + "values of the array.");
                    for (int i=0; i < arrayLength; i++) {
                        array[i] = terminalInput.nextDouble();;
                    }
                    clientDataForOperation += "sortArray-";
                    for(int i=0; i< array.length-1; i++)
                    {
                        clientDataForOperation += array[i]+",";
                    }
                    clientDataForOperation += array[array.length-1];
                    dataOutput.writeUTF(clientDataForOperation);
                    System.out.print("Sorted Array: ");
                    for (int i=0; i < arrayLength; i++){
                        System.out.print(dataInput.readDouble()+" ");
                    }
                    break;
            case 4:
                    System.out.println("You Chose Option 4");
                    System.out.println("No. of rows and columns for matrix M1:");
                    r1 = terminalInput.nextInt();
                    c1 = terminalInput.nextInt();
                    int[][] M1 = new int[r1][c1];
                    System.out.println("Enter values of M1 of " + r1 + " rows and "+ c1 + " columns.");
                     for (int i=0; i<r1; i++){
                        for (int j=0; j<c1; j++){
                            M1[i][j] = terminalInput.nextInt();
                        }
                    }

                    System.out.println("No. of rows and columns for matrix M2:");
                    r2 = terminalInput.nextInt();
                    c2 = terminalInput.nextInt();
                    int[][] M2 = new int[r2][c2];
                    System.out.println("Enter elements of M2 of " + r2 + " rows and "+ c2 + " columns.");
                    for (int i=0; i<r2; i++){
                        for (int j=0; j<c2; j++){
                            M2[i][j] = terminalInput.nextInt();
                        }
                    }

                    System.out.println("No. of rows and columns for matrix M3:");
                    r3 = terminalInput.nextInt();
                    c3 = terminalInput.nextInt();
                    int[][] M3 = new int[r3][c3];
                    System.out.println("Enter elements of M3 of " + r3 + " rows and "+ c3 + " columns.");
                    for (int i=0; i<r3; i++){
                        for (int j=0; j<c3; j++){
                            M3[i][j] = terminalInput.nextInt();
                        }
                    }

                    MatrixMultiplyStub("matrixMultiply", M1, M2, M3);
                    System.out.println("Matrix multiplication result returned from server:");
                    String[] returnStatement = dataInput.readUTF().split(" ");
                    if (returnStatement[0].equals("Invalid")){
                        System.out.println(returnStatement[1]);
                    }
                    else {
                        System.out.println(returnStatement[0] + returnStatement[1]);
                        for (int i=0; i < r1; i++){
                            System.out.println();
                            for (int j=0; j< c3; j++){
                                System.out.print(dataInput.readInt() + " ");
                            }
                        }
                    }
                    break;

            default:
                    System.out.println("You Entered a Wrong Value");
                    break;
        }
    }

    private void calculatePiClientStub(String optionChosen) throws IOException{
        dataOutput.writeUTF(optionChosen);
    }

    private void AddClientStub(String optionChosen, double a, double b) throws IOException {
        clientDataForOperation += "add-";
        clientDataForOperation += a + ",";
        clientDataForOperation += b;
        dataOutput.writeUTF(clientDataForOperation);
    }

    private void MatrixMultiplyStub(String multiply, int[][] M1, int[][] M2, int[][] M3) throws IOException {

        clientDataForOperation += multiply+"-matrix";
        for (int i = 0; i<r1; i++){
            clientDataForOperation += "row";
            for (int j=0; j< c1-1; j++){
                clientDataForOperation += M1[i][j]+",";
            }
            clientDataForOperation += M1[i][c1-1];
        }
        clientDataForOperation += "matrix";
        for (int i = 0; i<r2; i++){
            clientDataForOperation += "row";
            for (int j=0; j< c2-1; j++){
                clientDataForOperation += M2[i][j]+",";
            }
            clientDataForOperation += M2[i][c2-1];
        }
        clientDataForOperation += "matrix";
        for (int i = 0; i<r3; i++){
            clientDataForOperation += "row";
            for (int j=0; j< c3-1; j++){
                clientDataForOperation += M3[i][j]+",";
            }
            clientDataForOperation += M3[i][c3-1];
        }
        dataOutput.writeUTF(clientDataForOperation);
    }

    public static void main(String args[])
    {
            Client client = new Client("localhost", 5000);
    }
}