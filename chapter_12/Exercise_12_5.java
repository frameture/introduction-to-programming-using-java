import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * It is possible to get an estimate of the mathematical constant PI by using 
 * a random process. The idea is based on the fact that the area of a circle of 
 * radius 1 is equal to PI, and the area of a quarter of that circle is PI/4.
 *
 * The area of the whole square is one, while the area of the part inside the 
 * circle is PI/4. If we choose a point in the square at random, the probability 
 * that it is inside the circle is PI/4. If we choose N points in the square at 
 * random, and if C of them are inside the circle, we expect the fraction C/N 
 * of points that fall inside the circle to be about PI/4. That is, we expect 
 * 4*C/N to be close to PI. If N is large, we can expect 4*C/N to be a good 
 * estimate for PI, and as N gets larger and larger, the estimate is likely to 
 * improve.
 * 
 * We can pick a random point in the square by choosing numbers x and y in the 
 * range 0 to 1 (using Math.random()). Since the equation of the circle is 
 * x*x+y*y=1, the point lies inside the circle if x*x+y*y is less than 1. One 
 * trial consists of picking x and y and testing whether x*x+y*y is less than 1. 
 * To get an estimate for PI, you have to do many trials, count the trials, and 
 * count the number of trials in which x*x+y*y is less than 1,
 * 
 * For this exercise, you should write a GUI program that does this computation 
 * and displays the result. The computation should be done in a separate thread, 
 * and the results should be displayed periodically. The program can use JLabels 
 * to the display the results. It should set the text on the labels after running 
 * each batch of, say, one million trials. (Setting the text after each trial 
 * doesn't make sense, since millions of trials can be done in one second, and 
 * trying to change the display millions of times per second would be silly.
 *
 * Your program should have a "Run"/"Pause" button that controls the computation. 
 * When the program starts, clicking "Run" will start the computation and change 
 * the text on the button to "Pause". Clicking "Pause" will cause the computation 
 * to pause. The thread that does the computation should be started at the beginning 
 * of the program, but should immediately go into the paused state until the "Run" 
 * button is pressed. Use the wait() method in the thread to make it wait until 
 * "Run" is pressed. Use the notify() method when the "Run" button is pressed 
 * to wake up the thread. Use a boolean signal variable running to control whether 
 * the computation thread is paused.
 * 
 */
public class Exe5_CheckPi extends JFrame {
  private static boolean running = false;
  private static ArrayBlockingQueue<Task> tasks;
  private static LinkedBlockingQueue<Result> results;  
  private static JLabel countLabel;
  private static JLabel piEstimateLabel;
  private static JButton runPause;
  private static Worker[] workers;
  

  public static void main(String[] args) {
    results = new LinkedBlockingQueue<Result>();
    tasks = new ArrayBlockingQueue<Task>(100);
    workers = new Worker[Runtime.getRuntime().availableProcessors()];
    
    JFrame window = new Exe5_CheckPi();
    window.setVisible(true);
    
    Thread producer = createTaskDividingThread();
    producer.start();
    
    startThreadPool(workers);

    calcEstimatePI();
  }
  
  public Exe5_CheckPi() {
    super("Estimating value of Pi");
    Content content = new Content();
    setContentPane(content);
    setLocation(100,100);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  private static Thread createTaskDividingThread() {
    Thread thread = new Thread() {
      public void run() {
        int step = 10000;
        long from = 0L;
        long to = step;
        while (true) {
          try {
            tasks.put( new Task(from, to));
            from += step;
            to += step;
          } catch (InterruptedException e) {}
        }
      }
    };
    return thread;
  }
  
  private static void startThreadPool(Worker[] workers) {
    for (int i = 0; i < workers.length ; i++) {
      Worker worker = new Worker();
      worker.start();
      workers[i] = worker;
    }
  }
  
  private static void calcEstimatePI() {
    long trials = 0L;
    long inCircle = 0L;
    while (true) {
      try {
        Result result = results.take();
        trials += result.trials;
        inCircle += result.inCircleCount;
        double estimateForPi = 4 * ((double)inCircle / trials);
        if (trials % 1000000 == 0) { // Update the labels once every million trials
          countLabel.setText( " Number of Trials: " + trials);
          piEstimateLabel.setText( " Current Estimate: " + estimateForPi);
        }
      } catch(InterruptedException e){}
    }
  }
  
  private class Content extends JPanel implements ActionListener {  
    public Content() {
      super( new GridLayout(4,0,40,0));
      setPreferredSize(new Dimension(600, 400));
      Font font = new Font("Serif", Font.BOLD, 34);
      
      JLabel actualPi = newLabel("Actual value of Pi is " + Math.PI, font);
      add(actualPi);
      countLabel = newLabel("Getting ready", font);
      add(countLabel);
      piEstimateLabel = newLabel("Getting ready", font);
      add(piEstimateLabel);
      
      runPause = new JButton("RUN");
      runPause.setFont(font);
      runPause.addActionListener(this);
      add(runPause);
      setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));    
    }
    
    private JLabel newLabel(String title, Font font) {
      JLabel label = new JLabel(title);
      label.setFont(font);
      label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
      return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (running) {
        running = !running;
        runPause.setText("RUN");
      } else {
        synchronized (workers) {    
          running = !running;
          runPause.setText("PAUSE");
          workers.notifyAll();
        }        
      }      
    }
  }
  
  static class Worker extends Thread {  
    private void checkStatus() {
      if (!running) {
        try {
          synchronized (workers) {
            workers.wait();
            System.out.println("Thread: " + this + " is notified.");
          }
        } catch (InterruptedException e) {}
      }
    }
    
    private static Result doTask() {
      Result result = null;
      try {
        Task task = tasks.take();    
        long trialCount = 0, inCircleCount = 0;    
        for (long i = task.from; i < task.to; i++) {
          double x = Math.random();
          double y = Math.random();
          trialCount++;
          if (x * x + y * y < 1)
            inCircleCount++;
          }
          result = new Result(trialCount, inCircleCount);
      } catch (InterruptedException e) {}
      return result;
    }
    
    public void run() {
      while (true) {
        checkStatus();
        Result result = doTask();
        try {
          results.put(result);
        } catch (InterruptedException e) { }
      }
    }
  }
  
  private static class Task {
    private long from;
    private long to;
    
    public Task(long from, long to) {
      this.from = from;
      this.to = to;
    }
  }
  
  private static class Result {
    private long trials;
    private long inCircleCount;
    
    public Result(long trials, long inCircle) {
      this.trials = trials;
      inCircleCount = inCircle;
    }
  }
}
