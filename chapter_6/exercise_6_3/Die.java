import java.awt.Color;
import java.awt.Graphics;

public class Die {	
  private int value;                     // Die's value
  private final int X, Y;                // coordinates where die should be drawn 
  public final int WIDTH;                // width (in pixels)
  private final double DOT_WIDTH = 0.25; // dot's width
  private final double OFFSET = 0.05;    // off-set
  private final int OFFSET_2 = 19;       // second off-set

/**
* Constructor of the Die instance
*/
  public Die(int x, int y, int width) {
    X = x;
    Y = y;
    WIDTH = width;
    roll();
  }

/**
* Method to roll the die.
*/
  public void roll() { value = (int)(Math.random() * 6) + 1; }

/**
* getter method for value variable.
* @return the value of value
*/
  public int getValue() { return value; }

/**
* @return Die value as String
*/
  public String valueToString() { return "" + value; }

  @Override
  public String toString() { return "The number on the die is " + value + "."; }

/**
* Drawing method. The die is drawn according to the current die's value.
* Each value has its own pattern.
* @param g drawing Graphics context 
*/
  public void draw(Graphics g) {	
    g.setColor(Color.WHITE);
    g.fillRect(X, Y, WIDTH, WIDTH); // draw white background
    g.setColor(Color.BLACK);
    g.drawRect(X, Y, WIDTH, WIDTH); // draw black border
    drawDie(g);						// draw die's dots
  }

/**
* Private method used in draw() method. Draws die's dots depending on 
* current 'value' - number of dots.
* @param g
*/
  private void drawDie(Graphics g) {	
    int dot = (int) (WIDTH * DOT_WIDTH);     // dot's width
    int offset = (int) (WIDTH * OFFSET); 	 // off-set
    int offset2 = (int) (offset * OFFSET_2); // second off-set
    g.setColor(Color.BLACK);

    switch (value) {
      case 1:
        g.fillOval(X + WIDTH / 2 - dot / 2, Y + WIDTH / 2 - dot / 2, dot, 
            dot);
        break;
      case 2:
        g.fillOval(X + offset, Y + offset, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset2 - dot, dot, dot);
        break;
      case 3:
        g.fillOval(X + WIDTH / 2 - dot / 2, Y + WIDTH / 2 - dot / 2, dot,
            dot);
        g.fillOval(X + offset, Y + offset, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset2 - dot, dot, dot);
        break;
      case 4:
        g.fillOval(X + offset, Y + offset, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset2 - dot, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset, dot, dot);
        g.fillOval(X + offset, Y + offset2 - dot, dot, dot);
        break;
      case 5:
        g.fillOval(X + offset, Y +offset, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset2 - dot, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset, dot, dot);
        g.fillOval(X + offset, Y + offset2 - dot, dot, dot);
        g.fillOval(X + WIDTH / 2 - dot / 2, Y + WIDTH / 2 - dot / 2, dot,
            dot);
        break;
      default:
        g.fillOval(X + offset, Y + offset, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset2 - dot, dot, dot);
        g.fillOval(X + offset2 - dot, Y + offset, dot, dot);
        g.fillOval(X + offset, Y + offset2 - dot, dot, dot);
        g.fillOval(X + offset, Y + WIDTH / 2 - dot / 2, dot, dot);
        g.fillOval(X + offset2 - dot, Y + WIDTH / 2 - dot / 2, dot, dot);
        break;
    }
  }	
}