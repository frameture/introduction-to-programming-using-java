/**
 * An object of this class represents one of the standard functions.
 * Objects of this type are stored in the symbol table, associated
 * with the name of the standard functions.  Note that an object
 * of this type also knows how to evaluate the corresponding function.
 */
public class StandardFunction {  
  // An enumerated type whose values represent the possible standard functions.
  public static enum Functions { SIN, COS, TAN, ABS, SQRT, LOG };
  private Functions functionCode; // Determines which function represents this object.

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
  public double evaluate(double x) {
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
}