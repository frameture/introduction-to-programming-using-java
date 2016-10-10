public class Test {
	public static void main(String[] args) {
		Deck deck = new Deck(false);	// instance of a Deck class
		BlackJackHand hand = new BlackJackHand();	// instance of a BlackJackHand
		while (true) {
			deck.shuffle();	// Shuffling the deck
			int random = (int)(5 * Math.random()) + 2;	// Picking random hand's size (between 2 and 6)	
			System.out.println("Your cards are: ");
			Card c; // 
			for (int i = 0; i < random; i++) {
				c = deck.dealCard();
				hand.addCard(c, i);
				System.out.println(c);
			}
			System.out.printf("And your hand's value is: %1s", hand.getBlackJackValue());
			System.out.println();
			hand.clear(); // emptying the hand
			System.out.println("To get new hand type 'y' if not type 'n'."); 
				// test for terminating program
			if (!TextIO.getBoolean()) { // user wants to exit the program
				System.out.println();
				System.out.println("Bye!");
				break;
			}
		}
	}
}
