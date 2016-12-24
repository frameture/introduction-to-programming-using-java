import java.util.concurrent.ArrayBlockingQueue;
import java.util.ArrayList;
import java.net.Socket;
import java.io.File;

public class Main {
  private static final int SERVER_CAPACITY = 10;
  private static final int PORT = 1024;
  private static ArrayBlockingQueue<Socket> clientsQueue;
  public static File directory;

  public static void main(String[] args) {
    directory = (args.length != 0) ? readDirectory(args[0]) : new File("C://");
    clientsQueue = new ArrayBlockingQueue<Socket>(SERVER_CAPACITY);
    
    new Server(PORT, clientsQueue).start(); // Run the server.
    
    ArrayList<IOWorker> clients = new ArrayList<>();
    while (true) {
      try {
        IOWorker worker = new IOWorker(clientsQueue.take());
        worker.start();
        clients.add(worker);
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
  }
  
  /**
   * Checks whether provided File exist and if it is a directory.
   */
  private static File readDirectory(String dirName) {
    File dir = new File(dirName);
    if (!dir.exists()) {
      System.out.println("Specified directory does not exist.");
      System.exit(1);
    } else if (!dir.isDirectory()) {
      System.out.println("The specified file is not a directory.");
      System.exit(1);
    }
    return dir;
  }
}
