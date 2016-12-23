import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Exercise 3.2 asked you to find the integer in the range 1 to 10000 that has 
 * the largest number of divisors. Now write a program that uses multiple threads 
 * to solve the same problem, but for the range 1 to 100000 (or less, if you 
 * don't have a fast computer). By using threads, your program will take less 
 * time to do the computation when it is run on a multiprocessor computer. At 
 * the end of the program, output the elapsed time, the integer that has the 
 * largest number of divisors, and the number of divisors that it has. The program 
 * can be modeled on the sample prime-counting program ThreadTest2.java from 
 * Subsection 12.1.3. For this exercise, you should simply divide up the problem 
 * into parts and create one thread to do each part.
 */
public class Exercise_12_2 {
  private static final int N = 100000;
  private static final int TASK_SIZE = 1000;
  private static final int TASKS = N / TASK_SIZE;
  private static Task[] workers = new Task[TASKS];
  
  public static void main(String[] args) {  
    long start = System.currentTimeMillis();
    
    doTasks();
    checkResult();
    
    System.out.printf("It took %s  milliseconds to do the whole task.",
        System.currentTimeMillis() - start);
  }

  private static void doTasks() {
    for (int i = 0; i < workers.length; i++)
      workers[i] = new Task(i * TASK_SIZE + 1, (i + 1) * TASK_SIZE);
  }

  static void checkResult() {
    int maxInt = 0;
    int maxIntDivisors= 0;
    
    for (int i = 0; i < workers.length; i++) {
      if (workers[i].isAlive())
        try {
          workers[i].join();
        } catch (Exception e) {}  
      if (workers[i].maxIntDivisors > maxIntDivisors) {
        maxInt = workers[i].maxInt;
        maxIntDivisors = workers[i].maxIntDivisors;        
      }
    }
    System.out.printf("Integer with max divisors is %s and has %s divisors. \n",
        maxInt, maxIntDivisors);
  }
  
  /* ----------- Nested Class ------------- */
  
  private static class Task extends Thread {
    private int from;
    private int to;
    int maxInt;
    int maxIntDivisors;
    
    public Task(int from, int to) {
      this.from = from;
      this.to = to;
      start();
    }
    
    public void run() {
      int currDivisors;
      for (int i = from; i <= to; i++) {
        currDivisors = 0;
        for (int j = 1; j <= to; j++) {
          if (i % j == 0)
            currDivisors++;
          if (currDivisors > maxIntDivisors) {
            maxInt = i;
            maxIntDivisors = currDivisors;
          }
        }
      }
    }
  }
}



