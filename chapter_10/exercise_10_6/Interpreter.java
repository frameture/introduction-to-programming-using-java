import java.util.HashMap;

// Program depends on non-standard TextIO Class.

/**
 * Interpreter provides a way to teach the program to work as a programming language.
 * It provides only two commands: 'let', which introduces new variables to the interpreter
 * and 'print', which can show the values stored in the variables.  
 */
public class Interpreter {
  // The symbolTable contains information about the values of variables.  When a variable 
  // is assigned a value, it is recorded in the symbol table. The key is the name of the 
  // variable, and the  value is an object of type Double that contains the value of the 
  // variable. The symbol table can also contain standard functions.  The key is the name of
  // of the function, and the value is the corresponding StandardFunction object. 
  public HashMap<String, Object> symbolTable;
  
  /**
   * Interpreter's constructor. 
   */
  public Interpreter() {
       // Create the map that represents symbol table.
      symbolTable = new HashMap<String, Object>();

       // To start, add variables named "pi" and "e" to the symbol
       // table.  Their values are the usual mathematical constants.
      symbolTable.put("pi", Math.PI);
      symbolTable.put("e", Math.E);
      
       // Add the standard functions to the hash table.
      symbolTable.put("sin", new StandardFunction(StandardFunction.Functions.SIN));
      symbolTable.put("cos", new StandardFunction(StandardFunction.Functions.COS));
      symbolTable.put("tan", new StandardFunction(StandardFunction.Functions.TAN));
      symbolTable.put("abs", new StandardFunction(StandardFunction.Functions.ABS));
      symbolTable.put("sqrt", new StandardFunction(StandardFunction.Functions.SQRT));
      symbolTable.put("log", new StandardFunction(StandardFunction.Functions.LOG));
  }
  
  /**
   * Process a command of the form  let <variable> = <expression>.
   * When this method is called, the word "let" has already been read. The 
   * variable name is read and the following expression. Then the value of 
   * that variable is stored in the Interpreter object.
   */
  public void letCommand() throws ParseError {
    TextIO.skipBlanks();
    if ( ! Character.isLetter(TextIO.peek()) )
      throw new ParseError("Expected variable name after 'let'.");
    
    String varName = readWord(); // The name of the variable.
    TextIO.skipBlanks();
    if ( TextIO.peek() != '=' )  // '=' sign is expected.
      throw new ParseError("Expected '=' operator for 'let' command.");
    
    TextIO.getChar();
    double val = expressionValue(); // Read the variable's value.
    TextIO.skipBlanks();
    if ( TextIO.peek() != '\n' ) // User wrote some more unexpected text.
      throw new ParseError("Extra data after end of expression.");
    
    symbolTable.put(varName, val); // Add new variable and its value to the symbol table.
    System.out.println("Succeded.");
  }

  /**
   * Process a command of the form  print <expression>.
   * When this method is called, the word "print" has already
   * been read.  Evaluate the expression and print the value.
   */
  public void printCommand() throws ParseError {
    double val = expressionValue(); // Read the expression.
    TextIO.skipBlanks();
    if ( TextIO.peek() != '\n' )
      throw new ParseError("Extra data after end of expression.");
    System.out.println("Value is " + val); // Print the expression's value.
  }

  /**
   * Read an expression from the current line of input and return its value.
   */
  public double expressionValue() throws ParseError {
    TextIO.skipBlanks();
    boolean negative = false; // True if there is a leading minus sign.
    if (TextIO.peek() == '-') {
      TextIO.getAnyChar();
      negative = true;
    }
    
    double val = termValue(); // Expression must start with a term.
    if (negative)
      val = -val; // Apply the leading minus sign
    TextIO.skipBlanks();
    while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {
        // Read the next term and add it to or subtract it from
        // the value of previous terms in the expression.
      char op = TextIO.getAnyChar();
      double nextVal = termValue();
      if (op == '+')
        val += nextVal; 
      else
        val -= nextVal;
      TextIO.skipBlanks();
    }
    return val;
  } // end expressionValue()

