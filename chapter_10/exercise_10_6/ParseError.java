/**
 * Represents a syntax error found in the user's input.
 */
public class ParseError extends Exception {
  ParseError(String message) {
    super(message);
  }
}
