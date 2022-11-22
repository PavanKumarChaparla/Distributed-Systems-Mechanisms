01.	Program Details:

	(i)	Date: 07th October, 2021
	(ii)	Language: Java

02.	Problem Statement:

	(i)	Message-oriented communication between client and server using single server and single client. Should be able to perform four operations on files.
		a)	UPLOAD a file from client to server
        b)  DOWNLOAD a file from server to client
        c)  RENAME a file 
        d)  DELETE a file 

    (ii)	Message-oriented communication between client and server using single server and multiple client. Should be able to perform four operations on files.
		a)	UPLOAD a file from  multiple clients to server.
        b)  DOWNLOAD a file from server to multiple clients at a time.
        c)  RENAME files respectively in the different client machines connected to Server. 
        d)  DELETE files respectively in the different client machines connected to Server.

    (iii)   Remote procedure call (RPC) based communication.
        a.) Send request from a client with the parameters packed in a client stub.
        b.) Server should receive the client stub along with the request and unpack the parameters using       server stub.
        c.) Server performs operations on the input received along with the operation specified by the client which will be one of 
            - calculate_pi(),
            - add(x, y),
            - matrixMultiply(m1, m2, m3),
            - sortArray(arr)
        d.) Specified Operation is performed and returned to the Client.   


03.	Code Execution:
    Part-1: /DSPROJECT1-A 
        (i)	Before starting,
            you have to check if you have files called client.txt in the clientFiles folder and server.txt in the serverFiles folder.

        (ii)	Also change the directories of those folders and files as per your file directory structure on your macine in Client.java and ServerA.java.

        (iii)	Then, enter 'javac ServerA.java', 'javac Client.java'  to compile these files.
            You can only run these files after compiling them.

        (iv)	Finally, to run these files correctly,
            first, run 'ServerA.class' by entering 'java ServerA' then run 'Client.class' by entering 'java Client'.

    Part-2: /DSPROJECT1-B
        (i)	Before starting,
            you have to check if you have files called client.txt in the clientFiles folder and server.txt in the serverFiles folder.

        (ii)	Also change the directories of those folders and files as per your file directory structure on your macine MultiClient.java and MultiClientHandler.java.

        (iii)	Then, enter 'javac MultiClient.java', 'javac MultiClientHandler.java' and 'javac MultiClientServer.java' to compile these files.
            You can only run these files after compiling them.

        (iv)	Finally, to run these files correctly,
            first, run 'MultiClientServer.class' by entering 'java MultiClientServer' then run 'MultiClient.class' by entering 'java MultiClient'.

    Part-3: /DSPROJECT1-C-D
        (i)	Enter 'javac Add.java', 'javac CalculatePi.java' and 'javac MatrixMultiply.java'  and 'javac SortArray.java' and 'javac Server.java', 'javac Client.java' to compile these files.
            You can only run these files after compiling them.

        (iii)	Finally, to run these files correctly,
            first, run 'Server.class' by entering 'java Server' then run 'Client.class' by entering 'java Client'.                
