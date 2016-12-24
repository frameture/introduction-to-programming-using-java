import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class Server extends Thread {
  private final int PORT;
  private ArrayBlockingQueue<Socket> clients;
  
  public Server(int port, ArrayBlockingQueue<Socket> clients) {
    PORT = port;
    this.clients = clients;
  }
  /**
   * Listen for connection requests from clients. Add new clients' sockets
   * to the queue for later processing.
   */
  public void run() {
    try ( ServerSocket server = new ServerSocket(PORT) ) {
      System.out.println("Listening on port " + PORT);
      while (true) {
        Socket connection = server.accept();
        clients.put(connection);
        System.out.println("Client connected");
      }
    } catch (Exception e) {
      System.out.println("Server shut down unexpectedly.");
      System.out.println("Error:  " + e);
      System.exit(1);
    }
  }
}
