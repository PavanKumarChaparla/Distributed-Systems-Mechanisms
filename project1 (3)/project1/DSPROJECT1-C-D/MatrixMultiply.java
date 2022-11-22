import java.io.*;
import java.io.*;

public class MatrixMultiply {

    DataOutputStream dataOutput;
    private int r1;
    private int c1;
    private int r2;
    private int c2;
    private int r3;
    private int c3;
    private int[][] M1;
    private int[][] M2;
    private int[][] M3;
    private String result ="";


    public MatrixMultiply(String input, DataOutputStream dataOutput) throws IOException{

        this.dataOutput = dataOutput;
        serverStubForMatrixMultiply(input);

    }

    private void serverStubForMatrixMultiply(String input) throws IOException{

        System.out.println("Server stub unpacking parameters.");
        String[] matrices = input.split("matrix");
        String rowsOfM1[] = matrices[1].split("row");
        String rowsOfM2[] = matrices[2].split("row");
        String rowsOfM3[] = matrices[3].split("row");

        r1 = rowsOfM1.length-1;
        r2 = rowsOfM2.length-1;
        r3 = rowsOfM3.length-1;

        String rowElementsOfM1[][] = new String[r1][];
        for (int i=1; i<= r1; i++){
            rowElementsOfM1[i-1] = rowsOfM1[i].split(",");
        }

        String rowElementsOfM2[][] = new String[r2][];
        for (int i=1; i<= r2; i++){
            rowElementsOfM2[i-1] = rowsOfM2[i].split(",");
        }

        String rowElementsOfM3[][] = new String[r3][];
        for (int i=1; i<= r3; i++){
            rowElementsOfM3[i-1] = rowsOfM3[i].split(",");
        }

        c1 = rowElementsOfM1[0].length;
        c2 = rowElementsOfM2[0].length;
        c3 = rowElementsOfM3[0].length;

        M1 = new int[r1][c1];
        M2 = new int[r2][c2];
        M3 = new int[r3][c3];

        constructMatrix(M1, rowElementsOfM1, r1, c1);
        constructMatrix(M2, rowElementsOfM2, r2, c2);
        constructMatrix(M3, rowElementsOfM3, r3, c3);
        matrixMultiply(M1, M2, M3);
    }

    private void constructMatrix(int[][] matrix, String[][] rowElementsOfMatrix, int rows, int cols) {
        System.out.println("\n Matrix::");
        for (int i=0; i < rows; i++){
            System.out.println();
            for (int j=0; j< cols; j++){
                matrix[i][j] = Integer.parseInt(rowElementsOfMatrix[i][j]);
                System.out.print(matrix[i][j] + " ");
            }
        }
    }

    private void matrixMultiply(int[][] M1, int[][] M2, int[][] M3) throws IOException{

        System.out.println("Matrix Multiplication at the serverSide.");
        if( c1 == r2 && c2 == r3){

            result += "Answer:-";
            dataOutput.writeUTF(result);
            int[][] interMediateResult = new int[r1][c2];
            int[][] finalResult = new int[r1][c3];

            for (int i=0; i< r1; i++){
                for (int j=0; j< c2; j++){
                    for (int k=0; k<r2; k++){
                        interMediateResult[i][j] += M1[i][k] * M2[k][j];
                    }
                }
            }
            for (int i=0; i< r1; i++){
                System.out.println();
                for (int j=0; j< c3; j++){
                    for (int k=0; k<r3; k++){
                        finalResult[i][j] += interMediateResult[i][k] * M3[k][j];
                    }
                    System.out.print(finalResult[i][j] + " ");
                    dataOutput.writeInt(finalResult[i][j]);
                }
            }

        }
        else {
            result += "Invalid Matrices Submitted.";
            System.out.println(result);
            dataOutput.writeUTF(result);
        }

    }
}