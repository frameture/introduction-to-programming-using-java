A polygon is a geometric figure made up of a sequence of connected line segments. 
The points where the line segments meet are called the vertices of the polygon. 
The Graphics class includes commands for drawing and filling polygons. For these 
commands, the coordinates of the vertices of the polygon are stored in arrays.

Write a program that lets the user draw polygons. As the user clicks a sequence 
of points, count them and store their x- and y-coordinates in two arrays. These 
points will be the vertices of the polygon. As the user is creating the polygon, 
you should just connect all the points with line segments. When the user clicks 
near the starting point, draw the complete polygon. Draw it with a red interior 
and a black border. Once the user has completed a polygon, the next click will 
clear the data and start a new polygon from scratch. All drawing should be done 
in the paintComponent() method.