In Exercise 11.3, you wrote a network server program that can send text files 
from a specified directory to clients. That program used a single thread, which 
handled all the communication with each client. Modify the program to turn it 
into a multithreaded server. Use a thread pool of connection-handling threads 
and use an ArrayBlockingQueue to get connected sockets from the main() routine 
to the threads. The sample program DateServerWithThreads.java from Subsection 
12.4.3 is an example of a multithreaded server that works in this way. Your 
server program will work with the same client program as the original server. 
You wrote the client program as the solution to Exercise 11.4.