/**
 * 
 * Write a function that simulates rolling a pair of dice until the total on the 
 * dice comes up to be a given number. The number that you are rolling for is 
 * a parameter to the function. The number of times you have to roll the dice 
 * is the return value of the function. The parameter should be one of the possible 
 * totals: 2, 3, ..., 12. The function should throw an IllegalArgumentException 
 * if this is not the case. Use your function in a program that computes and prints 
 * the number of rolls it takes to get snake eyes. (Snake eyes means that the total 
 * showing on the dice is 2.)
 *
 */
public class Exercise_4_3 {
	public static void main(String[] args) {
		System.out.println("Please type a number which you want to obtain after "
				+ "rolling two dice.");
		int input = TextIO.getlnInt(),	// user's input;
			rollCount = 0;				// rolling counter;
		try {
			rollCount = rollDice(input);
		} catch(IllegalArgumentException e) {
			System.out.println(e);
			return;
		}
		System.out.println("There were needed "+rollCount+" tries to get "+input);
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
			throw new IllegalArgumentException("Impossible number to obtain (n > 1 && n < 13");
		int counter = 0,	// return value;
			die1, die2;		// dice in process;
		while (true) {
			die1 = (int)(6 * Math.random()) + 1;
			die2 = (int)(6 * Math.random()) + 1;
			counter++;
			if (die1 + die2 == valueToObtain)
				break;
		}
		return counter;
	}
}
