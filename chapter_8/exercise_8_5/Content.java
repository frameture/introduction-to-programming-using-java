import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
  
private class Content extends JPanel implements ActionListener {
  private final int WIDTH = 1300;      // JPanel width
  private final int HEIGHT = 700;      // JPanel height
  private final int LABEL_HEIGHT = 50; // JLabel height 
  Expr exp;         // expression object  
  JTextField textF; // input box
  JLabel message;   // message label

  public Content() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    add(initJComponents(this), BorderLayout.SOUTH); // add JComponents
    pack();
  }

  /**
   * Initializes and sets the JComponents needed for the program.
   */
  private JPanel initJComponents(ActionListener listener) {
    JPanel container = new JPanel(); // container for the other JComponents
    container.setPreferredSize(new Dimension(WIDTH, 50));
    container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    container.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    Font font = new Font("SansSerf", Font.BOLD, 24);
    
    // initialize the JLabel and JTextField
    message = new JLabel("Enter the function you want to see, e.g. '3*x+1'   ");
    message.setFont(font);
    textF = new JTextField(20);
    textF.setFont(font);
    textF.addActionListener(listener);      // set listener for 'enter' click
    container.add(textF);                   // add the input box
    container.add(message,FlowLayout.LEFT); // add the label
    return container; // return the reference to the container
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLUE);
    Graphics2D g2 = (Graphics2D)g;
    g2.setStroke(new BasicStroke(2));
    // turn on antialiasing
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                      RenderingHints.VALUE_ANTIALIAS_ON);

    int yCenter = (HEIGHT - LABEL_HEIGHT) / 2; // Y-axis center point in the JPanel  
    int xCenter = WIDTH / 2;                   // X-axis center point
    int border = 25;                           // offset the axes from the JPanel borders
    int axisLength = HEIGHT - LABEL_HEIGHT - 2 * border; // length of each axis
    int unit = axisLength / 11; // represents one unit of the axis -- scale: -5 <= x <= 5
    
    drawAxes(g, yCenter, xCenter, axisLength, border, unit); // draw an empty graph
    
    if (exp != null) {
      //draw function's points
      g.setColor(Color.BLACK);
      for (int i = -270; i <= 270; i += 2) {
        int firstY = (int) - exp.value(i);
        int secondY = (int) - exp.value(i + 1);
        g.drawLine(xCenter + i, yCenter + firstY, xCenter + i + 1, yCenter + secondY);
      }
    }
  }

  /**
   * Draws an empty graph.
   * @param g Graphics context
   */
  private void drawAxes(Graphics g, int yCenter, int xCenter, int axis, int border, int unit) {
    int xMargin = (WIDTH - axis) / 2; // offset the X-axis from the edge
    int fontOffset = 10;  // offset the labels from the axes

    /* draw X-axis arrow */
    int xEnd = WIDTH - xMargin; // end of the Y-axis  
    g.drawLine(xMargin, yCenter, xEnd, yCenter); // draw main line
    // draw arrow's wings 
    g.drawLine(xEnd - border / 5, yCenter - border / 5, xEnd, yCenter);
    g.drawLine(xEnd - border / 5, yCenter + border / 5, xEnd, yCenter);
    // draw divisors
    for (int i = -5; i < 6; i++) {
      if (i != 0) {
        g.drawLine(xCenter + i * unit, yCenter - fontOffset / 2, xCenter + i * unit,
            yCenter + fontOffset / 2);
        g.drawString("" + i * unit, xCenter + i * unit - 3, yCenter - fontOffset);
      }
    }
    
    /* draw Y-axis arrow */
    int yEnd = HEIGHT - LABEL_HEIGHT - border;
    g.drawLine(xCenter, border, xCenter, yEnd);
    // draw arrow's wings
    int arrowStart = border + border / 5;
    g.drawLine(xCenter + border / 5, arrowStart, xCenter, border);
    g.drawLine(xCenter - border / 5, arrowStart, xCenter, border);
    // draw divisors
    for (int i = -5; i < 6; i++) {
      if (i != 0) {
        g.drawLine(xCenter - fontOffset / 2, yCenter - i * unit, xCenter 
            + fontOffset / 2, yCenter - i * unit);
        g.drawString("" + i * unit, xCenter + 2 * fontOffset / 2, yCenter - i * unit);
      }
    }
     //draw x / y labels
    g.drawString("x", xEnd - fontOffset, yCenter - fontOffset);
    g.drawString("y", xCenter + fontOffset, border + fontOffset);
  }

  @Override
  /**
   * Handles the event of submitting the expression.
   */
  public void actionPerformed(ActionEvent e) {
    String input = textF.getText().toLowerCase().trim();
    textF.setText("");
    message.setText("The graph of the " + input + " function   ");
    
    try {
      exp = new Expr(input);
    }
    catch (IllegalArgumentException evt) {
      message.setText("Function expression has illegal input.");
      input = "";
      exp = null;  
    }
    repaint(); // draw the graph    
  }
}