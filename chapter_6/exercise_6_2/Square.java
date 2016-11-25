import java.awt.Graphics;
import java.awt.Color;

public class Square {	
  private int x,y; 		 // location variables
  private final int WIDTH; // square's size

/**
* Constructor of Square object.
* @param x Horizontal coordinate - initial location
* @param y Vertical coordinate - initial location
* @param width Size of the square that will be used for drawing (pixels)
*/
  public Square(int x, int y, int width) {
    setX(x);
    setY(y);
    WIDTH = width;
  }

/**
* Method responsible for drawing current Square object. Size of the
* current Square was specified during object construction.
* @param g Graphics object responsible for drawing in current panel
* @param c Color in which the square should be painted
*/
  public void draw(Graphics g, Color c) {
    g.setColor(c);
    g.fillRect(x, y, WIDTH, WIDTH);
  }

/**
* Setter method of X variable
* @param x
*/
  public void setX(int x) {
    this.x = x;
  }

/**
* Setter method of Y variable
* @param y
*/
  public void setY(int y) {
    this.y = y;
  }

/**
* @return X coordinate of current square
*/
  public int getX() {
    return x;
  }

/**
* @return Y coordinate of current square
*/
  public int getY() {
    return y;
  }
}