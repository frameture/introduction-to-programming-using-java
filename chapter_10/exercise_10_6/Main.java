//Depends on TextIO Class.

public class Main {
  public static void main(String[] args) { 
   printIntro(); // Print the briefing.
   Interpreter interpreter = new Interpreter(); // Interpreter object.
   
    while (true) {
      TextIO.skipBlanks();
      if (TextIO.peek() == '\n')
        break; // A blank input line ends the while loop and the program.
      try {
        String command = TextIO.getWord();
        if (command.equalsIgnoreCase("print"))
          interpreter.printCommand(); // Command: print.
        else if (command.equalsIgnoreCase("let"))
          interpreter.letCommand();  // Command: let.
        else
           // Illegal input.
          throw new ParseError("Command must begin with 'print' or 'let'.");
        TextIO.getln(); // Read next command.
      }
      catch (ParseError e) {
        System.out.println("\n*** Error in input:   " + e.getMessage());
        System.out.println("*** Discarding input:  " + TextIO.getln());
      }
    }
    System.out.println("\n\nProgram ended.");
  }

  /**
   * Prints informational text to the user.
   */
  private static void printIntro() {
    System.out.println("\n\nEnter command and press return to submit.");
    System.out.println("There are two available commands:\n");
    System.out.println("    print <expression>");
    System.out.println("  or");
    System.out.println("    let <variable> = <expression>");
    System.out.println("\nTo end the program - submit empty input.\n");
    System.out.print("\n?  ");
  }
}
