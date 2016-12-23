import java.util.Scanner;

/**
 *  Subsection 12.1.3 discusses the need for synchronization in multithreaded 
 *  programs, and it defines a ThreadSafeCounter class with the necessary 
 *  synchronization. Is this really important? Can you really get errors by using 
 *  an unsynchronized count with multiple threads? Write a program to find out. 
 *  Use the following unsynchronized count class, which you can include as
 *  a nested class in your program:
 *
 * static class Counter {
 *   int count;
 *   void inc() {
 *     count = count+1;
 *   }
 *  int getCount() {
 *     return count;
 *   }
 * }
 *
 * Write a thread class that will call the inc() method in this class a specified 
 * number of times. Create several threads, start them all, and wait for all the 
 * threads to terminate. Print the final value of the count, and see whether 
 * it is correct.
 *
 * Let the user enter the number of threads and the number of times that each 
 * thread will increment the count. You might need a fairly large number of 
 * increments to see an error. And of course there can never be any error if you 
 * use just one thread. Your program can use join() to wait for a thread to 
 * terminate (see Subsection 12.1.2).
 *
 */
public class Exercise_12_1 {
  private static int n, // Numbers of threads to be created.
                     m; // Number of Counter.inc() calls by each thread.
  private static Counter counter  = new Counter();
  
  public static void main(String[] args) {
    getNMValues();

    CounterThread[] workers = new CounterThread[n];
    
    for (int i = 0; i < workers.length; i++)
      workers[i] = new CounterThread(m);
    for (int i = 0; i < workers.length; i++)
      workers[i].start();
    
    for (int i = 0; i < workers.length; i++) {
      while (workers[i].isAlive())
        try {
          workers[i].join();
        } catch (InterruptedException e) { }
    }
    
    System.out.print("\nIs the non-synchronized version of the program correct? "); 
    System.out.println( ((n * m)  == counter.getCount() ? " TRUE" : "FALSE") + "\n");
    System.out.println("Total number of threads was " + n + " and number of incrementations ");
    System.out.println("was " + m + ". Total value of the counter should be " + (n * m));
    System.out.println("Value of the counter is: " + counter.getCount());
  }
  
  /**
   * Lets the user to specify number of threads and number of counter's 
   * incrementations.
   */
  private static void getNMValues() {
    Scanner in = new Scanner(System.in);
    
    System.out.println("Specify number of threads");
    n = in.nextInt();
    System.out.println("Specify number of counter's incrementation that each thread will perform.");
    m = in.nextInt();
    
    in.close();
  }
/* ---------  Nested classes  --------- */
  private static class CounterThread extends Thread {
    int iters;
    
    public CounterThread(int n) {
      iters = n;
    }
    
    @Override
    public void run() {
      int i = 0;
      for ( ; i < iters; i++)
        counter.inc();
      // System.out.println("Thread "+ this + " finished with count = " + i);
    }
  }
  
  private static class Counter {
    int count;
    
    int getCount() {
      return count;
    }
    
    synchronized void inc() {
      count = count + 1;
    }
  }
}
