public class Hand {
  public Card[] hand;	// array of Card objects representing a hand of cards
  
/**
* 
* @param emptySlots Parameter specifying how many empty slots should Hand 
* instance contain. May be used in several card playing games.
*/
  public Hand(int emptySlots) {
    if (emptySlots < 1)
      throw new IllegalArgumentException("Illegal number of empty slots in hand.");
    else
      hand = new Card[emptySlots];
  }
  
/**
* Adds one Card 'c' to the Hand instance on the position of 'slot'
* @param c
* @param slot - numbering starts from 0 (0 means 1st card, 1 - 2nd...))
*/
  public void addCard(Card c, int slot) {
    if (c == null) // error - 'c' Card object is empty
      throw new IllegalArgumentException("The Card object is null.");
    else if (slot >= hand.length || slot < 0) // error - slot is greater than hand
      throw new IllegalArgumentException("Illegal slot number.");
    else if(hand[slot] != null) // error - current slot is occupied
      throw new IllegalArgumentException("Current slot number is occupied");
    else 
      hand[slot] = c;
  }
  
/**
* Method sets all Hand's object array elements to <b>null</b>
*/
  public void clear() {
    for (int i = 0; i < hand.length; i++)
      hand[i] = null;
  }
  
/**
* Sets a given Card's value to null
* @param c
*/
  public void removeCard(Card c) {
    if (c == null)
      throw new IllegalStateException("A given Card object is empty.");
    else {
      for(int i = 0; i < hand.length; i++) {
        if (hand[i] == c) {
          hand[i] = null;
          return;
        }
      }
    }
  }
  
/**
* Sets a Card's value specified by a given 'cardSlot' parameter to null.
* 
* @param cPos Begins with 0
*/
  public void removeCard(int cardSlot) {
    if (cardSlot < 0 || cardSlot >= hand.length)
      throw new IllegalArgumentException("Illegal Card's slot in the Hand instance.");
    else
      hand[cardSlot] = null;
  }
  
/**
* 
* @return integer of available cards in Hand object
*/
  public int getCardsCount() {
    int counter = 0;
    for (int i = 0; i < hand.length; i++) {
      if (hand[i] != null)
        counter++;
    }
  return counter;
}

/**
* 
* @param c Card object passed to find out its slot index.
* @return the slot of current Card. Begins with 0.
* If no Card found then '-1' is returned.
*/
  public int getCardSlot(Card c) {
    if (c == null)
      throw new IllegalStateException("Empty object");
    else {
      for (int i = 0; i < hand.length; i++) {
        if (hand[i] == c)
          return i;
      }  
    }
    return -1; // no card found
  }
  
/**
* 
* @param cardSlot Index of a Card object used for finding actual Card value
* @return Card object whose slot index was specified as a parameter to the 
* method.
*/
  public Card getCard(int cardSlot) {
    if (cardSlot < 0 || cardSlot >= hand.length)
      throw new IllegalStateException("Illegal slot number");
    else
      return hand[cardSlot];
  }
}
