// depends on TextIO class
import java.math.BigInteger;
import static java.lang.System.out;

/**
 * 
 * For this exercise, you should write a program that prints 3N+1 sequences with 
 * starting values specified by the user. In this version of the program, you 
 * should use BigIntegers to represent the terms in the sequence. You can read 
 * the user's input into a String with the TextIO.getln() function. Use the input 
 * value to create the BigInteger object that represents the starting point of 
 * the 3N+1 sequence. Don't forget to catch and handle the NumberFormatException 
 * that will occur if the user's input is not a legal integer! You should also 
 * check that the input number is greater than zero.
 *
 * If the user's input is legal, print out the 3N+1 sequence. Count the number 
 * of terms in the sequence, and print the count at the end of the sequence. Exit
 * the program when the user inputs an empty line.
 *
 */
public class Exercise_8_2 {
  public static void main(String[] args) {
    while (true) {
      int count = 0; // number of 3N+1 sequences
      out.println("Type integer value greater than 0 to begin the 3N+1 "
              +   "sequence. \nTo exit the program, simply click enter.");
      try {
        count = threeNPLusOne(getInput());
      } 
	  catch (ExitException e) {
        out.println("Bye!");
        break; // user entered an empty string
      }
      catch (IllegalArgumentException e) {
        out.println("Type in a N integer.");
        continue;
      }
      out.println("It took " + count + " times to get 1 of the 3N+1 sequence.");
    }
}

  /**
  * Gets the integer from the user.
  * @return String representing positive Integer.
  * @throws ExitException Signal exit the program
  */
  private static String getInput() throws ExitException {
    String input = TextIO.getlnString();
    if (input.length() == 0) // exit the program
      throw new ExitException("Exit the program. Empty string."); 
    else if (Integer.parseInt(input) <= 0 )
      throw new IllegalArgumentException("Integer has to be greater than 0");
    else
      return input;
  }

  /**
  * Performs the 3N+1 sequence
  * @param N String representing the integer value
  * @return number of sequences needed to N == 1
  */
  private static int threeNPLusOne(String N) {
    BigInteger one = new BigInteger("1"),  // BigIntegers representing 3 
               two = new BigInteger("2"),  // integers used in equation
               three = new BigInteger("3");
    BigInteger number = new BigInteger(N); // current number
    int count = 0; // number of 3N+1 sequences
    while(number.equals(one) == false) {
      count++;
      if (number.testBit(0)) // if the number is even --- *3+1
        number = number.multiply(three).add(one);
      else {
        number = number.divide(two);
      }
    }
    return count;
  }

  /**
  * Nested subclass of Exception -- signals that the user wants to exit the 
  * program.
  */
  private static class ExitException extends Exception {
    public ExitException(String text) {
      super(text);
    }
  }
}
