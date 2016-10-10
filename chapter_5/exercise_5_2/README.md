Modify the current source code, StatCalc.java, to add instance methods getMax() and getMin().
The getMax() method should return the largest of all the items that have been added to the dataset,
and getMin() should return the smallest. You will need to add two new instance variables to keep 
track of the largest and smallest items that have been seen so far.

Test your new class by using it in a program to compute statistics for a set of non-zero numbers 
entered by the user.

Read numbers from the user and add them to the dataset. Use 0 as a sentinel value 
(that is, stop reading numbers when the user enters 0). After all the user's non-zero numbers have been entered,
print out each of the six statistics that are available from calc.
