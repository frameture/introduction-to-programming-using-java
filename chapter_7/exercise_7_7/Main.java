import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    TextIO.readUserSelectedFile(); // open fileChooser --- user selects file
                                   // to be opened
    ArrayList<String> words = new ArrayList<String>(); // list of words occurred 
					                                   // in the given file
    String word;   // current word
    while (true) {
      word = readNextWord(); // read next word
      if (word == null) 
		  break;             // no word --- break the loop 
      word = word.toLowerCase();
      if (words.indexOf(word) >= 0)
        continue;            // duplicate --- omit it
      words.add(word);       // add current word to the list
    }
    Collections.sort(words);   // sort the list of words
    TextIO.writeUserSelectedFile();  // open fileChooser --- user selects
                                 	 // a file for saving the sorted list of words
    for (String each : words)
      TextIO.putln("   " + each);
  }

  /**
  * Read the next word from TextIO, if there is one. First, skip past
  * any non-letters in the input. If an end-of-file is encountered before
  * a word is found, return null. Otherwise, read and return the word.
  * A word is defined as a sequence of letters. Also, a word can include
  * an apostrophe if the apostrophe is surrounded by letters on each side.
  * @return the next word from TextIO, or null if an end-of-file is
  * encountered
  */
  private static String readNextWord() {
    char ch = TextIO.peek(); // Look at next character in input.
    while (ch != TextIO.EOF && ! Character.isLetter(ch)) {
      // Skip past non-letters.
      TextIO.getAnyChar(); // Read the character.
      ch = TextIO.peek(); // Look at the next character.
    }
    if (ch == TextIO.EOF) // Encountered end-of-file
      return null;
    // At this point, we know the next character is a letter, so read a word.
    String word = ""; // This will be the word that is read.
    while (true) {
      word += TextIO.getAnyChar(); // Append the letter onto word.
      ch = TextIO.peek(); // Look at next character.

      if ( ch == '\'' ) {
        // The next character is an apostrophe. Read it, and
        // if the following character is a letter, add both the
        // apostrophe and the letter onto the word and continue
        // reading the word. If the character after the apostrophe
        // is not a letter, the word is done, so break out of the loop.
        TextIO.getAnyChar(); // Read the apostrophe.
        ch = TextIO.peek(); // Look at char that follows apostrophe.
        if (Character.isLetter(ch)) {
          word += '\'' + TextIO.getAnyChar();
          ch = TextIO.peek(); // Look at next char.
        }
        else
          break;
      }
      if ( ! Character.isLetter(ch) ) {
        // If the next character is not a letter, the word is
        // finished, so break out of the loop.
        break;
      }
      // If we haven’t broken out of the loop, next char is a letter.
    }
    return word; // Return the word that has been read.
  }
}
