import java.io.*;
import java.net.*;
import java.util.*;
class clientThread extends Thread
    {
        BufferedReader is = null;
        String inputRead;
        String participantName;
        PrintStream os = null;
        Socket clientSocket = null;
        Server server;
        public clientThread(Server server,Socket clientSocket){
            this.clientSocket=clientSocket;
            this.server=server;
        }
        public void run(){
            try{
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintStream(clientSocket.getOutputStream());
                os.println("Please enter the Name of the participant:");
                participantName = is.readLine();
                os.println("Hi "+participantName);
                os.println(" ");
                os.println("You successfully entered the application. Now make a choice either to proceed or go against.");
                os.println("Please select any option of the below items.");
                os.println("1.) Enter 1 to COMMIT ");
                os.println("2.) Enter 2 to ABORT ");
                for(int i=0; i<(server.clientThread).size(); i++){
                    if((server.clientThread).get(i)!=this){
                        ((server.clientThread).get(i)).os.println(""+participantName+" has connected to the Appilcation");
                    }
                }
                long endTimeMillis = System.currentTimeMillis() + 30000;
                while (true){
                    if((System.currentTimeMillis()-endTimeMillis) > 0) {
                        System.out.println("Aborting because some of the participants didnot respond within the time limit 30 seconds. Issuing a Timeout and Global Abort....");
                        System.out.println("\nAborted");
                        for(int i=0; i<(server.clientThread).size(); i++){
                            ((server.clientThread).get(i)).os.println("global_abort");
                            ((server.clientThread).get(i)).os.close();
                            ((server.clientThread).get(i)).is.close();
                        }
                        break;
				    }
                    if(is.ready()){
                        inputRead = is.readLine();
                        if(inputRead.equals("2")){
                            System.out.println("'"+participantName+"' has chosen to ABORT. Thus the co-ordinator will abort the process and not wait for inputs from other participants.");
                            System.out.println("");
                            System.out.println("Aborted....");
                            for(int i=0; i<(server.clientThread).size(); i++){
                                ((server.clientThread).get(i)).os.println("global_abort");
                                ((server.clientThread).get(i)).os.close();
                                ((server.clientThread).get(i)).is.close();
                            }
                            break;
                        }
                        if(inputRead.equals("1")){
                                System.out.println("'"+participantName+"' has chose to commit");
                            if((server.clientThread).contains(this))
                            {
                                (server.phase).set((server.clientThread).indexOf(this), "commit");
                                for(int j=0;j<(server.phase).size();j++)
                                {
                                    if(!(((server.phase).get(j)).equals("Initial_connection")))
                                    {
                                        server.inputFromAll=true;
                                        continue;
                                    }
                                    else
                                    {
                                        server.inputFromAll=false;
                                        System.out.println("\nWaiting for inputs from other participants.");
                                        break;
                                    }
                                }
                                if(server.inputFromAll)
                                {
                                    System.out.println("\n All the Participants Committed");
                                    System.out.println("");
                                    System.out.println("The operation can be processed and the participants can release the locks.");
                                    System.out.println("");
                                    for(int i=0; i<(server.clientThread).size(); i++)
                                    {
                                        ((server.clientThread).get(i)).os.println("global_commit");
                                        ((server.clientThread).get(i)).os.close();
                                        ((server.clientThread).get(i)).is.close();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                server.closed=true;
                clientSocket.close();
            }
            catch(IOException e){
            };
        }
    }