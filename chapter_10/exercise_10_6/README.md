The sample program SimpleInterpreter.java from Subsection 10.4.1 can carry out 
commands of the form "let variable = expression" or "print expression". That 
program can handle expressions that contain variables, numbers, operators, and 
parentheses. Extend the program so that it can also handle the standard mathematical 
functions sin, cos, tan, abs, sqrt, and log. For example, the program should be 
able to evaluate an expression such as sin(3*x-7)+log(sqrt(y)), assuming that 
the variables x and y have been given values. Note that the name of a function 
must be followed by an expression that is enclosed in parentheses.

In the original program, a symbol table holds a value for each variable that has 
been defined. In your program, you should add another type of symbol to the table 
to represent standard functions. You can use the following nested enumerated type 
and class for this purpose:

    private enum Functions { SIN, COS, TAN, ABS, SQRT, LOG }

    /**
     * An object of this class represents one of the standard functions.
     */
    private static class StandardFunction {

       /**
        * Tells which function this is.
        */
       Functions functionCode; 

       /**
        * Constructor creates an object to represent one of 
        * the standard functions
        * @param code which function is represented.
        */
       StandardFunction(Functions code) {
          functionCode = code;
       }

       /**
        * Finds the value of this function for the specified 
        * parameter value, x.
        */
       double evaluate(double x) {
          switch(functionCode) {
          case SIN:
             return Math.sin(x);
          case COS:
             return Math.cos(x);
          case TAN:
             return Math.tan(x);
          case ABS:
             return Math.abs(x);
          case SQRT:
             return Math.sqrt(x);
          default:
             return Math.log(x);
          }
       }
    } // end class StandardFunction

Add a symbol to the symbol table to represent each function. The key is the name 
of the function and the value is an object of type StandardFunction that represents 
the function. For example:

    symbolTable.put("sin", new StandardFunction(Function.SIN));

In SimpleInterpreter.java, the symbol table is a map of type HashMap<String,Double>. 
It's not legal to use a StandardFunction as the value in such a map, so you will 
have to change the type of the map. The map has to hold two different types of objects. 
The easy way to make this possible is to create a map of type HashMap<String,Object>. 
(A better way is to create a general type to represent objects that can be values 
in the symbol table, and to define two subclasses of that class, one to represent 
variables and one to represent standard functions, but for this exercise, you 
should do it the easy way.)

In your parser, when you encounter a word, you have to be able to tell whether 
it's a variable or a standard function. Look up the word in the symbol table. 
If the associated object is non-null and is of type Double, then the word is 
a variable. If it is of type StandardFunction, then the word is a function. 
Remember that you can test the type of an object using the instanceof operator. 
For example: 
    if (obj instanceof Double) 