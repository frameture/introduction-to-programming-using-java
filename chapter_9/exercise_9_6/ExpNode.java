// Depends on TextIO Class.

/**
 *  An abstract class representing any node in an expression tree.
 */
abstract public class ExpNode {

  /**
   * Abstract. Returns the result of the expression of given ExpNode instance where each
   * 'x' variable will be substituted for given value.
   * @param x Value to be substituted for 'x' occurrence in the expression
   * @return Result of the expression 
   */
  abstract double value(double x); 
  
  /**
   * Abstract. Prints postfix evaluation of given ExpNode.
   */
  abstract void printStackCommands();
    
  /**
   * Reads an expression from the current line of input and builds
   * an expression tree that represents the expression.
   * @return an ExpNode which is a pointer to the root node of the 
   *     expression tree
   * @throws ParseError if a syntax error is found in the input
   */
  public static ExpNode expressionTree() throws ParseError {
    TextIO.skipBlanks();
    boolean negative;  // True if there is a leading minus sign.
    negative = false;
    if (TextIO.peek() == '-') {
      TextIO.getAnyChar();
      negative = true;
    }
    ExpNode exp;     // The expression tree for the expression.
    exp = termTree();  // Start with the first term.
    if (negative)
      exp = new UnaryMinusNode(exp);
    TextIO.skipBlanks();
    while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {
        // Read the next term and combine it with the
        // previous terms into a bigger expression tree.
      char op = TextIO.getAnyChar();
      ExpNode nextTerm = termTree();
      exp = new BinOpNode(op, exp, nextTerm);
      TextIO.skipBlanks();
    }
    return exp;
  }

  /**
   * Reads a term from the current line of input and builds
   * an expression tree that represents the expression.
   * @return an ExpNode which is a pointer to the root node of the 
   *     expression tree
   * @throws ParseError if a syntax error is found in the input
   */
  public static ExpNode termTree() throws ParseError {
    TextIO.skipBlanks();
    ExpNode term;  // The expression tree representing the term.
    term = factorTree();
    TextIO.skipBlanks();
    while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
        // Read the next factor, and combine it with the
        // previous factors into a bigger expression tree.
      char op = TextIO.getAnyChar();
      ExpNode nextFactor = factorTree();
      term = new BinOpNode(op, term, nextFactor);
      TextIO.skipBlanks();
    }
    return term;
  }

  /**
   * Reads a factor from the current line of input and builds
   * an expression tree that represents the expression.
   * @return an ExpNode which is a pointer to the root node of the 
   *     expression tree
   * @throws ParseError if a syntax error is found in the input
   */
  public static ExpNode factorTree() throws ParseError {
    TextIO.skipBlanks();
    char ch = TextIO.peek();
    if ( Character.isDigit(ch) ) {
        // The factor is a number.  Return a ConstNode.
      double num = TextIO.getDouble();
      return new ConstNode(num);
    }
    else if( ch == 'x' || ch == 'X' ){
      //read the 'x' / 'X'
      TextIO.getAnyChar();
      return new VariableNode();
    }
    else if ( ch == '(' ) {
        // The factor is an expression in parentheses.
        // Return a tree representing that expression.
      TextIO.getAnyChar();  // Read the "("
      ExpNode exp = expressionTree();
      TextIO.skipBlanks();
      if ( TextIO.peek() != ')' )
        throw new ParseError("Missing right parenthesis.");
      TextIO.getAnyChar();  // Read the ")"
      return exp;
    }
    else if ( ch == '\n' )
      throw new ParseError("End-of-line encountered in the middle of an expression.");
    else if ( ch == ')' )
      throw new ParseError("Extra right parenthesis.");
    else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' )
      throw new ParseError("Misplaced operator.");
    else
      throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
  }
}
