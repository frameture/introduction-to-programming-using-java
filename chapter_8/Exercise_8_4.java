import static java.lang.System.out;
/** NOTE: Program depends on Expr.java from the book 
  * link: http://math.hws.edu/javanotes/source/chapter8/Expr.java
  * 
  * For this exercise, you should write a program that lets the user enter an 
  * expression. If the expression contains an error, print an error message. 
  * Otherwise, let the user enter some numerical values for the variable x. 
  * Print the value of the expression for each number that the user enters. 
  * However, if the expression is undefined for the specified value of x, 
  * print a message to that effect. You can use the boolean-valued function 
  * Double.isNaN(val) to che ck whether a number, val, is Double.NaN.
  *   
  * The user should be able to enter as many values of x as desired. After that, 
  * the user should be able to enter a new expression.
  */
public class Exercise_8_4 {
  public static void main(String[] args) {
    Expr exp; // expression object
    do {
      out.println("Please enter your expression, e.g. '3*x+1");
      String input = TextIO.getlnWord(); // get input
      if (input.toLowerCase().indexOf('x') < 0) {      // input doesn't contain 'x' parameter
        out.println("You have to include 'x' parameter in the expression.");
        return;
      }
      try {     // try to create new expression object
        exp = new Expr(input);
      } catch (RuntimeException e) {
        out.println("Wrong expression. Use numbers, an 'x' parameter "
                + "and simple operators: '+, -, *, /, ln, ^, sin, cos");
        return;
      }
    } while (getArgsAndCalculate(exp));
  }

  /** 
  * Gets the numbers from the user to be substituted as 'x' in the earlier
  * specified expression and output the expression result.
  * @param exp Expr object that will 
  * @return Flag value specifying if the program should be closed
  */
  private static boolean getArgsAndCalculate(Expr exp) {
    out.println("Please enter a number that will be used as 'x' parameter.");
    out.println("To quit, write 'exit' and submit.");
    out.println("To write another expression, write 'next' and submit.");
    while (true) {
      try {
        String input = TextIO.getln(); // input
        if (input.equals("exit")) {    // user wants to quit the program
          out.println("Bye");
          return false;
        }
        else if (input.equals("next")) // user wants to write new expression
          return true;

        double x = Double.parseDouble(input); // 'x' parameter
        if (Double.isNaN(x)) {
          out.println(x + " is not a number.");
          return false;
        }
        else {
          out.println(exp.value(x));
        }
      }
      catch (IllegalArgumentException e) {
        out.println("Error." + e.getMessage());
        return false;
      }
    }
  }
}