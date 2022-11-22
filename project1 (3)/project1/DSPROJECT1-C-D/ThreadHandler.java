import java.io.*;
import java.net.Socket;
    
class ThreadHandler extends Thread{
        DataOutputStream dataOutput = null;
        DataInputStream dataInput = null;
        Socket socket = null;
        ThreadHandler(Socket socket) throws IOException{
            this.socket = socket;
            this.dataInput = new DataInputStream(socket.getInputStream());
            this.dataOutput = new DataOutputStream(socket.getOutputStream());
        }
        public void run(){

            try {

                String client = dataInput.readUTF();
                System.out.println("client: " +client);
                String input[] = client.split("-");

                if(input[0].equals("calculate_pi")){
                    new CalculatePi(dataOutput);
                }

                else if(input[0].equals("add")){
                    new Add(input[1], dataOutput);
                }

                else if(input[0].equals("sortArray")){
                    new SortArray(input[1], dataOutput);
                }

                else if(input[0].equals("matrixMultiply")){
                    new MatrixMultiply(input[1], dataOutput);
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                    dataOutput.close();
                    dataInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Client has been disconnected");
        }

    }