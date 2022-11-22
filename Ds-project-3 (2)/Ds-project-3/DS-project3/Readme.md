01.	Program Details:

	(i)	Date: 5th December, 2021
	(ii)	Language: Java

02.	Problem Statement:

	(i)	Implement a 2-phase distributed commit (2PC) protocol and use 
        controlled and randomly injected failures to study how the 2PC protocol handles node crashes. 
        Assume one coordinator and at least three participants in the 2PC protocol.  Similar to the 
        previous projects, we use multiple processes to emulate multiple nodes. Vote requests and 
        responses should be carried out using communications. Each node (both the coordinator and 
        the participants) devises a time-out mechanism when no response is received and transits to 
        either the abort or commit state. Design a controlled failure test to evaluate whether the 
        implemented 2PC protocol leads to consistent states across the coordinator and participants. 
        For simplicity, you can assume that only one node fails in the controlled test. Evaluate different 
        possibilities of failures (e.g., coordinator fails before or after sending vote-commit). To emulate a 
        failure, you can impose a much longer delay at a failed node than the time-out period used by 
        other healthy nodes. Node print their states before termination. Verify all nodes converge to the 
        same state regardless of the failure.  
        
        Furthermore, evaluate the 2PC protocol by randomly injecting failures to any nodes (e.g., a node 
        may be delayed emulating a failure with a probability at any point during execution). Verify the 
        terminal state to ensure consistency.


03.	Code Execution:
    Part-1: /Ds-project3
        (i)	Before starting,
            you have to check if you have files called Server.java, clientThread.java, participant1.java, participant2.java, participant3.java in the folder.

        (ii)	Then, enter 'javac Server.java', 'javac Participant1.java',  'javac Participant2.java',  'javac Participant3.java' to compile these files in four different terminals.
            You can only run these files after compiling them.

        (iv)	Finally, to run these files correctly,
            first, run 'Server.class' by entering 'java Server' then run 'Participant1.class' by entering 'java Participant1' and respectively 'Participant2.class' and 'Participant3.class'.

        (v)  In all the three participants make a choice to "COMMIT" or "ABORT" for an operation "vote request" received from the co-ordinator which is the "Server". 

        (vi) Once you make the choice in all the participant terminals the server will make a decision to abort or commit.

        (vii) If you select "ABORT" in any participant the "coordinator" will abort the process and send a "GLOBAL_ABORT" message to all the nodes and exits the connections.

        (viii) If all the participants choose to "COMMIT" the "coordinator" will choose to commit the process and the participants will perform the operation and release all the locks.

        (iX) Inorder to determine a condition for failure we have added a time constraint of "30 secs" to all participants to respond to the "coordinator" which is the server.

        (x) Try not making any choice in one of the participant and wait for 30 seconds. The coordinator will "ABORT" the process and send a "GLOBAL_ABORT" to all the other nodes even though they agreed to "COMMIT" and thus terminates the operation.

        (xi) SInce it is 2-phase the co-ordinator if fails after sending the initial vote requests to the other nodes, It will have to wait till recovering and then take a decision. Thus we can also try timing out the co-ordinator.
