<strong> I am not satisfied with my initial version of this program, which was 
         written around half a year ago. I have to rewrite it in order to upload.
</strong>

Write a GUI Blackjack program that lets the user play a game of Blackjack, with 
the computer as the dealer. The program should draw the user's cards and the 
dealer's cards, just as was done for the graphical HighLow card game in 
Subsection 6.6.6.You can find a description of the game of Blackjack in Exercise
5.5. Add the following rule to that description: If a player takes five cards 
 without going over 21, that player wins immediately. This rule is used in some 
 casinos. For your program, it means that you only have to allow room for five 
 cards. You should assume that the panel is just wide enough to show five cards, 
 and that it is tall enough show the user's hand and the dealer's hand.
 
 Note that 
 the design of a GUI Blackjack game is very different from the design of the 
 text-oriented program that you wrote for Exercise 5.5. The user should play the 
 game by clicking on "Hit" and "Stand" buttons. There should be a "New Game" button 
 that can be used to start another game after one game ends. The "New Game" button 
 should be disabled when there is a game in progress. The "Hit" and "Stand" buttons 
 should be disabled when there is not a game in progress. The instance variable 
 gameInProgress tells whether or not a game is in progress, so you just have to 
 make sure that the buttons are properly enabled and disabled whenever this variable 
 changes value. 
 
 You have to decide what happens when each of these buttons is pressed. You don't 
 have much chance of getting this right unless you think in terms of the states 
 that the game can be in and how the state can change.
 
 There is one other thing to think about: Ideally, the program should not start 
 a new game when it is first created. The user should have a chance to set a bet 
 amount before the game starts. So, in the constructor for the drawing surface 
 class, you should not call doNewGame(). You might want to display a message such 
 as "Welcome to Blackjack" before the first game starts.
 
 
 