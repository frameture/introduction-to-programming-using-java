import java.awt.*;
import java.util.ArrayList;

/**
* Concrete class implementing abstract ShapeArt class. Represents a drawing
* made of oval shapes.
*/
class OvalsArt extends ShapeArt {
  /** 
  * Constructor of OvalsArt. Creates new lists of colors and coordinates
  * of the ovals in the current ShapeArt instance.
  */
  OvalsArt() {
    super(); // set background color
    this.colors = new ArrayList<>(); // create new list of Colors
    this.points = new ArrayList<>(); // create new list of coordinates

    for (int i = 0; i < 200; i++) {
      int centerX = (int)(getWidth() * Math.random()),
          centerY = (int)(getHeight() * Math.random());
      // add colors and coordinates to the data set
      this.colors.add(Color.getHSBColor( (float)Math.random(), 1.0F, 1.0F));
      this.points.add(new Point(centerX, centerY));
    }
  }

  /**
  * Draws all ovals from current data set.
  */
  public void draw(Graphics g) {
    super.draw(g); // draw background
    int i = 0;     // iterator
    for (Point oval : this.points) {
      g.setColor(this.colors.get(i));
      g.drawOval(oval.x - 50, oval.y - 50, 100, 100);
      i++;
    }
  }
}