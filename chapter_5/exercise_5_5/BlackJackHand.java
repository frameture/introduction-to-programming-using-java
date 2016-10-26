package Toolbox;
/**
 * 
 * BlackJackHand object represents a Hand object adopted to a blackjack game.
 * Default constructor creates a Hand object containing 6 empty slots. The 
 * BlackJackHand adds new behavior to the Hand class - the getBlackJackValue()
 * method which returns an integer of a current BlackJackHand object adopted
 * from the BlackJack card game.
 *
 */
public class BlackJackHand extends Hand {
		/**
		 * Constructor of BlackJackHand object with 6 empty slots.
		 */
	public BlackJackHand() {
		this(6); // call to its main constructor
	}
	/**
	 * Constructor of BlackJackHand object with specified empty slots.
	 * @param emptySlots specify how many empty slots the BlackJackHand
	 * object should contain.
	 */
	public BlackJackHand(int emptySlots) {
		super(emptySlots); // call to its super - Hand constructor
	}
	/**
	 * @return Integer specifying the blackjack value of current Hand object
	 */
	public int getBlackJackValue() {
		int handsValue = 0;		// Value of current hand
		boolean isAce = false;	// Test for having an ace in hand
		int cardValue;
		for (int i = 0; i < getCardsCount(); i++) { // loop for getting value from each card
			cardValue = hand[i].getValue();
			if (cardValue > 10) // maximal value that can be added in blackjack hand
				handsValue += 10;
			else if ( cardValue == 1) { // there is an Ace card
				isAce = true;
				handsValue += 1;
			}
			else
				handsValue += cardValue;
		}
		if (isAce)
			if ((handsValue + 10) <= 21)
				handsValue += 10;
		return handsValue;
	}
}
