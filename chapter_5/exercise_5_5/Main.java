import static java.lang.System.out;
public class Main {
  private static int gamesPlayed = 0;  // counter of already played games
  private static int balance = 100;    // user's cash balance
  private static int bet;              // users' bet amount
  static final BlackJackGame GAME = new BlackJackGame();
  
  public static void main(String[] args) {
    out.printf("Welcome ot the BlackJack Game!%n%n");		
    while (true) {
      if (balance > 1) {
        out.println("Would you like to play your " + (gamesPlayed + 1) + ". BlackJack game?");
        out.println("If so, type 'yes' and confirm by clicking 'enter'.");
        if (TextIO.getlnBoolean()) {
          if (initGame())
            balance += bet;
          else
            balance -= bet;
        }
        else {
          out.printf("You have played %1s times and your final balance is %1s$.%n%n"
                + "Bye!%n", gamesPlayed, balance );
          return;
        }
      } else {
          out.println();
          out.println("You run out of balance!");
          out.println("Not possible to play another games.");
          out.printf("You have played %1s times and your final balance is %1s$.%n%n"
              + "Bye!", gamesPlayed, balance );
          return;
        }
    }
  }
  
/**
* Initializes the actual game out of BlackJackGame instance. 
* @return The result of game is returned. True - win, false - lost
*/
  private static boolean initGame() {
    gamesPlayed++;  // another game has begun	
    out.println("Let's play!");
    out.println("How much would you like to bet?");
    bet = TextIO.getlnInt();  // gets user's response
    while (bet > balance) {
      out.println("Not enough balance to bet that amount!");
      out.println("Please, type amount that can be covered with your "
          + "current balance of " + balance);
      bet = TextIO.getlnInt();
      if (bet <= balance)  // correct bet amount
        break;
    }
    return GAME.playGame();
  }
}
