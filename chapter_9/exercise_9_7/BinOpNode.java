/**
 * An expression node representing a binary operator.
 */
public class BinOpNode extends ExpNode {
  char op;    // The operator.
  ExpNode left;   // The expression for its left operand.
  ExpNode right;  // The expression for its right operand.
  BinOpNode(char op, ExpNode left, ExpNode right) {
      // Construct a BinOpNode containing the specified data.
    assert op == '+' || op == '-' || op == '*' || op == '/';
    assert left != null && right != null;
    this.op = op;
    this.left = left;
    this.right = right;
  }
  
  double value(double xValue) {
      // The value is obtained by evaluating the left and right
      // operands and combining the values with the operator.
    double x = left.value(xValue);
    double y = right.value(xValue);
    switch (op) {
    case '+':  return x + y;
    case '-':  return x - y;
    case '*':  return x * y;
    case '/':  return x / y;
    default:   return Double.NaN;  // Bad operator!
    }
  }
  
  @Override
  ExpNode derivative() {
    switch (op) {
    case '+':  return new BinOpNode(op, left.derivative() , right.derivative());
    case '-':  return new BinOpNode(op, left.derivative(), right.derivative());
    //  A*dB + B*dA.
    case '*':  return new BinOpNode('+', 
        new BinOpNode('*', left, right.derivative()),
        new BinOpNode('*', left.derivative(), right));
    
    //  (B*dA - A*dB) / (B*B).
    default:  return new BinOpNode('/',
           new BinOpNode('-', 
            new BinOpNode('*', right, left.derivative()), 
            new BinOpNode('*', left, right.derivative())), 
            new BinOpNode('*', right, right));
    }  
  }
  
  void printInfix() {
    System.out.print("(");
    left.printInfix();
    System.out.print(" " + op + " ");
    right.printInfix();
    System.out.print(")");
  }
  
  void  printStackCommands() {
      // To evaluate the expression on a stack machine, first do
      // whatever is necessary to evaluate the left operand, leaving
      // the answer on the stack.  Then do the same thing for the
      // second operand.  Then apply the operator (which means popping
      // the operands, applying the operator, and pushing the result).
    left.printStackCommands();
    right.printStackCommands();
    System.out.println("  Operator " + op);
  }
}