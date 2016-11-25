/**
 * 
 * Write a program that helps the user count his change. The program should ask 
 * how many quarters the user has, then how many dimes, then how many
 * nickels, then how many pennies. Then the program should tell the user how much
 * money he has, expressed in dollars.
 * 
 * No error checking - user is expected to provide positive, decimal value.
 */
public class Exercise_4 {
  public static void main(String[] args) {
    // declaring all necessary variables;
    int quarter, dime, nickel, penny;
    double quarterD, dimeD, nickelD, pennyD; 
    double wholeAmount;

    // asking the user for inputs and storing them in proper variables;
    System.out.println("Please write how many quarters do you have?");
    quarter = TextIO.getlnInt();
    // variables with 'D' suffix hold dollar expressed values of coins;
    quarterD = quarter * 0.25;

    System.out.println("Please write how many dimes do you have?");
    dime = TextIO.getlnInt();
    dimeD = dime * 0.1;

    System.out.println("Please write how many nickels do you have?");
    nickel = TextIO.getlnInt();
    nickelD = nickel * 0.05;

    System.out.println("Please write how many pennies do you have?");
    penny = TextIO.getlnInt();
    pennyD = penny * 0.01;

    // calculating the sum of coins and printing it;
    wholeAmount = quarterD + dimeD + nickelD + pennyD;
    System.out.printf("Your total amount of money equals $%1.2f. %n", wholeAmount);
  }
}
