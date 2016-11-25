/**
 * 
 * Write a program that will evaluate simple expressions such as 17 + 3 and
 * 3.14159 * 4.7. The expressions are to be typed in by the user. The operand1 
 * always consists of a number, followed by an operator, followed by another 
 * number. The operators that are allowed are +, -, *, and /. You can read the 
 * numbers with TextIO.getDouble() and the operator with TextIO.getChar(). Your
 * program should read an expression, print its value, read another expression,
 * print its value, and so on. The program should end when the user enters 0 as
 * the first number on the line.
 *
 */

public class Exercise_3_3 {
  public static void main(String[] args) {	
    System.out.printf("%n	Welcome to the calculator! %n");

    // infinite loop - the calculator works until the user wishes to exit;
    while(true) {
      System.out.printf("%n	Please type a simple expression.");
      System.out.printf("%n 	To close the calculator type 0.");

      double operand1, operand2;
      char operator;
      double result;

      operand1 = TextIO.getDouble();
      // exit the calculator;
      if (operand1 == 0 ) {
        System.out.println("\n	Calculator Closed");
          break;
      }
      operator = TextIO.getChar();
      operand2 = TextIO.getlnDouble();	

      // switch statement - determining the actual expression type;
      switch (operator) {
        case '+':
          result = operand1 + operand2;
          System.out.printf("%n	"+operand1+" "+operator+" "+operand2+" = "+result + "%n");
          break;
        case '-':
          result = operand1 - operand2;
          System.out.printf("%n	"+operand1+" "+operator+" "+operand2+" = "+result + "%n");
          break;
        case '/':
          result = operand1 / operand2;
          System.out.printf("%n	"+operand1+" "+operator+" "+operand2+" = "+result + "%n");
          break;
        case '*':
          result = operand1 * operand2;
          System.out.printf("%n	"+operand1+" "+operator+" "+operand2+" = "+result + "%n");
          break;		
      }
    }
  }
}
