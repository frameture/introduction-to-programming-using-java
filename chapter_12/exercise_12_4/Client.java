import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class Client {  
  private static final int PORT = 1024;
  private static BufferedReader in;
  private static PrintWriter out; 
  
  public static void main(String[] args) {
    Socket connection = connect();
    openStreams(connection);

    BufferedReader userCL = new BufferedReader(new InputStreamReader(System.in));
    try {
      while (true) {  
        System.out.println("Write your command.");  
        String command = userCL.readLine();
        sendCommand(command);
        receiveAnswer();
      }
    } catch (IOException e) {
      System.out.println("ERROR");
      e.printStackTrace();
      System.exit(1);
    }
  }
  
  /**
   * Opens the connection to the server and returns a reference to the 
   * connected socket.
   */
  private static Socket connect() {
    System.out.println("Connecting");
    Socket socket = null;
    try {
      socket = new Socket("localhost", PORT);  
      if (socket.isConnected())
        System.out.println("Connection succeded.");
      else {
        System.out.println("Failed to connect.");
        return null;
      }
    } catch (Exception e) {
        System.out.println("ERROR while connecting to the server. " + e);
        System.exit(1);
    }
    return socket;
  }

  private static void openStreams(Socket c) {
    try {
      in = new BufferedReader(new InputStreamReader(c.getInputStream())); 
        out = new PrintWriter(c.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }  
  }
  
  private static void sendCommand(String command) throws IOException {
    out.println(command);
    out.flush();
    if (out.checkError())
      throw new IOException("ERROR while sending command.");
  }
  
  /**
   * Output the response from the server to the console.
   */
  private static void receiveAnswer() throws IOException {
    char[] buffer = new char[10000];
    in.read(buffer);
    System.out.println(buffer);
  }
}
