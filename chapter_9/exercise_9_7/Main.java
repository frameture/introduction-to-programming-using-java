// depends on TextIO Class.

 /*  This program reads standard expressions typed in by the user. 
  The program constructs an expression tree to represent the
  expression.  It computes the derivative of the expression and
  prints out the derivative and the value of the derivative at
  several values of x.  It also prints out a list of commands 
  that could be used on a stack machine to evaluate the derivative.
  The expressions can use the variable "x", positive real numbers, and
  the binary operators +, -, *, and /.  The unary minus operation
  is supported.  The expressions are defined by the BNF rules:

    <expression>  ::=  [ "-" ] <term> [ [ "+" | "-" ] <term> ]...

    <term>  ::=  <factor> [ [ "*" | "/" ] <factor> ]...

    <factor>  ::=  <number>  | <x-variable> |  "(" <expression> ")"
    
    <x-variable> ::= "x" | "X"

  A number must begin with a digit (i.e., not a decimal point).
  A line of input must contain exactly one such expression.  If extra
  data is found on a line after an expression has been read, it is
  considered an error.
 */

public class Main {
  public static void main(String[] args) {
    while (true) {
      System.out.println("\n\nEnter an expression, or press return to end.");
      System.out.print("\n?  ");
      TextIO.skipBlanks();
      if ( TextIO.peek() == '\n' )
        // user wants to exit the program
        break;
      
      try {
        //  Try to create an expression tree and output the result
        ExpNode deriv = ExpNode.expressionTree().derivative(); 
        TextIO.skipBlanks();
        if ( TextIO.peek() != '\n' )
          // There is still some input.
          throw new ParseError("Extra data after end of expression.");
        
        TextIO.getln();
        for (int i = 0; i < 4; i++) 
          System.out.println("\nThe derivative for 'x = "
                     + i + "' is " + deriv.value(i));

        System.out.println("\nOrder of infix evaluation of the derivative is:\n");
        deriv.printInfix(); // Print infix evaluation of the expression tree
        
      } catch (ParseError e) {
        System.out.println("\n*** Error in input:  " + e.getMessage());
        System.out.println("*** Discarding input:  " + TextIO.getln());
      }
    }
    System.out.println("\n\nDone.");
  }
}