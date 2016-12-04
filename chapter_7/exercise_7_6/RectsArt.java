import java.awt.*;
import java.util.ArrayList;

/**
* Concrete class implementing abstract ShapeArt class. Represents a drawing
* made of rectangular shapes.
*/
class RectsArt extends ShapeArt {
  /** 
   * Constructor of RectsArt. Creates new lists of colors and coordinates
   * of the rectangulars in the current ShapeArt instance.
   */
  RectsArt() {
    super(); // set background color
    this.colors = new ArrayList<>(); // create new list of Colors
    this.points = new ArrayList<>(); // create new list of coordinates

    for (int i = 0; i < 50; i++) {
      int x = (int)(getWidth() * Math.random()),
          y = (int)(getHeight() * Math.random());
      // add color and coordinates to the data set
      this.colors.add(new Color( (int)(256 * Math.random()), 
                    (int)(256 * Math.random()), (int)(256 * Math.random()) ));
      this.points.add(new Point(x, y));
    }
  }

  /**
  * Draws all ovals from current data set.
  */
  public void draw(Graphics g) {
    super.draw(g); // draw background
    int i = 0;     // iterator
    for (Point rect: this.points) {
      g.setColor(this.colors.get(i));
      int size = (int) (Math.random() * 300);
      g.fill3DRect(rect.x - size / 2, rect.y - size / 2, size, size, true);
      i++;
    }
  }
}