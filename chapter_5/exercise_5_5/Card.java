/**
 *  Object of a Card class represents an idea of a physical playing card.
 *  A card my represent one of the 4 suits and one of the 13 number values.
 *  
 *  Suits: spades, hearts, diamonds, clubs and in certain situations a joker.
 *  Numbers: 2 to 10; 1 representing an ace, 11 a jack, 12 a queen and 13 a king.
 *  
 */
public class Card {
		// Initializing suits of a card;
	public final static int SPADES = 0;
	public final static int HEARTS = 1;
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;
	public final static int JOKER = 4;
	public final static int ACE = 1;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;
	private final int suit; // suit of a current card
	private final int value; // number value of a current card
	/**
	 * Constructor of a Card object with specific suit and value.
	 * @param value For cards different than JOKER, value is in the range 
	 * between 1 and 13.
	 * @param suit one of the Named Constants in instance variable suit.
	 */
	public Card(int value, int suit){
		if (suit != JOKER && suit != SPADES && suit != HEARTS 
				&& suit != CLUBS && suit != DIAMONDS) // wrong suit
			throw new IllegalArgumentException("Illegal suit of card.");
		if (suit != JOKER)
			if (value < 1 && value > 13) // wrong number value
				throw new IllegalArgumentException("Illegal value of card.");
		this.value = value;
		this.suit = suit;
	}
		/**
		 * Function to get value of instance variable 'value' of
		 * the object of Card class
		 * @return integer value
		 */
	public int getValue(){
		return value;
	}
		/**
		 * Function to get value of instance variable 'suit'
		 * of the object of Card class.
		 * @return integer value
		 */
	
	public int getSuit(){
		return suit;
	}
		/**
		 * 
		 * @return String value of a instance 'value' member of current Card object. 
		 */
	public String getValueAsString(){
		if (suit == JOKER)
			return ("#" + value);
		else 
			switch(value){
			case 1:
				return "Ace";
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				return "" + value; // typecast integer to String
			case 11:
				return "Jack";
			case 12:
				return "Queen";
			default: 
				return "King";			
			}
	}
		/**
		 * String value of a instance 'suit' member of current Card object. 
		 * @return
		 */
	public String getSuitAsString() {
		switch (suit) {
		case 0:
			return "Spades";
		case 1:
			return "Hearts";
		case 2:
			return "Diamonds";
		case 3:
			return "Clubs";
		default:
			return "Joker";
		}
	}	
	/**
	 * Return the concatenating String of the 'suit' and 'value' variables of Card class.
	 */
	
	public String toString(){
		return getValueAsString() + " of " + getSuitAsString();
	}
}
