import java.awt.*;
import java.util.ArrayList;

/**
* Concrete class implementing abstract ShapeArt class. Represents a drawing
* made of line shapes.
*/
  class LinesArt extends ShapeArt {
    private ArrayList<Point> endPoints; // list of lines' end points coordinates

    /** 
    * Constructor of LinesArt. Creates new lists of colors, start points and
    * end points of the lines in the current ShapeArt instance.
    */
    LinesArt() {
      super(); // set background color
      this.colors = new ArrayList<>();    // create new list of Colors
      this.points = new ArrayList<>();    // create new list of start points
      this.endPoints = new ArrayList<>(); // create new list of end points

      for (int i = 0; i < 500; i++) {  // get random coordinates and size of lines
        int x1 = (int)(getWidth() * Math.random()),
            y1 = (int)(getHeight() * Math.random()),
            x2 = (int)(getWidth() * Math.random()),
            y2 = (int)(getHeight() * Math.random());
        // add color and line's points to the data set
        this.colors.add(Color.getHSBColor( (float)Math.random(), 1.0F, 1.0F));
        this.points.add(new Point(x1, y1));
        this.endPoints.add(new Point(x2, y2));
      }
    }

  /**
  * Draws all lines from current data set.
  */
  public void draw(Graphics g) {
    super.draw(g); // draw background
    int i = 0;     // iterator
    for (Color c : this.colors) {
      // set color and draw current 'i' line
      g.setColor(c); 
      g.drawLine(this.points.get(i).x, this.points.get(i).y, 
      this.endPoints.get(i).x, this.endPoints.get(i).x);
      i++;
    }
  }
}