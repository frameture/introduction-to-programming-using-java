import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class defining structure and behavior for concrete classes 
 * implementing it. Represents a piece of drawing. Instance variables hold 
 * background color, colors and coordinates of each shape.
 */
abstract class ShapeArt {
  protected Color bgColor; // background color
  protected ArrayList<Color> colors; // shapes' colors
  protected ArrayList<Point> points; // shapes' coordinates

  /** 
  * Constructor sets background color to be a random hue of gray. 
  */
  ShapeArt() { 
    int x = (int)(256 * Math.random());
    bgColor = new Color( x, x, x );
  }

  /**
  * Draws only background. Implementing classes will override this method.
  * @param g Graphics context
  */
  public void draw(Graphics g) {
    g.setColor(bgColor);
    g.fillRect(0, 0, getWidth(), getHeight()); // get size of the panel
  }
}