import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * Write a program that will count the number of lines in each file that is 
 * specified on the command line. Assume that the files are text files. Note 
 * that multiple files can be specified, as in:
 *
 *  java  LineCounts  file1.txt  file2.txt  file3.txt
 *
 * Write each file name, along with the number of lines in that file, to standard 
 * output. If an error occurs while trying to read from one of the files, you 
 * should print an error message for that file, but you should still process all the 
 * remaining files. Do not use TextIO to process the files; use a Scanner,
 * a BufferedReader, or a TextReader to process each file.
 *
 */
public class Exercise_11_2 {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("No specified files in command line.");
      return;
    }
    for (String f : args)
      try {
        System.out.println("File '" + f + "' has " + lines(f) + " lines.");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
  }

  /**
   * Counts lines of text in passed file name.
   * @param args Name of the file that will have lines counted.
   * @throws Exception If any error occur during file processing.
   */
  private static int lines(String f) throws Exception {
    int count = 0;
     // Scanner will be automatically closed.
    try  (Scanner scanner = new Scanner( new File(f)) ) {
      while (scanner.hasNextLine()) {
        count++;
        scanner.nextLine();
      }  
    } catch (IOException e) {
      throw new Exception("Error " + e + " occured during processing the file: " + f);
    }
    return count;
  }
}