/**
 * 
 * Test program destined to check a PairOfDice class.
 *
 */
public class Test {	
  public static void main(String[] args){
    PairOfDice firstPair = new PairOfDice(6, 6);	// instance of PairOfDice
    int die1, die2;		// two dice
    int counter = 0;	// rolls counter
    do {
      counter++;
      firstPair.roll();
      die1 = firstPair.getDie1();
      die2 = firstPair.getDie2();
    } while ((die1 + die2) != 2);	// while dice don't give a total of 2
    System.out.printf("%nIt took %1s times to roll the dice to get 'Snake Eyes'.",
        counter);
  }
}
