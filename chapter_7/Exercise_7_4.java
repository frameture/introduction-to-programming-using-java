import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * In Exercise 6.1, you wrote a program SimpleStamperWithDrag that allows the 
 * user to place red rectangles and blue ovals in a panel by clicking and 
 * dragging the mouse. However, that program does not store any information 
 * about what has been drawn, so the panel cannot repaint itself correctly. 
 * Revise the program to use an ArrayList to store data about the contents of 
 * the panel. All drawing should be done in a paintComponent() method. 
 */
public class Exercise_7_4 extends JPanel implements MouseListener, MouseMotionListener {
  private boolean drawingAllowed;
  private ArrayList<DrawingData> data; // for storing information about drawn shapes

  public static void main(String[] args) {
    JFrame window = new JFrame("Simple Stamper");
    Exercise_7_4 content = new Exercise_7_4();
    window.setContentPane(content); // setting panel's content
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(0, 0);
    window.setSize(800, 600);
    window.setVisible(true);
  }
  
  /**
   * Nested class representing data for each drawn shape on the panel.
   */
  private class DrawingData {
    private boolean isRect; // is it an oval or rect?
    private int x, y;       // coordinates of given shape 

    public DrawingData(boolean isRect, int x, int y) {
      this.isRect = isRect;
      this.x = x;
      this.y = y;
    }
 }

/**
* This constructor simply sets the background color of the panel to be black
* and sets the panel to listen for mouse events on itself.
*/
  public Exercise_7_4() {
	data = new ArrayList<>();
    drawingAllowed = true;
    setBackground(Color.GRAY);
    // register this object as listeners to MouseEvents
    addMouseListener(this);
    addMouseMotionListener(this);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // adding border
  }
  
  /**
   * Drawing method. Drawing is done in reliance on data array holding information
   * of each drawn shape. Resizing the window of the program will not erase the panel.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (DrawingData each : data) {
      int x = each.x, y = each.y;
      if (each.isRect) {
        g.setColor(Color.RED);   // Red interior.
        g.fillRect( x - 30, y - 15, 60, 30 );
        g.setColor(Color.BLACK); // Black outline.
        g.drawRect( x - 30, y - 15, 60, 30 );
      } else {
        g.setColor(Color.BLUE);  // Blue interior.
        g.fillOval( x - 30, y - 15, 60, 30 );
        g.setColor(Color.BLACK); // Black outline.
        g.drawOval( x - 30, y - 15, 60, 30 );
      }
    }
  }

  /**
  *  Since this panel has been set to listen for mouse events on itself, 
  *  this method will be called when the user clicks the mouse on the panel.
  *  This method is part of the MouseListener interface.
  */
  public void mousePressed(MouseEvent evt) {
    if (drawingAllowed) {
      if ( evt.isShiftDown() ) { // reset the panel
        data = new ArrayList<DrawingData>();
        repaint();
        return;
      }

      int x = evt.getX();  // x-coordinate where user clicked.
      int y = evt.getY();  // y-coordinate where user clicked.

      if ( evt.isMetaDown() ) {
        // User right-clicked at the point (x,y). Draw a blue oval
        data.add(new DrawingData(false,x, y));
        repaint();
      }
      else {
        // User left-clicked (or middle-clicked) at (x,y). 
        // Draw a red rectangle centered at (x,y).
        data.add(new DrawingData(true,x, y));
        repaint();
      }
    }  
  } // end mousePressed();

  // The next four empty routines are required by the MouseListener interface.
  // They don't do anything in this class, so their definitions are empty.
  public void mouseEntered(MouseEvent evt) { }
  public void mouseExited(MouseEvent evt) { }
  public void mouseClicked(MouseEvent evt) { }
  public void mouseReleased(MouseEvent evt) { }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (drawingAllowed) {
      if ( e.isShiftDown() ) {
        data = new ArrayList<DrawingData>(); // reset data
        repaint();
        return;
      }

      int x = e.getX();  // x-coordinate where user clicked.
      int y = e.getY();  // y-coordinate where user clicked.

      if ( e.isMetaDown() ) {
        // User right-clicked at the point (x,y). Draw a blue oval
        // more distinct when shapes overlap.)
        data.add(new DrawingData(false,x, y));
        repaint();
      }
      else {
        // User left-clicked (or middle-clicked) at (x,y). 
        // Draw a red rectangle centered at (x,y).
        data.add(new DrawingData(true,x, y));
        repaint();
      }
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) { }
} 