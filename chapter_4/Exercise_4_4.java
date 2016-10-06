/**
 * 
 * This exercise builds on Exercise 4.3. Every time you roll the dice repeatedly, 
 * trying to get a given total, the number of rolls it takes can be different. 
 * The question naturally arises, what's the average number of rolls to get 
 * a given total? Write a function that performs the experiment of rolling to 
 * get a given total 10000 times. The desired total is a parameter to the 
 * subroutine. The average number of rolls is the return value. Each individual 
 * experiment should be done by calling the function you wrote for Exercise 4.3. 
 * Now, write a main program that will call your function once for each of the 
 * possible totals (2, 3, ..., 12).
 *
 */
public class Exercise_4_4 {
	private static final int NUMBER_OF_EXPERIMENTS = 10000;	// named constant;	
	public static void main(String[] args) {
		System.out.println("Total on dice		Average Number of Rolls");
		System.out.println("-------------		-----------------------");
		double rollingAverage;	// mean of a given dice total; 
		for(int i = 2; i <= 12; i++) {
			try {
				rollingAverage = rollDiceAverage(i);
			} catch(IllegalArgumentException e) {
				System.out.println(e);
				return;
			}
			System.out.println("     "+ i +"			        " + rollingAverage);
		}
		System.out.println("-------------		-----------------------");
	}
	/**
	 * 
	 * @param total of 2 dice to calculate average for.
	 * @return average of needed rolls to obtain given 'total' by 'NUMBER_OF_EXPERIMENT' 
	 */
		private static double rollDiceAverage(int total) {
			double aggregateNum = 0;
			for (int i = 0; i < NUMBER_OF_EXPERIMENTS; i++) {
				aggregateNum += rollDice(total);
			}
		return aggregateNum / NUMBER_OF_EXPERIMENTS;
	}
		/**
		 * 
		 * @param valueToObtain
		 * @return Returns the amount of times needed to get aggregate number of dice that equals
		 * the given total that is input by a user.
		 * @throws IllegalArgumentException when the value lower than 2 and higher than 12.
		 */
	private static int rollDice(int valueToObtain) {
		if (valueToObtain < 2 || valueToObtain > 12)	// illegal argument;
			throw new IllegalArgumentException("Impossible number to obtain (n > 1 && n < 13)");
		int counter = 0,	// return value;
			die1, die2;		// dice in process;
		while(true){
			die1 = (int)(6 * Math.random()) + 1;
			die2 = (int)(6 * Math.random()) + 1;
			counter++;
			if(die1 + die2 == valueToObtain)
				break;
		}
		return counter;
	}
}
