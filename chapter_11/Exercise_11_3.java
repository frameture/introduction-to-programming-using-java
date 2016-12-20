import java.io.*;
import java.net.*;

/**
 * For this exercise, you will write a network server program. The program is 
 * a simple file server that makes a collection of files available for 
 * transmission to clients. When the server starts up, it needs to know the name 
 * of the directory that contains the collection of files. This information can 
 * be provided as a command-line argument. You can assume that the directory 
 * contains only regular files (that is, it does not contain any sub-directories). 
 * You can also assume that all the files are text files.
 *
 * When a client connects to the server, the server first reads a one-line command 
 * from the client. The command can be the string "INDEX". In this case, the 
 * server responds by sending a list of names of all the files that are available 
 * on the server. Or the command can be of the form "GET <filename>", where 
 * <filename> is a file name. The server checks whether the requested file actually 
 * exists. If so, it first sends the word "OK" as a message to the client. Then 
 * it sends the contents of the file and closes the connection. Otherwise, it 
 * sends a line beginning with the word "ERROR" to the client and closes the 
 * connection. (The error response can include an error message on the rest of the line.)
 *
 */
public class Exercise_11_3 {  
   private static final int PORT = 1024;
   private static enum Commands {INDEX, GET};
  
  public static void main(String[] args) {
    PrintWriter out = null;
    BufferedReader in = null;
    
    File directory = null;
    if (args.length > 0)
      directory = checkDirValidity(args[0]);
    if (directory == null) // Not valid File object.
      return;
    
    try (ServerSocket server = new ServerSocket(PORT)) { 
      System.out.println("Waiting for a client.");
      Socket connection = server.accept();
      out = new PrintWriter(connection.getOutputStream());
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      handleCommunication(in, out, directory);
    } catch (IOException e) {
      System.out.println("Error occured during communication " + e);
      return;
    } finally {
      try {
        out.close();
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Server is off.");
  }      

  /**
   * Checks whether there is a provided path for constructing a File.
   * If constructed File doesn't exist or it is not a directory - null 
   * is returned. Else, reference to the valid File is returned.
   */
  private static File checkDirValidity(String dirName) {
    if (dirName == null || dirName.length() < 1) {
      System.out.println("No directory name provided.");
      return null;
    }
    File dir = new File(dirName);
    
    if (!dir.exists()) {
      System.out.println("Provided directory doesn't exist.");
      return null;
    } else if(!dir.isDirectory()) {
      System.out.println("It is not a directory.");
      return null;
    } else
      return dir;
  }

  /**
   * Performs connection protocol. If error occurs at any stage, proper message
   * is sent to the client or server. Returns true only after Commands.INDEX
   * command, which means that the client still should be under service for next
   * command.
   */
  static void handleCommunication(BufferedReader in, PrintWriter out, File dir) {
    System.out.println("Client connected.");
  
    try {          
      String command = in.readLine();
      if (command.equalsIgnoreCase(Commands.INDEX.toString())) {
        sendIndex(out, dir);
      } else if (command.startsWith(Commands.GET.toString()))
        sendFile(out , new File(dir, command.substring(3).trim()));
      else {
        //out.println("Error. Invalid command: '" + command + "'");
        System.out.println("Wrong command.");
      }
    } catch (IOException e) {
      System.out.println("Error " + e);  
    } finally {
      out.flush();
    }
  }

  /**
   * Tries to send text content of provided File to the given Writer. 
   */
  private static void sendFile(PrintWriter out, File file) {
    if (!file.exists()) {
      out.println("ERROR. No such file exists.");
      return;
    }
    try (BufferedReader fileReader = new BufferedReader(new FileReader(file));){
      out.println("OK \n");
        out.flush();
        
      String line = fileReader.readLine();
      while (line != null) {
        out.println(line);
        line = fileReader.readLine();
      }
      if (out.checkError())
        out.println("ERROR during sending the file: " + file);  
    } catch (IOException e) {
      out.println("ERROR. Failed to process the file.");
      return;
    }
  }

  /**
   * Sends index of file names contained inside the given directory.
   */
  private static void sendIndex(PrintWriter out, File dir) {
    String[] files = dir.list();
    
    out.println("Directory " + dir + " contains following files:");
    for (String f : files) 
      out.println("  " + f);
    if (out.checkError())
      out.println("ERROR");
    
    out.println("To download certain file content use: 'GET <filename>'.");  
  }
}
