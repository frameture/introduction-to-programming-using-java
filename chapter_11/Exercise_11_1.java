import java.io.File;
import java.util.Scanner;

/**
 * The sample program DirectoryList.java, given as an example in Subsection 
 * 11.2.2, will print a list of files in a directory specified by the user. 
 * But some of the files in that directory might themselves be directories. And 
 * the subdirectories can themselves contain directories. And so on. Write 
 * a modified version of DirectoryList that will list all the files in a directory 
 * and all its subdirectories, to any level of nesting. You will need a recursive 
 * subroutine to do the listing. The subroutine should have a parameter of type 
 * File. You will need the constructor from the File class that has the form
 *
 * public File( File dir, String fileName )
 *  // Constructs the File object representing a file
 *  // named fileName in the directory specified by dir.
 */
public class Exercise_11_1 {
  public static void main(String[] args) {
    Scanner scanner; // For reading a line of input from the user.
    scanner = new Scanner(System.in);

    System.out.print("Enter a directory name: ");

    listFiles(new File(scanner.nextLine().trim())); // Print files.
    
    scanner.close(); // Release the Scanner.
  }
  
  /**
   * Recursive, prints all file names that are included in the passed directory or its
   * name printed if it is a File.
   * @param file File or directory that will be checked recursively for its
   * content. If another File is found, then its name is printed, else if 
   * directory is found - its content is checked. 
   */
  static void listFiles(File file){
    if (file.isDirectory()) {
    String[] files = file.list(); // List of files that the directory contains.
    if (files == null)
      return;
    else
      for (int i = 0; i < files.length; i++)
      listFiles(new File(file, files[i]));
    } else 
    System.out.println(file.getName());
  }
}

