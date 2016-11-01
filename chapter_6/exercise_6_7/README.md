Exercise 5.2 involved a class, StatCalc.java, that could compute some statistics 
of a set of numbers. Write a GUI program that uses the StatCalc class to compute 
and display statistics of numbers entered by the user. The panel will have an 
instance variable of type StatCalc that does the computations. The panel should 
include a JTextField where the user enters a number. It should have four labels 
that display four statistics for the numbers that have been entered: the number 
of numbers, the sum, the mean, and the standard deviation. Every time the user 
enters a new number, the statistics displayed on the labels should change. The 
user enters a number by typing it into the JTextField and pressing return. There 
should be a "Clear" button that clears out all the data. This means creating
a new StatCalc object and resetting the displays on the labels. My panel also 
has an "Enter" button that does the same thing as pressing the return key in 
the JTextField. (Recall that a JTextField generates an ActionEvent when the user
presses return, so your panel should register itself to listen for ActionEvents 
from the JTextField as well as the buttons.) 