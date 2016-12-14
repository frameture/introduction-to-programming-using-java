import static java.lang.System.out;

import java.util.TreeSet;
import java.util.LinkedList;

/**
 * 
 * In mathematics, several operations are defined on sets. The union of two sets
 * A and B is a set that contains all the elements that are in A together with 
 * all the elements that are in B. The intersection of A and B is the set that 
 * contains elements that are in both A and B. The difference of A and B is the 
 * set that contains all the elements of A except for those elements that are also in B.
 *
 * Suppose that A and B are variables of type set in Java. The mathematical operations
 * on A and B can be computed using methods from the Set interface. In particular: 
 * A.addAll(B) computes the union of A and B; A.retainAll(B) computes the intersection
 * of A and B; and A.removeAll(B) computes the difference of A and B. (These operations 
 * change the contents of the set A, while the mathematical operations create 
 * a new set without changing A, but that difference is not relevant to this exercise.)
 *
 * For this exercise, you should write a program that can be used as a "set calculator" 
 * for simple operations on sets of non-negative integers. (Negative integers are not 
 * allowed.) A set of such integers will be represented as a list of integers, 
 * separated by commas and, optionally, spaces and enclosed in square brackets. 
 * For example: [1,2,3] or [17, 42, 9, 53, 108]. The characters +, *, and - will 
 * be used for the union, intersection, and difference operations. The user of the
 * program will type in lines of input containing two sets, separated by an operator. 
 * The program should perform the operation and print the resulting set. 
 * 
 * Here are some examples:
 * 
 *         Input                                 Output
 *        -------------------------           -------------------
 *         [1, 2, 3] + [3,  5,  7]             [1, 2, 3, 5, 7]
 *         [10,9,8,7] * [2,4,6,8]              [8]
 *         [ 5, 10, 15, 20 ] - [ 0, 10, 20 ]   [5, 15]
 *
 * To represent sets of non-negative integers, use sets of type TreeSet<Integer>. 
 * Read the user's input, create two TreeSets, and use the appropriate TreeSet 
 * method to perform the requested operation on the two sets. Your program should 
 * be able to read and process any number of lines of input. If a line contains 
 * a syntax error, your program should not crash. It should report the error and 
 * move on to the next line of input. (Note: To print out a Set, A, of Integers, 
 * you can just say System.out.println(A). I've chosen the syntax for sets to be 
 * the same as that used by the system for outputting a set.)
 *
 */
public class Exercise_10_2 {
  public static void main(String[] args) {  
    out.println("Type a set operation. Example: [1,2,3,4] * [5,6,7]");
    
    LinkedList<Character> input = getQueueOfChars(); // get the queue of chars
    
    try {
      outputSet(getSet(input), getOperator(input), getSet(input));
    } 
    catch (IllegalArgumentException e) {
      out.println(e.getMessage());
    }
    catch (IllegalStateException e) {
      out.println(e.getMessage());
    }
  }
  
  /**
   * Prints the result of given operation on sets.
   * @param x First set.
   * @param op Operator.
   * @param y Second set.
   */
  private static void outputSet(TreeSet<Integer> x, char op, TreeSet<Integer> y) {
    switch (op) {
    case '+':
      out.println("The union of sets is: ");
      x.addAll(y);
      out.println(x);
      break;
    case '-':
      out.println("The difference of sets is: ");
      x.removeAll(y);
      out.println(x);
      break;
    case '*':
      out.println("The intersection of sets is: ");
      x.retainAll(y);
      out.println(x);
      break;
    default: out.println(op + " is an illegal operator.");
    }
  }

  /**
   * Reads the input form the user and creates a queue out of characters
   * that were read from the input stream.
   * @return queue of characters that the user has written to the input.
   */
  private static LinkedList<Character> getQueueOfChars() {
    LinkedList<Character> queue = new LinkedList<>();
    String input = TextIO.getln().trim(); // get input from the user
    
    for (int i = 0; i < input.length(); i++)
      //adding chars to queue;
      queue.addLast(input.charAt(i));;
    return queue;
  }

  /**
   * Gets the operator form the queue of characters.
   * @param queue Queue of characters.
   * @return A character specifying the operator. [ '+' | '-' | '*' ] 
   */
  private static char getOperator(LinkedList<Character> queue) {  
    if (queue.isEmpty())
      throw new IllegalStateException("Too little input.");
    while (queue.getFirst() == ' ')
      queue.removeFirst();       // remove any empty space
    
    char op = queue.removeFirst(); // get the operator
    if (op == '*' || op == '-' || op == '+')
      return op;
    else
      throw new IllegalArgumentException("Wrong operator.");
  }
  
  /**
   * Gets the set from given queue.
   * @param queue Queue of characters to be processed.
   * @return Set of integers.
   */
  private static TreeSet<Integer> getSet(LinkedList<Character> q) {  
    TreeSet<Integer> set = new TreeSet<Integer>();
    
    if (q.isEmpty())
      throw new IllegalStateException("Set is not specified.");
    
    char ch = q.removeFirst();    
    while (ch == ' ') 
      ch = q.removeFirst(); // Remove empty spaces.
    
    if (ch != '[')
      throw new IllegalArgumentException("'[' character expected at the beginning of the set.");
    else {
      for (ch = q.removeFirst(); !q.isEmpty(); ch = q.removeFirst()) {
        if (ch == '\n')
          break;    // End of the line.
        else if (ch == ' ')
          continue; // Remove empty spaces.
        else if (ch == ',')
          continue; // Remove comma.
        else if (Character.isDigit(ch)) {
          String number = "" + ch;
          while (Character.isDigit(q.getFirst()))
            // Read multiple digits number.
            number += q.removeFirst();
          set.add(Integer.parseInt(number)); // Add number to the set.
        }
        else if (ch == ']')
          break; // end of the set 
        else 
          throw new IllegalArgumentException("Illegal input: '" + ch + "'");
      }
    }
    return set;      
  }
}
