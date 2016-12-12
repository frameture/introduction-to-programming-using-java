/**
 * Represents an expression node that holds a number.
 */
public class ConstNode extends ExpNode {
  double number;  // The number.
  
  ConstNode(double val) {
    number = val;
  }
  
  ConstNode derivative() {
    // The derivative of constant = 0
    return new ConstNode(0);
  }
  
  void printInfix() {
    System.out.print(number);
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