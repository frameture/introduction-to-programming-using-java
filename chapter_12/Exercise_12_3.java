import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * In the previous exercise, you divided up a large task into a small number of 
 * large pieces and created a thread to execute each task. Because of the nature 
 * of the problem, this meant that some threads had much more work to do than 
 * others -- it is much easier to find the number of divisors of a small number 
 * than it is of a big number. As discussed in Subsection 12.3.1, a better approach 
 * is to break up the problem into a fairly large number of smaller problems. 
 * Subsection 12.3.2 shows how to use a thread pool to execute the tasks: Each 
 * thread in the pool runs in a loop in which it repeatedly takes a task from 
 * a queue and carries out that task. Implement a thread pool strategy for solving 
 * the same maximum-number-of-divisors problem as in the previous exercise.
 *
 * To make things even more interesting, you should try a new technique for 
 * combining the results from all the tasks: Use two queues in your program. Use 
 * a queue of tasks, as usual, to hold the tasks that will be executed by the 
 * thread pool (Subsection 12.3.2). But also use a queue of results produced by 
 * the threads. When a task completes, the result from that task should be placed 
 * into the result queue. The main program can read results from the second queue 
 * as they become available, and combine all the results to get the final answer. 
 * The result queue will have to be a blocking queue (Subsection 12.3.3), since 
 * the main program will have to wait for results to become available. Note that 
 * the main program knows the exact number of results that it expects to read 
 * from the queue, so it can do so in a for loop; when the for loop completes, 
 * the main program knows that all the tasks have been executed.
 */
public class Exercise_12_3 {
  private static TaskThread[] workers;
  private static ConcurrentLinkedQueue<Task> queue;
  private static LinkedBlockingQueue<Result> results;
  private static final int N = 100009;
  private static final int TASK_SIZE = 1000;
  private static final int TASKS = N / TASK_SIZE;

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    
    queue = new ConcurrentLinkedQueue<Task>();
    results = new LinkedBlockingQueue<Result>();
  
    divideTasks();
    createThreads();
    checkResults();
    
    System.out.printf("It took %s  milliseconds to do the whole task.", 
        System.currentTimeMillis() - start);
        
  }
  
  private static void divideTasks() {
    for (int i = 0; i < TASKS;  i++)
      queue.add(new Task((i * TASK_SIZE) + 1, (i + 1) * TASK_SIZE));
  }
  
  static void createThreads() {
    workers = new TaskThread[Runtime.getRuntime().availableProcessors() * 2];
      
    for (int i = 0; i < workers.length; i++) {
      workers[i] = new TaskThread();
      workers[i].start();
    }
  }
  
  private static void checkResults() {
    int maxInt = 0;
    int maxIntDivisors = 0;
    
    for (int i = 0; i < TASKS; i++)
      try {
        Result result = results.take();
        if (maxIntDivisors < result.maxIntDivisors) {
          maxInt = result.maxInt;
          maxIntDivisors = result.maxIntDivisors;
        }
      } catch (InterruptedException e) {}
    System.out.printf("Integer with max divisors is %s and has %s divisors. \n", 
        maxInt, maxIntDivisors);
  }
  
  /* -------------- Nested Classes ---------------- */
  
  private static class TaskThread extends Thread {  
    public void run() {
      Task task = queue.poll();
      while (task != null) {
        task.compute();
        results.add(new Result(task.maxInt, task.maxIntDivisors));
        task = queue.poll();
      }
    }  
  }
  
  private static class Result {  
    int maxInt;
    int maxIntDivisors;
    
    public Result(int maxInt, int maxIntDivisors) {  
      this.maxInt = maxInt;
      this.maxIntDivisors = maxIntDivisors;
    }
  }
  
  private static class Task {   
    private int from;
    private int to;
    int maxInt;
    int maxIntDivisors;
    
    public Task(int from, int to) {
      this.from = from;
      this.to = to;
    }
    
    private void compute() {
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

