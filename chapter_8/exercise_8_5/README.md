
 This exercise uses the class Expr, which was described in Exercise 8.4 and which 
 is defined in the source code file <a href="http://math.hws.edu/javanotes/source/chapter8/Expr.java">Expr.java</a>.
 
 For this exercise, you should write a GUI program that can graph a function, f(x), 
 whose definition is entered by the user. The program should have a text-input 
 box where the user can enter an expression involving the variable x, such as x^2
 or sin(x-3)/x. This expression is the definition of the function. When the user 
 presses return in the text input box, the program should use the contents of 
 the text input box to construct an object of type Expr. If an error is found in 
 the definition, then the program should display an error message. Otherwise, it 
 should display a graph of the function. (Recall: A JTextField generates an 
 ActionEvent when the user presses return.)

 The program will need a JPanel for displaying the graph. To keep things simple, 
 this panel should represent a fixed region in the xy-plane, defined by 
 -5 <= x <= 5 and -5 <= y <= 5. To draw the graph, compute a large number of 
 points and connect them with line segments. (This method does not handle 
 discontinuous functions properly; doing so is very hard, so you shouldn't try 
 to do it for this exercise.) My program divides the interval -5 <= x <= 5 into 
 300 subintervals and uses the 301 endpoints of these subintervals for drawing 
 the graph. 
 
 Note that the function might be undefined at one of these x-values. In that case, 
 you have to skip that point.

 A point on the graph has the form (x,y) where y is obtained by evaluating the 
 user's expression at the given value of x. You will have to convert these real 
 numbers to the integer coordinates of the corresponding pixel on the canvas. 
 The formulas for the conversion are:

 a  =  (int)( (x + 5)/10 * width );
 b  =  (int)( (5 - y)/10 * height );

 where a and b are the horizontal and vertical coordinates of the pixel, and 
 width and height are the width and height of the panel.