diningPhilosophers
==================

Riley Lundquist

Operating Systems & Networking

February 2014

=========


The Dining Philosophers Problem describes a situation in which five philosophers sit about a round table with a large bowl of rice and a single chopstick between each of them. The philosophers think and eat (and wait to eat if they cannot). They run into problems if two adjacent philosophers wish to eat (since neighbors share chopsticks). This therefore turns into an analogy for synchronization in operating systems. The goal is for all philosophers to eat (not starve) and avoid deadlock (the case where a philosopher is waiting on another who is waiting on him in return). Chopsticks represent critical sections in code which must be mutually exclusive. 

In this simulation, the user clicks on a philosopher to indicate that he wishes to eat. If both of his chopsticks are available (neither of his neighbors have them), he begins eating. Otherwise, he stops thinking and begins waiting. At any point when a philosopher is waiting and both of his chopsticks become available, he begins to eat. Here, the solution in which a philosopher may only take his chopsticks if both are available is used. This is particularly useful because it prevents deadlock. Starvation can still occur, however--it is up to the user to make sure that all philosophers get a chance to eat.

The user is presented with a list of the current statuses of each philosopher below the table to help him decide what to do next. A second activity or screen provides a list of all actions made and a table displaying the total time (number of turns) each philosopher has spent in each state.


Future Improvements
-------------------
-asymmetrical solution to the problem as well, so the user could see the differences in approach

-varying number of philosophers

-make the philosophers automatically request to eat randomly and have the user decide who is allowed


Running the Project
-------------------
-install the .apk on an Android device

-touch philosophers to indicate who would like to eat


