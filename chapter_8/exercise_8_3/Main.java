public class Exercise_8_3 {
  public static void main(String[] args) {
    while (true) {
      System.out.println("Enter a roman or arabic numeral.");
      String input = TextIO.getln(); // get input

      try { // try to translate 
        RomanNumeral obj = Character.isDigit(input.charAt(0)) ? new RomanNumeral(Integer.parseInt(input)) : new RomanNumeral(input);
        System.out.println("Roman value is '" + obj.toString() + "'");
        System.out.println("Arabic vlue is '" + obj.toInt() + "'");
        break; // end the program
      } catch (RuntimeException e) {
        System.out.println(e.getMessage() + " Try again.");
      }
    }
  }
}