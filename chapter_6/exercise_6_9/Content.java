import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

private class Content extends JPanel implements MouseListener {	
  private final int WIDTH_WINDOW ;         // window width
  private final int HEIGHT_WINDOW;         // window height
  private final Color RED = Color.RED;     // red color
  private final Color BLACK = Color.BLACK; // black color
  private int[] x, y;          // arrays for points' coordinates	
  private int points;          // number of total points	
  private int offset = 15;     // maximal off-set between initial and closing dots 
  private boolean drawPolygon; // true if the shape is closed
  private boolean nextClear;   // true if next click will clear the panel

  public Content(int width, int height) {
    WIDTH_WINDOW = width;
    HEIGHT_WINDOW = height;
    x = new int[100];
    y = new int[100];
    addMouseListener(this);				
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setStroke( new BasicStroke(4) );
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, WIDTH_WINDOW, HEIGHT_WINDOW ); // draw background

    if (points > 0 && ! drawPolygon) { // draw dots
      g.setColor(BLACK);
      g.fillOval(x[0], y[0], 12, 12);
      for (int i = 1; i < points; i++) {
        g.fillOval(x[i], y[i], 6, 6);
      }
    }

    if (drawPolygon) {
      g.setColor(RED);	
      g.fillPolygon(x, y, points); // draw red polygon
      g.setColor(BLACK);
      g.drawPolygon(x, y, points); // draw  black border
      drawPolygon = false;
    }		
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (nextClear) { // if there is a click after the polygon had been drawn
      x = new int[100];
      y = new int[100];
      points = 0;
      nextClear = false;
      repaint();
      return;
    }
    if (points > 0) {
      int xOffset, yOffset; // actual X and Y off-sets between
      // initial and current points
      xOffset = Math.abs(e.getX() - x[0]); 
      yOffset = Math.abs(e.getY() - y[0]);
      // if current point is close enough to the initial point - draw
      // polygon
      if (xOffset < offset && yOffset < offset) {
        drawPolygon = true;
        nextClear = true;
        repaint();
        return;
      }
    }
    x[points] = e.getX();
    y[points] = e.getY();
    points++;	
    repaint(); // redraw the panel
  }

  @Override
  public void mouseClicked(MouseEvent e) { }
  @Override
  public void mouseReleased(MouseEvent e) { }
  @Override
  public void mouseEntered(MouseEvent e) { }
  @Override
  public void mouseExited(MouseEvent e) { }	
}