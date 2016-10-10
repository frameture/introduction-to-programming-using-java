public class PairOfDice {
	private int die1;
	private int die2;
		/**
		 * Constructor of PairOfDice instance
		 */
	public PairOfDice() {
		roll();
	}
	public PairOfDice(int die1, int die2) {
		if(die1 < 1 || die2 < 1 || die1 > 6 || die2 > 6)	// error case
			throw new IllegalArgumentException("Illegal die number.");
		this.die1 = die1;
		this.die2 = die2;
	}
		/**
		 * Method to roll the dice.
		 */
	public void roll(){
		die1 = (int)(Math.random() * 6) + 1;
		die2 = (int)(Math.random() * 6) + 1;
	}
		/**
		 * getter method for private integer die1
		 * @return the value of die1
		 */
	public int getDie1(){
		return die1;
	}
		/**
		 * getter method for private integer die2
		 * @return the value of die2
		 */
	public int getDie2(){
		return die2;
	}
	/**
	 * getter method for total of two dice
	 * @return total of tow dice
	 */
	public int getTotal(){
		return die1 + die2;
	}
		/**
		 * 
		 * @return die1 value as a String
		 */
	public String die1ToString(){
		return "" + die1;
	}
		/**
		 * 
		 * @return die2 value as a String
		 */
	public String die2ToString(){
		return "" + die2;
	}
	public String totalToString(){
		return "" + getTotal();
	}
	
	public String toString(){
		return "The number from first die is " + die1 + " and from second is " + 
				die2 + ".";
	}
}
