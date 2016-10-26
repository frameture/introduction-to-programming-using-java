import static java.lang.System.out;
/**
 * 
 * BlackJackGame depends on Card, Hand, BlackJackHand and Deck classes.
 * Its instance simulates BlackJack game in command line environment. 
 *
 */
public class BlackJackGame {
	private Deck deck;
	private BlackJackHand playerHand;
	private BlackJackHand dealerHand;
	private final static int GAME_CONTINUE = 0;
	private final static int GAME_WIN = 1;
	private final static int GAME_LOST = 2;
	/**
	 * Default constructor of BlackJackGame class
	 */
	public BlackJackGame() {
		deck = new Deck(false);
		playerHand = new BlackJackHand();
		dealerHand = new BlackJackHand();
	}
	/**
	 * Method initializes the game and administers it.
	 * @return true - game is won, false - game is lost.
	 */
	public boolean playGame() {	
		initGame();
		if (calculateHands() == GAME_WIN)
			return true;
		else if (calculateHands() == GAME_LOST)
			return false;
		if (hitOrStand() == GAME_LOST)
			return false;
		if (dealerMove() == GAME_WIN)
			return true;
		if (compareHands() == GAME_WIN)
			return true;
		else
			return false;
	}
	/**
	 * Initializes the game. Deck of cards is shuffled, player's and dealer's 
	 * hands are emptied and new cards are delt to them.
	 */
	private void initGame() {
		deck.shuffle();  // shuffles the deck of cards
		playerHand.clear(); 
		dealerHand.clear();	
		// dealing cards to the dealer and showing the first one
		dealCards(dealerHand, 2, false); 
		out.printf("The first of dealer's cards is %n%1s.%n%n", dealerHand.getCard(0));
		out.println("Your first two cards are");
		out.println();
		// dealing cards to the player and showing all
		dealCards(playerHand, 2, true);
	}
	/**
	 * Determines the current state of game.
	 * @return One of two possible values is returned - depends on dealer
	 * Hand's value. GAME_WIN or GAME_CONTINUE may be returned.
	 */
	private int dealerMove() {
		for (int i = 2; i < 6; i++) {
			 if (dealerHand.getBlackJackValue() > 21) {
				out.println();
				out.println("You won! Dealer got more that 21.");
				return GAME_WIN;
			}
			else if(dealerHand.getBlackJackValue() >= 16)
				break;	// dealer doesn't risk to go over 16
			Card c = deck.dealCard();
			dealerHand.addCard(c, i);
			out.printf("%nDealer's next card is %n%1s.", c);
		}
		return GAME_CONTINUE;
	}
	/**
	 * Determines the current state of game.
	 * @return One of two possible values is returned - depends on dealer 
	 * and player hands' values. GAME_WIN or GAME_LOST may be returned.
	 */
	private int compareHands() {
		if (playerHand.getBlackJackValue() > dealerHand.getBlackJackValue()) {
			out.printf("%n%nYou won!%nYou got %1s and dealer %1s.", playerHand.getBlackJackValue(),
					dealerHand.getBlackJackValue());
			return GAME_WIN;
		} else {
			out.printf("%n%nYou lost!%nYou got %1s and dealer %1s.", playerHand.getBlackJackValue(),
					dealerHand.getBlackJackValue());
			return GAME_LOST;
		}
	}	
	/**
	 * Determines the current state of game. Whether the player wants to get 
	 * another card or not. 
	 * @return One of two possible values is returned - depends on dealer 
	 * and player hands' values. GAME_WIN or GAME_LOST may be returned.
	 */
	private int hitOrStand() {
		for (int i = 2; i < 6; i++) {
			out.printf("Would you like to hit?"
					+ "%n"
					+ "If so, type 'yes'.%n");
			out.printf("If not type 'no'.");
			if (TextIO.getlnBoolean() ) {	// user's response - hit
				playerHand.addCard(deck.dealCard(), i);
				out.printf("Your next card is %1s. %n", playerHand.getCard(i));
				if (playerHand.getBlackJackValue() > 21) {
					out.printf("Your cards' current value is greater than 21.%n"
							+ "You lost.%n");
					return GAME_LOST;
				}
			}
			else 
				break;
		}
		return GAME_CONTINUE;
	}
	/**
	 * Deals specified number of cards 'cardsQuantity' to the specified 
	 * BlackJackHand 'receiverHand' object. 
	 * @param receiverHand BlackJackHand object to which cards will be dealt
	 * @param cardsQuantity Integer of cards quantity to be dealt
	 * @param showCards Boolean specifying if the dealt cards should be
	 * immediately show to the user. False value used when dealing cards
	 * to the 'dealerHand' 
	 */
	private void dealCards(BlackJackHand receiverHand, int cardsQuantity, 
			boolean showCards) {
		for (int i = 0; i < cardsQuantity; i++) {
			Card c = deck.dealCard();
			receiverHand.addCard(c, receiverHand.getCardsCount());
			if (showCards) {
				out.print(c);
				out.println();
			}
		}
		out.println();
	}
	/**
	 * Calculates dealer's and player's cards values and determines the game's state.
	 * @return One of the 3 possible values: GAME_LOST, GAME_WIN, GAME_CONTINUE
	 */
	private int calculateHands() {
		//Calculating hands
		if (dealerHand.getBlackJackValue() == 21) {
			out.println("Dealer has a BlackJack, you lost.");
			return GAME_LOST;
		}
		else if (playerHand.getBlackJackValue() == 21 && dealerHand
				.getBlackJackValue() < 21) {
			out.println("You have a BlackJack, you win.");
			return GAME_WIN;
		}
		return GAME_CONTINUE;
	}
}
