import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.System.out;

/**  
 * An example in Subsection 10.4.2 concerns the problem of making an index for 
 * a book. A related problem is making a concordance for a document. A concordance 
 * lists every word that occurs in the document, and for each word it gives the 
 * line number of every line in the document where the word occurs. All the 
 * subroutines for creating an index that were presented in Subsection 10.4.2 can 
 * also be used to create a concordance. The only real difference is that the 
 * integers in a concordance are line numbers rather than page numbers.
 *
 * Write a program that can create a concordance. The document should be read 
 * from an input file, and the concordance data should be written to an output 
 * file. You can use the indexing subroutines from Subsection 10.4.2, modified 
 * to write the data to TextIO instead of to out. (You will need to make 
 * these subroutines static.) The input and output files should be selected by 
 * the user when the program is run. The sample program WordCount.java, from 
 * Subsection 10.4.4, can be used as a model of how to use files. That program 
 * also has a useful subroutine that reads one word from input.
 *
 * As you read the file, you want to take each word that you encounter and add 
 * it to the concordance along with the current line number. Keeping track of 
 * the line numbers is one of the trickiest parts of the problem. In an input 
 * file, the end of each line in the file is marked by the newline character, '\n'. 
 * Every time you encounter this character, you have to add one to the line 
 * number. WordCount.java ignores ends of lines. Because you need to find and 
 * count the end-of-line characters, your program cannot process the input file 
 * in exactly the same way as does WordCount.java. Also, you will need to detect 
 * the end of the file. The function TextIO.peek(), which is used to look ahead 
 * at the next character in the input, returns the value TextIO.EOF at end-of-file, 
 * after all the characters in the file have been read.
 *
 * Because it is so common, don't include the word "the" in your concordance. 
 * Also, do not include words that have length less than 3.
 * 
 */
public class Exercise_10_5 {
  static int lines = 1; // Keeps track of current line in the input file. 

  public static void main(String[] args) {
    printIntro();   // Inform the user about the program.
    TextIO.getln(); // Wait for user to proceed.
    selectFile();   // Let the user specify the input file.

    TreeMap<String, ArrayList<Integer>> concordance; // Create a TreeMap to hold 
                                                     // the data.  Read the file 
                                                     // and record occurrences of 
                                                     // all words in the text. 
    concordance = new TreeMap<String, ArrayList<Integer>>();
    processText(concordance); // Process the input file. 
    
    printCounter(concordance); // Let the user know how many different words were 
                               // found in the text.
    printResult(concordance); // Copy the words data into an array and sort the 
                              // list alphabetically. Also, output the sorted list 
                              // with its number of lines on which it occurred.
  }
  
  /**
   * Prints the informational text to to the user.
   */
  private static void printIntro() {
    out.println("\n\n   This program will ask you to select an input file.");
    out.println("It will make a list of all the words that occur in the file");
    out.println("along with the number of lines on which each word occurs.");
    out.println("\n   After reading the input file, the program asks you to");
    out.println("select an output file.  If you select a file, the list of");
    out.println("words will be written to that file; if you cancel, the list");
    out.println("will be written to the standard output. \n");
    out.println("All words are converted to lower case.\n\n");
    out.print("Please click enter to begin.");
  }
  
  /**
   * Allow the user to pick a file that will provide the text for processing.
   * If no file selected, the program will exit.
   */
  private static void selectFile() {
    if (TextIO.readUserSelectedFile() == false) {
      out.println("No input file selected.  Exiting.");
      System.exit(1);
    }
  }
  
  /**
   * Reads the input text and count occurrences of each word in the text.
   * @param concordance Map of key/value pairs.
   */
  private static void processText(TreeMap<String, ArrayList<Integer>> concordance) {
    String word = readNextWord();
    while (word != null) {
      word = word.toLowerCase();  // Convert word to the lower case.
      if (word.length() < 3 || word.equals("the") ) {
          // We ignore words of length lower than 3 and the word "the".
        word = readNextWord();
        continue;
      }
      ArrayList<Integer> list = concordance.get(word); // List of page numbers.
      if (list == null) {
          // There was no associated list of page numbers with current word (key).
        list = new ArrayList<Integer>();
        list.add(lines);
        concordance.put(word, list); // Add new key/value pair to the map.
      }
      else
          // There is already current word in the map. Add new line to its
          // list of page numbers.
        list.add(lines);
      word = readNextWord(); // Read new word.
    }
  }
  
  /**
   * Read the next word from TextIO, if there is one.  First, skip past
   * any non-letters in the input.  If an end-of-file is encountered before 
   * a word is found, return null.  Otherwise, read and return the word.
   * A word is defined as a sequence of letters.  Also, a word can include
   * an apostrophe if the apostrophe is surrounded by letters on each side.
   * @return the next word from TextIO, or null if an end-of-file is encountered
   */
  private static String readNextWord() {
    char ch = TextIO.peek(); // Look at next character in input.
    while (ch != TextIO.EOF && ! Character.isLetter(ch)) {
      if (ch == '\n') // checking for new line character
        lines++;
      TextIO.getAnyChar();  // Read the character.
      ch = TextIO.peek();   // Look at the next character.
    }
    if (ch == TextIO.EOF) // Encountered end-of-file
      return null;
    
    // At this point, we know that the next character is a letter, so read a word.
    String word = "";  // This will be the word that is read.
    while (true) {
      word += TextIO.getAnyChar(); // Append the letter onto word.
      ch = TextIO.peek(); // Look at next character.
      if ( ch == '\'' ) {
          // The next character is an apostrophe.  Read it, and
          // if the following character is a letter, add both the
          // apostrophe and the letter onto the word and continue
          // reading the word.  If the character after the apostrophe
          // is not a letter, the word is done, so break out of the loop.
        TextIO.getAnyChar(); // Read the apostrophe.
        ch = TextIO.peek();  // Look at char that follows apostrophe.
        if (Character.isLetter(ch)) {
          word += "\'" + TextIO.getAnyChar();
          ch = TextIO.peek();  // Look at next char.
        }
        else
          break;
      }
      if ( ! Character.isLetter(ch) ) {
          // If the next character is not a letter, the word is
          // finished, so break out of the loop.
        break;
      }
      // If we haven't broken out of the loop, next char is a letter.
    }
    return word; // Return the word that has been read.
  }
  
  /**
   * Prints to the console number of the words found in the text provided by the user.
   * @param c Map of the key/value pairs.
   */
  private static void printCounter(TreeMap<String, ArrayList<Integer>> c) {
    if (c.size() == 0) {
      out.println("No words found in file.");
      out.println("Exiting without saving data.");
      System.exit(0);
    } else {
      out.println("Number of different words found in file: " + c.size());
      out.println();
    }
  }

  /**
   * Print the concordance of the words found in the text to the new specified file.
   * If no output file specified, the result will be printed to the console.
   * @param concordance Map of key/value pairs.
   */
  private static void printResult(TreeMap<String, ArrayList<Integer>> concordance) {
    TextIO.writeUserSelectedFile(); // If user cancels, output automatically
                                    // goes to standard output (console).
    TextIO.putln(concordance.size() + " words found in file:\n");
    TextIO.putln("List of words in alphabetical order (with number of occurrences"
      + " in brackets):\n");
    for ( String key : concordance.keySet() )
      TextIO.putln("   " + key + concordance.get(key));
    out.println("\n\nDone.");  
  }
}