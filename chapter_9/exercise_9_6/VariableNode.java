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
  void printStackCommands() {
    // On a stack machine, just push the note onto the stack.
    System.out.println("  Push variable X");      
  }
}