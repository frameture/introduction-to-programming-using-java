import exercise_5_1.PairOfDice;
import exercise_5_2.StatCalc;
/**
 * 
 * This problem uses the PairOfDice class from Exercise 5.1 and the StatCalc class
 * from Exercise 5.2. 
 * 
 * The program in Exercise 4.4 performs the experiment of counting how many times
 * a pair of dice is rolled before a given total comes up. It repeats this 
 * experiment 10000 times and then reports the average number of rolls. It 
 * does this whole process for each possible total (2, 3, ..., 12).
 * 
 * Redo that exercise. But instead of just reporting the average number of rolls, 
 * you should also report the standard deviation and the maximum number of rolls. 
 * Use a PairOfDice object to represent the dice. Use a StatCalc object to compute 
 * the statistics. (You'll need a new StatCalc object for each possible total
 * , 2, 3, ..., 12. You can use a new pair of dice if you want, but it's not required.)
 *
 */
public class Exe_5_3_Extended_Exe_4_4 {
	private static final int NUMBER_OF_EXPERIMENTS = 10000;
	private static StatCalc[] stats = new StatCalc[11]; // array holding calculator for each die number
	private static PairOfDice dice = new PairOfDice(); // instance of PairOfDice class	
	public static void main(String[] args) {
		System.out.println("Total on dice	 Average Number of Rolls   Standard Deviation   Max Tries");
		System.out.println("-------------	 -----------------------   ------------------   --------- ");
		for (int i = 0; i < 11; i++) // create 11 StatCalc objects
			stats[i] = new StatCalc();
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < NUMBER_OF_EXPERIMENTS; j++)
				stats[i].enter(rollDice(i+2));
			System.out.printf("%7s %23.4s %21.4s %15s %n",(i+2), stats[i].getMean(), 
					stats[i].getStandardDeviation(), (int)(stats[i].getMaxEnter()));
		}
		System.out.println("-------------	 -----------------------   ------------------   --------- ");
	}
			/**
			 * 
			 * @param valueToObtain
			 * @return Returns the amount of times needed to get aggregate number of dice that equals
			 * the given total that is equal to the valueToObtain parameter.
			 * @throws IllegalArgumentException when the value lower than 2 and higher than 12.
			 */
		private static int rollDice(int valueToObtain) {
			if(valueToObtain < 2 || valueToObtain > 12)
				throw new IllegalArgumentException("Impossible dice number to obtain (n>1 && n<13");
			int counter = 0;
			while(true) {
				dice.roll();
				counter++;
				if (dice.getTotal() == valueToObtain)
					break;
			}
			return counter;
		}
}