  /**
   * Read a term from the current line of input and return its value.
   */
  private double termValue() throws ParseError {
    TextIO.skipBlanks();
    double val;  // The value of the term.
    val = factorValue();  // A term must start with a factor.
    TextIO.skipBlanks();
    while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
        // Read the next factor, and multiply or divide
        // the value-so-far by the value of this factor.
      char op = TextIO.getAnyChar();
      double nextVal = factorValue();
      if (op == '*')
        val *= nextVal;
      else
        val /= nextVal;
      TextIO.skipBlanks();
    }
    return val;
  } // end termValue()

  /**
   * Read a factor from the current line of input and return its value.
   */
  private double factorValue() throws ParseError {
    TextIO.skipBlanks();
    double val;  // Value of the factor.
    val = primaryValue(); // A factor must start with a primary.
    TextIO.skipBlanks();
    while ( TextIO.peek() == '^' ) {
        // Read the next primary, and exponentiate
        // the value-so-far by the value of this primary.
      TextIO.getChar();
      double nextVal = primaryValue();
      val = Math.pow(val, nextVal);
      if (Double.isNaN(val))
        throw new ParseError("Illegal values for ^ operator.");
      TextIO.skipBlanks();
    }
    return val;
  } // end termValue()

  /**
   *  Read a primary from the current line of input and
   *  return its value.  A primary must be a number,
   *  a variable, or an expression enclosed in parentheses.
   */
  private double primaryValue() throws ParseError {
    TextIO.skipBlanks();
    char ch = TextIO.peek();
    if ( Character.isDigit(ch) ) {
        // The factor is a number.  Read it and
        // return its value.
      return TextIO.getDouble();
    }
    else if ( Character.isLetter(ch) ) {
        // The factor is a variable or a standard function.  Read its name and
        // look up its value in the symbol table.  If the name is not in the symbol table,
        // an error occurs.  (Note that the values in the symbol table are objects of type 
        // Double or StandardFunction.)
      String name = readWord();
      Object obj = symbolTable.get(name);
      if (obj == null)
        throw new ParseError("Unknown word \"" + name + "\"");
      assert (obj instanceof Double || obj instanceof StandardFunction);
      if (obj instanceof Double) {
          // The name is a variable; return value of that variable.
        Double val = (Double)obj;
        return val.doubleValue();
      }
      else {
          // The name is a standard function.  Read the argument
          // of the function and return the value of the function
          // at that argument.  The argument must be an expression
          // in parentheses.
        StandardFunction func = (StandardFunction)obj;
        TextIO.skipBlanks();
        if ( TextIO.peek() != '(' )
          throw new ParseError("Parenthesis missing after standard function");
        TextIO.getChar(); // discard the '('
        double argument = expressionValue();  // read and evaluate expression
        TextIO.skipBlanks();
        if ( TextIO.peek() != ')' )
          throw new ParseError("Missing right parenthesis.");
        TextIO.getChar(); // discard the ')'
        return func.evaluate(argument);
      }
    }
    else if ( ch == '(' ) {
        // The factor is an expression in parentheses.
        // Return the value of the expression.
      TextIO.getAnyChar();  // Read the "("
      double val = expressionValue();
      TextIO.skipBlanks();
      if ( TextIO.peek() != ')' )
        throw new ParseError("Missing right parenthesis.");
      TextIO.getAnyChar();  // Read the ")"
      return val;
    }
    else if ( ch == '\n' )
      throw new ParseError("End-of-line encountered in the middle of an expression.");
    else if ( ch == ')' )
      throw new ParseError("Extra right parenthesis.");
    else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/')
      throw new ParseError("Misplaced operator.");
    else
      throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
  }

  /**
   *  Reads a word from input.  A word is any sequence of
   *  letters and digits, starting with a letter.  When 
   *  this subroutine is called, it should already be
   *  known that the next character in the input is
   *  a letter.
   */
  private String readWord() {
    String word = "";  // The word.
    char ch = TextIO.peek();
    while (Character.isLetter(ch) || Character.isDigit(ch)) {
      word += TextIO.getChar(); // Add the character to the word.
      ch = TextIO.peek();
    }
    return word;
  }
}
