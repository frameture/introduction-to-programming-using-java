import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A simple demonstration of MouseEvents.  Shapes are drawn
 * on a black background when the user clicks the panel.  If
 * the user Shift-clicks, the panel is cleared.  If the user
 * right-clicks the panel, a blue oval is drawn.  Otherwise,
 * when the user clicks, a red rectangle is drawn.  The contents of
 * the panel are not persistent.  For example, they might disappear 
 * if the panel is resized.
 * This class has a main() routine to allow it to be run as an application.
 */
public class Exercise_6_1 extends JPanel implements MouseListener, MouseMotionListener {
  private boolean drawingAllowed;

  public static void main(String[] args) {
    JFrame window = new JFrame("Simple Stamper");
    Exercise_6_1 content = new Exercise_6_1();
    window.setContentPane(content); // setting panel's content
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(0, 0);
    window.setSize(800, 600);
    window.setVisible(true);
  }

/**
* This constructor simply sets the background color of the panel to be black
* and sets the panel to listen for mouse events on itself.
*/
  public Exercise_6_1() {
    drawingAllowed = true;
    setBackground(Color.GRAY);
    // register this object as listeners to MouseEvents
    addMouseListener(this);
    addMouseMotionListener(this);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // adding border
  }

/**
*  Since this panel has been set to listen for mouse events on itself, 
*  this method will be called when the user clicks the mouse on the panel.
*  This method is part of the MouseListener interface.
*/
  public void mousePressed(MouseEvent evt) {
    if (drawingAllowed) {
    if ( evt.isShiftDown() ) {
      // The user was holding down the Shift key.  Just repaint the panel.
      // Since this class does not define a paintComponent() method, the 
      // method from the superclass, JPanel, is called.  That method simply
      // fills the panel with its background color, which is black.  The 
      // effect is to clear the panel.
      repaint();
      return;
    }
      int x = evt.getX();  // x-coordinate where user clicked.
      int y = evt.getY();  // y-coordinate where user clicked.
      Graphics g = getGraphics();  // Graphics context for drawing directly.
      // NOTE:  This is considered to be bad style!
      if ( evt.isMetaDown() ) {
        // User right-clicked at the point (x,y). Draw a blue oval centered 
        // at the point (x,y). (A black outline around the oval will make it 
        // more distinct when shapes overlap.)
        g.setColor(Color.BLUE);  // Blue interior.
        g.fillOval( x - 30, y - 15, 60, 30 );
        g.setColor(Color.BLACK); // Black outline.
        g.drawOval( x - 30, y - 15, 60, 30 );
      }
      else {
        // User left-clicked (or middle-clicked) at (x,y). 
        // Draw a red rectangle centered at (x,y).
        g.setColor(Color.RED);   // Red interior.
        g.fillRect( x - 30, y - 15, 60, 30 );
        g.setColor(Color.BLACK); // Black outline.
        g.drawRect( x - 30, y - 15, 60, 30 );
      }
      g.dispose();  // We are finished with the graphics context, so dispose of it.
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
        // The user was holding down the Shift key.  Just repaint the panel.
        // Since this class does not define a paintComponent() method, the 
        // method from the superclass, JPanel, is called.  That method simply
        // fills the panel with its background color, which is black.  The 
        // effect is to clear the panel.
        repaint();
        drawingAllowed = false;
        return;
      }
      int x = e.getX();  // x-coordinate where user clicked.
      int y = e.getY();  // y-coordinate where user clicked.

      Graphics g = getGraphics();  // Graphics context for drawing directly.
      // NOTE:  This is considered to be bad style!

      if ( e.isMetaDown() ) {
        // User right-clicked at the point (x,y). Draw a blue oval centered 
        // at the point (x,y). (A black outline around the oval will make it 
        // more distinct when shapes overlap.)
        g.setColor(Color.BLUE);  // Blue interior.
        g.fillOval( x - 30, y - 15, 60, 30 );
        g.setColor(Color.BLACK); // Black outline.
        g.drawOval( x - 30, y - 15, 60, 30 );
      }
      else {
        // User left-clicked (or middle-clicked) at (x,y). 
        // Draw a red rectangle centered at (x,y).
        g.setColor(Color.RED);   // Red interior.
        g.fillRect( x - 30, y - 15, 60, 30 );
        g.setColor(Color.BLACK); // Black outline.
        g.drawRect( x - 30, y - 15, 60, 30 );
      }
      g.dispose();  // We are finished with the graphics context, so dispose of it.
    }	
  }

  @Override
  public void mouseMoved(MouseEvent e) { }
} 