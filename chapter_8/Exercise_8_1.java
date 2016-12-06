// depends on TextIO class
import static java.lang.System.out;

/** 
 * Write a program that uses the root() subroutine, from Subsection 8.3.3, 
 * to solve equations specified by the user. Your program should allow the 
 * user to specify values for A, B, and C. It should call the subroutine to 
 * compute a solution of the equation. If no error occurs, it should print 
 * the root. However, if an error occurs, your program should catch that error 
 * and print an error message. After processing one equation, the program should 
 * ask whether the user wants to enter another equation. The program should 
 * continue until the user answers no.
 */
public class Exercise_8_1 {
  public static void main(String[] args) {
    while (true) {
      out.println("Specify A, B and C values for the equation.");
      try { // try to calculate result
        double result = root(getDouble("A = "), getDouble("B = "), getDouble("C = "));
        out.println("Result is " + result);
      }
      catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
      out.println("To finish write 'no', to check another equation type 'yes'.");
      if ( ! TextIO.getlnBoolean()) { // user wants to exit the program
        out.println("Bye!");
        break;
      }
    }
  }

  /**
  * Gets the input number from the user. Uses TextIO class.
  * @param text String to be displayed to the user
  * @return number given by the user
  */
  private static double getDouble(String text) {
    out.println(text);
    return TextIO.getlnDouble();
  }

  /**
  * Returns the larger of the two roots of the quadratic equation
  * A*x*x + B*x + C = 0, provided it has any roots. If A == 0 or
  * if the discriminant, B*B - 4*A*C, is negative, then an exception
  * of type IllegalArgumentException is thrown.
  */
  private static double root( double A, double B, double C ) throws IllegalArgumentException {
    if (A == 0) 
      throw new IllegalArgumentException("A can’t be zero.");
    else {
      double disc = B*B - 4*A*C;
      if (disc < 0)
        throw new IllegalArgumentException("Discriminant < zero.");
      return (-B + Math.sqrt(disc)) / (2*A);
    }
  }
}