01.	Program Details:

	(i)	Date: 5th November, 2021
	(ii)	Language: Java

02.	Problem Statement:

	(i)	Implement  totally  ordered  multicasting  using  Lamport’s  algorithm.  Each  process  conducts  local operations and numbers them as PID.EVENT_ID. After each operation is done, a process multicasts  the  event  to  all  other  processes  in  the  distributed  system.  The  expected  outcome  of this  assignment  is  that  events  occurred  at  different  processes  will  appear  in  the  same  order  at each  individual  process.  To  realize  such  a  total  order  of  events,  each  process maintains  a  buffer for  received  events  and  follow  the  rules  on  slide  19  in  Lecture  6  when  delivering  the  events.  In this  assignment,  the  delivery  of  events  is  simply  printing  them  on  screen,  in  the  format  of CURRENT_PID:  PID.EVENT_ID.  

    (ii) Implement  the  vector  clock  algorithm  to  enable  causally-ordered  events  in  the distributed system. Similar to the previous assignment, we use multiple processes to emulate multiple nodes. Assume that all nodes are initiated to the same vector clock. After completing  a local operation,each process sends its updated vector clock to all other processes. Follow the steps on slide 34 in Lecture 6 to implement the vector clock algorithm.  

    (iii) Implement  a  locking  scheme  to  protect  a  share  file  in  your  distributed  system.  While  we  use processes  on  a  single  machine  to  emulate  the  distributed  system  and  there  are  shared-memory synchronization primitives available, you are required to implement one of the following distributed  locking  schemes:  centralized,  decentralized,  distributed  locking  or  the  token  ring algorithm. When a process acquires the lock, it simply opens the file, increments a counter in the file,  and  closes  the  file.  Assume  that  all  processes  keep  requesting  the  lock  until  successfully acquiring the lock for a predefined number of times.  


03.	Code Execution:
    Part-1: /DSPROJECT2-Part 1 
        (i)	Before starting,
            you have to check if you have files called client1.java, client2.java, client3.java in the folder.

        (ii)	Then, enter 'javac Client1.java', 'javac Client2.java',  'javac Client3.java' to compile these files.
            You can only run these files after compiling them.

        (iv)	Finally, to run these files correctly,
            first, run 'Client1.class' by entering 'java Client1' then run 'Client2.class' by entering 'java Client2' and respectively 'Client3.class'.

    Part-2: /DSPROJECT2-Part 2
        (i)	Before starting,
            you have to check if you have files called client1.java, client2.java, client3.java in the folder.

        (ii)	Then, enter 'javac Client1.java', 'javac Client2.java',  'javac Client3.java' to compile these files.
            You can only run these files after compiling them.

        (iv)	Finally, to run these files correctly,
            first, run 'Client1.class' by entering 'java Client1' then run 'Client2.class' by entering 'java Client2' and respectively 'Client3.class'.

    Part-3: /DSPROJECT2-Part 3
        (i)	Enter 'javac ClientRunner.java'
        (ii) This will generate a ClientRunner.class file which whill also generate the 'FileLocking.class' through which the locks for the file will be given from co-ordinator.
        (iii)	Finally, to run the files correctly,
             run 'ClientRunner.class' by entering 'java ClientRunner'. You can run multiple CLients at a time to see how the locks are given simultaneously to each client one by one by the filelocker coordinator.
