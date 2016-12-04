
  The sample program RandomArt.java from Subsection 6.4.1 shows a different 
  random "artwork" every four seconds. There are three types of "art", one 
  made from lines, one from circles, and one from filled squares. However, 
  the program does not save the data for the picture that is shown on the 
  screen. As a result, the picture cannot be redrawn when necessary. In fact,
  every time paintComponent() is called, a new picture is drawn.
  
  Write a new version of RandomArt.java that saves the data needed to redraw 
  its pictures. The paintComponent() method should simply use the data to draw 
  the picture. New data should be recomputed only every four seconds, in response 
  to an event from the timer that drives the program.
  
  To make this interesting, write a separate class for each of the three different 
  types of art. Also write an abstract class to serve as the common base class 
  for the three classes. Since all three types of art use a random gray background, 
  the background color can be defined in their superclass. The superclass also 
  contains a draw() method that draws the picture; this is an abstract method 
  because its implementation depends on the particular type of art that is being drawn.
 
