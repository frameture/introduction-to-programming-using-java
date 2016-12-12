/**
 * An expression node to represent a unary minus operator.
 */
public class UnaryMinusNode extends ExpNode {
  ExpNode operand;  // The operand to which the unary minus applies.
  
  public UnaryMinusNode(ExpNode operand) {
    assert operand != null;
    this.operand = operand;
  }
  
  double value(double x) {
      // The value is the negative of the value of the operand.
    double neg = operand.value(x);
    return -neg;
  }
  
  ExpNode derivative() {
  	return new UnaryMinusNode(operand.derivative());
  }
  
  void printInfix() {
  	System.out.print("((-)");
  	operand.printInfix();
  	System.out.print(")");
  }
  
  void printStackCommands() {
    operand.printStackCommands();
    System.out.println("  Unary minus");
  }
}
