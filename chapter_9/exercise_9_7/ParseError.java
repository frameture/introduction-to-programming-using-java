/**
 * An object of type ParseError represents a syntax error found in 
 * the user's input.
 */
 public class ParseError extends Exception {
  ParseError(String message) {
  	// simply create Exception object with specified message.
    super(message);
  }
} 
