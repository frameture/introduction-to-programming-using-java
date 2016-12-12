/**
 * Represents an expression node that holds a number.
 */
public class ConstNode extends ExpNode {
  double number;  // The number.
  ConstNode(double val) {
    number = val;
  }
  double value(double x) {
    // The value of the node is the number that it contains.
    return number;
  }
  void printStackCommands() {
    // On a stack machine, just push the number onto the stack.
    System.out.println("  Push " + number); 
  }
}