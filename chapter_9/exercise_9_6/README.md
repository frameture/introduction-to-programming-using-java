      The parsing programs in Section 9.5 work with expressions made up of 
      numbers and operators. We can make things a little more interesting by 
      allowing the variable "x" to occur. This would allow expression such as 
      "3*(x-1)*(x+1)", for example. Make a new version of the sample program 
      SimpleParser3.java that can work with such expressions. In your program, 
      the main() routine can't simply print the value of the expression, since 
      the value of the expression now depends on the value of x. Instead, it 
      should print the value of the expression for x=0, x=1, x=2, and x=3.

      The original program will have to be modified in several other ways. 
	  Currently, the program uses classes ConstNode, BinOpNode, and UnaryMinusNode 
	  to represent nodes in an expression tree. Since expressions can now include x, 
	  you will need a new class, VariableNode, to represent an occurrence of x 
	  in the expression.

      In the original program, each of the node classes has an instance method, 
	  "double value()", which returns the value of the node. But in your program, 
	  the value can depend on x, so you should replace this method with one of 
	  the form "double value(double xValue)", where the parameter xValue is the 
	  value of x.

      Finally, the parsing subroutines in your program will have to take into 
	  account the fact that expressions can contain x. There is just one small 
	  change in the BNF rules for the expressions: A <factor> is allowed to be 
	  the variable x:

      <factor>  ::=  <number>  |  <x-variable>  |  "(" <expression> ")"

      where <x-variable> can be either a lower case or an upper case "X". This change
	  in the BNF requires a change in the factorTree() subroutine.
