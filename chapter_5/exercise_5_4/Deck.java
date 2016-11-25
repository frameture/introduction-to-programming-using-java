/**
 * 
 * Object of Deck class represents an idea of deck of cards used in  card playing
 * games. Deck can shuffle its cards by calling shuffle() method; card can be dealt
 * by calling dealCard() method. Deck keeps counting the cards that are available for
 * dealing. 
 *
 */
public class Deck {
  public Card[] deck;	 // array of cards representing a deck
  private int usedCards; // counter of already used cards
  
/**
* Constructor of Deck object. Cards are not shuffled during construction
* of the Deck object.
* @param includeJokers boolean variable denoting if current Deck object 
* should include Jokers.
*/
  public Deck(boolean includeJokers){	
    usedCards = 0;      // new Deck object has none used card
    if (!includeJokers) // checking if current Deck object should contain Jokers
      deck = new Card[52];
    else
      deck = new Card[54];
    int theValue;        // 'value' of newly created Card object
    int theSuit;         // 'suit' of newly created Card object
    int cardCounter = 0; // counter of newly created Card objects
    for ( int i = 0 ; i < 4 ; i++) {
      theSuit = i;
      for (int j = 1 ; j < 14 ; j++) {
        theValue = j;
        deck[cardCounter] = new Card(theValue, theSuit);
        cardCounter++;
      }
    }		
    if (includeJokers) { // add two Jokers
      deck[52] = new Card(1, 4);
      deck[53] = new Card(2, 4);
    }
  }
  
/**
* Method shuffles current Deck object so that all cards are mixed.
* Process of shuffling takes cards one by one and exchanges it with
* other randomly chosen card.
*/
  public void shuffle() {
    for (int i = 0 ; i < deck.length ; i++) {
      int rand = (int)(deck.length * Math.random());
      Card temp = deck[i];
      deck[i] = deck[rand];
      deck[rand] = temp;
    }
    usedCards = 0; // deck is shuffled - there is no used card
  }
  
/**
* 
* @return the number of available cards in current Deck object.
*/
  public int cardsLeft() {
    return deck.length - usedCards;
  }
  
/**
* 
* @return Function returns the last available card form the deck.
*/
  public Card dealCard() {
    if (cardsLeft() == 0)
      throw new IllegalStateException("No cards left.");
    usedCards++;
    return deck[cardsLeft() - 1];			
  }
}
