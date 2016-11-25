/** If you have N eggs, then you have N/12 dozen eggs, with N%12 eggs left 
 * over. (This is (solution)essentially the definition of the / and % operators
 * for integers.) Write a program that asks the user how many eggs she has and 
 * then tells the user how many dozen eggs she has and how many extra eggs are
 * left over. A gross of eggs is equal to 144 eggs. Extend your program so that
 * it will tell the user how many gross, how many dozen, and how many left over
 * eggs she has. For example, if the user says that she has 1342 eggs, then your
 * program would respond with:
 * 
 * Your number of eggs is 9 gross, 3 dozen, and 10 since 1342 is equal 
 * to 9*144 + 3*12 + 10.
 */
public class Exercise_5 {
  public static void main(String[] args) {
    // variables holding egg values
    int gross, dozen, left;

    // asking for number of eggs
    System.out.println("How many eggs do you have? Please type a number.");
    int inputEgg = TextIO.getlnInt();

    gross = inputEgg / 144;
    dozen = inputEgg % 144 / 12;
    left = inputEgg % 144 % 12;

    // printing the result
    System.out.printf("Your number of eggs is %d gross, %d dozens and %d "
	  + "since %d is equal to %d * 144 + %d * 12 + %d", gross, dozen, left, 
	  inputEgg, gross, dozen, left);
  }
}
