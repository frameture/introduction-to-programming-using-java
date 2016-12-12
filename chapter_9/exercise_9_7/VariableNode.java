/**
 * An expression node to represent a variable 'x'
 */
public class VariableNode extends ExpNode {

  @Override
  double value(double x) {
    // The value of the node is the number that is passed to the method.
    return x;
  }
  
  @Override
  ConstNode derivative() {
    // The derivative of 'x' = 1.
    return new ConstNode(1);
  }
  
  @Override
  void printInfix() {
    System.out.print("x");
  }

  @Override
  void printStackCommands() {
    // On a stack machine, just push the note onto the stack.
    System.out.println("  Push variable X");      
  }
}