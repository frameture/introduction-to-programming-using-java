import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Content class is a subclass of JPanel, and holds all content inside.
 */
public class Content extends JPanel implements MouseListener, MouseMotionListener {
  private final int SQUARE_WIDTH = 100; // square's size
  private Square blue, red;			  // two Square objects
  boolean dragBlue, dragRed;			  // state variables
  // saving cursor off-set to the square's coordinates
  int offsetX, offsetY;

/**
* Constructor, creates main content of the program.
*/
  public Content() {
    red = new Square(0, 0, SQUARE_WIDTH);
    blue = new Square(SQUARE_WIDTH, 0, SQUARE_WIDTH);

    setBackground(Color.WHITE);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    addMouseListener(this);
    addMouseMotionListener(this); 
  }

// overriding the drawing method
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    red.draw(g, Color.RED);
    blue.draw(g, Color.BLUE);
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    int x = e.getX(); // x coordinate of the occurred event
    int y = e.getY(); // y coordinate of the occurred event	

    if (dragRed) {	// move red square	 	
      red.setX(x - offsetX);
      red.setY(y - offsetY);
    }		
    else if (dragBlue) { // move blue square within boundaries
      blue.setX(x - offsetX);
      blue.setY(y - offsetY);
	  
      // horizontal boundaries
      if (blue.getX() < 0)
        blue.setX(0);
      else if (blue.getX() > getWidth() - SQUARE_WIDTH)
        blue.setX(getWidth() - SQUARE_WIDTH);

      // vertical boundaries
      if (blue.getY() < 0)
        blue.setY(0);
      if (blue.getY() > getHeight() - SQUARE_WIDTH)
        blue.setY(getHeight() - SQUARE_WIDTH);
    }
    repaint(); // redraw the panel
  }

  @Override
  public void mousePressed(MouseEvent e) {
    int x, y; 		  // occurred event's coordinates
    int redX, redY;   // red square's coordinates 
    int blueX, blueY; // blue square's coordinates
    x = e.getX();  
    y = e.getY();
    blueX = blue.getX();
    blueY = blue.getY();
    redX = red.getX();
    redY = red.getY();

    // is red square being pressed?
    if (x >= redX && x <= (redX + SQUARE_WIDTH) && 
        y >= redY && y <= (redY + SQUARE_WIDTH)) {				
      offsetX = x - red.x;  // save cursor's X off-set
      offsetY = y - red.y;	// save cursor's Y off-set
      dragRed = true; 		// red square is pressed
    }

    // is blue square being pressed?
    else if (x >= blueX && x <= (blueX + SQUARE_WIDTH) && 
             y >= blueY && y <= (blueY + SQUARE_WIDTH)) {
      offsetX = x - blue.x; // save cursor's X off-set
      offsetY = y - blue.y; // save cursor's Y off-set
      dragBlue = true;	  // blue square is pressed
    }	
  }
  
  @Override
  public void mouseReleased(MouseEvent e) {
    if (dragBlue)
      dragBlue = false;
    if (dragRed)
    dragRed = false;	
  }

  @Override
  public void mouseMoved(MouseEvent e) { }
  @Override
  public void mouseClicked(MouseEvent e) { }
  @Override
  public void mouseEntered(MouseEvent e) { }
  @Override
  public void mouseExited(MouseEvent e) { }
}