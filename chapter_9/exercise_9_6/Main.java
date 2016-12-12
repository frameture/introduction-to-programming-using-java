// Depends on TextIO Class.

/*  modification -- program allows for occurrence of 'x' variable.

This program reads standard expressions typed in by the user. 
The program constructs an expression tree to represent the
expression.  It then prints the value of the tree.  It also uses
the tree to print out a list of commands that could be used
on a stack machine to evaluate the expression.
The expressions can use positive real numbers and
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
        ExpNode exp = ExpNode.expressionTree(); 
        TextIO.skipBlanks();
        if ( TextIO.peek() != '\n' )
          // There is still some input.
          throw new ParseError("Extra data after end of expression.");
        
        TextIO.getln();
        for (int i = 0; i < 4; i++) 
          System.out.println("\nResult of the expression for 'x = "
                     + i + "' is " + exp.value(i));

        System.out.println("\nOrder of postfix evaluation is:\n");
        exp.printStackCommands(); // Print postfix evaluation of the expression tree
        
      } catch (ParseError e) {
        System.out.println("\n*** Error in input:  " + e.getMessage());
        System.out.println("*** Discarding input:  " + TextIO.getln());
      }
    }
    System.out.println("\n\nDone.");
  }
}