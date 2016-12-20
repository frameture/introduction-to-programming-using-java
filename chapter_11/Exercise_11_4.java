import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/** 
 * 
 * Write a client program for the server from Exercise 11.3. Design a user 
 * interface that will let the user do at least two things: (1) Get a list 
 * of files that are available on the server and display the list on standard 
 * output; and (2) Get a copy of a specified file from the server and save it 
 * to a local file (on the computer where the client is running).
 * 
 */
public class Exercise_11_4 {  
  private static final int PORT = 1024;
  private static enum Commands {INDEX, GET};
  
  public static void main(String[] args) {
    Socket connection = connect();

    Scanner userCL = new Scanner(System.in);
    Scanner in = null;      // InputStream from the other program.
    PrintWriter out = null; // OutputStream to the other program.
    try {
      in = new Scanner(connection.getInputStream()); 
      out = new PrintWriter(connection.getOutputStream());
      String command = readCommand(userCL);
      performCommand(command, in, out);
    } catch (IOException e) {
      System.out.println(e);
    } finally {
      try {
        userCL.close();
        in.close();
        out.close();
        connection.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
    
  private static String readCommand(Scanner in) {
    System.out.println("Write your command.");
    return in.nextLine().trim();
  }

  private static void performCommand(String command, Scanner in, PrintWriter out) {
    System.out.println("command: " + command);
    if (command == null)
      return;
    if (command.equals(Commands.INDEX.toString()))
      indexCommand(in, out);
    else if (command.startsWith(Commands.GET.toString()))
      getCommand(in, out, command.substring(3));
  }

  /**
   * Performs the connection to the server and returns a reference to the 
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
    } catch (IOException e) {
        System.out.println("ERROR while connecting to the server. " + e);
    }
    return socket;
  }
  
  /**
   * Performs INDEX command. Sends request to the server to get index of the
   * files that are available to transfer.
   */
  private static void indexCommand(Scanner in, PrintWriter out) {
    try {
      out.println(Commands.INDEX); // Send request.
      out.flush();
      if (out.checkError())
        System.out.println("Error while sending INDEX command.");
      while (in.hasNextLine())
        System.out.println(in.nextLine());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Performs GET command. Sends request to the server to get the content of
   * specified file.
   */
  private static void getCommand(Scanner in, PrintWriter out, String fileName) {
    if (fileName.length() < 1) {
      System.out.println("No file name specified.");
      return;
    }
    try {
      out.println(Commands.GET + fileName);
      out.flush();
      if (out.checkError())
        System.out.println("Error while sending GET command.");
      if (copyFile(fileName, in))
        System.out.println("File copied successfully.");
      else 
        System.out.println("File not copied.");
    } catch (Exception e) {
      System.out.println("Error occured during file processing.");
    }
  }

  /**
   * Copy the content of given InputStream to the newly create file.
   */
  private static boolean copyFile(String fileName, Scanner in) {
    // Create a PrintWriter that prints to the new created File.
    try (PrintWriter out = new PrintWriter( new File(fileName))) {
      String status = in.nextLine();
      if (status.startsWith("OK")) 
        System.out.println(status);
      else 
        return false;
      
      while (in.hasNextLine())
        out.println(in.nextLine());
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
