import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class IOWorker extends Thread {
  private Socket connection;
  private BufferedReader in;
  private PrintWriter out;
  private LinkedList<Character> queueOfChars = new LinkedList<>();

  public IOWorker(Socket connection) {
    this.connection = connection;
    try {
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      out = new PrintWriter(connection.getOutputStream());
    } catch (IOException e) {
      System.out.println("Error while opening IO connection:  " + e);
      System.exit(1);
    }
    setDaemon(true);
    setPriority(getPriority() - 1);
  }

  @Override
  public void run() {
    char[] buffer = new char[1000];
    try {
      while (true) {
        System.out.println("Blocked on - in.read()");
        int chars = in.read(buffer);
    
        /* -- easier version -- */
        // processInputLine( in.readLine() );
        // -----------------------
        
        System.out.println("in.read() - done; characters read = " + chars);
        if (chars != -1) { // Is stream opened?
          addCharsToQueue(buffer, chars);
          processInputLine( readLineFromQueue() );
        }
        else {
          System.out.println("Client " + connection.getInetAddress() +
              " closed the connection.");
          System.out.println("Current thread finished.");
          break;
        }
      }
    } catch (IOException e) {
      System.out.println("Connection closed from the other side " + e.getMessage());
    } finally {
      try {
        out.close();
        in.close();
        connection.close();
        System.out.println("Thread finished.");
      } catch (IOException e) {
        System.out.println("Error while closing the connection.");
      }
    }
  }

  /**
   * Puts all characters read from the buffer to the queue of characters.
   */
  private void addCharsToQueue(char[] buffer, int chars) {
    for (int i = 0; i < chars; i++)
      queueOfChars.addLast(new Character(buffer[i]));
  }

  /**
   * Determines what command action should be performed.
   */
  private void processInputLine(String command) throws IOException{
      if (command.equalsIgnoreCase("index")) {
        sendIndex(Main.directory);
      } else if (command.toLowerCase().startsWith("get")) {
        String fileName = command.substring(3).trim();
        sendFile(fileName, Main.directory);
      } else {
        out.println("ERROR. Unsupported command '" + command + "'");
        out.flush();
      }
      System.out.println("Command :" + command + "processed from client " 
            + connection.getInetAddress());
  }

  /**
   * Reads characters from the queue and builds a string out of them. If '\n'
   * newline character is read - the string is returned.
   */
  private String readLineFromQueue() {
    StringBuilder builder = new StringBuilder();
    while (!queueOfChars.isEmpty()) {
      char ch = queueOfChars.removeFirst();
      if (ch == '\n')
        break;
      else
        builder.append(ch);
    }
    return builder.toString();
  }

  /**
   * "INDEX" command. Send the list of files that are contained within the directory.
   */
  private void sendIndex(File directory) throws IOException {
    String[] fileList = directory.list();
    for (int i = 0; i < fileList.length; i++)
      out.println(fileList[i]);
    out.flush();
    if (out.checkError())
      throw new IOException("Error while transmitting data. INDEX command");
  }

  /**
   * "GET <fileName>" command. If the file doesn't exist, the client is informed. 
   * Otherwise, send the contents of the file.
   */
  private void sendFile(String fileName, File directory) throws IOException {
    File file = new File(directory, fileName);
    if (!file.exists())
      out.println("ERROR. File '" + fileName + "' does not exist.");
    else if (file.isDirectory())
      out.println("ERROR. File '" + fileName + "' is a directory.");
    else {
      BufferedReader fileIn = new BufferedReader(new FileReader(file));
      while (true) {
        String line = fileIn.readLine();
        if (line == null)
          break;
        out.println(line);
      }
      fileIn.close();
    }
    out.flush();
    if (out.checkError())
      throw new IOException("Error while transmitting data. GET command");
  }
}
