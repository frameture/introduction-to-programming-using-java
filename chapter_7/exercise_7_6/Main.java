import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel {
  private ShapeArt currentArt; // current instance of ShapeArt
  
  public static void main(String[] args) {
    JFrame window = new JFrame("Random Art");
    Main content = new Main();
    window.setContentPane(content);
    window.setSize(400,400);
    window.setLocation(100,100);
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    window.setVisible(true);
  }

  /**
  * The constructor creates a timer with a delay time of four seconds
  * (4000 milliseconds), and with a RepaintAction object as its
  * ActionListener.  It also starts the timer running.
  */
  public Main() {
    currentArt = new LinesArt(); // initial art
    RepaintAction action = new RepaintAction();
    Timer timer = new Timer(4000, action);
    timer.start();
  }

  /**
  * The paintComponent() method fills the panel with a random shade of
  * gray and then draws one of three types of random "art".  The type
  * of art to be drawn is chosen at random.
  */
  public void paintComponent(Graphics g) {
    currentArt.draw(g);
  }

  /**
  * A RepaintAction object calls the repaint method of this panel each
  * time its actionPerformed() method is called.  An object of this
  * type is used as an action listener for a Timer that generates an
  * ActionEvent every four seconds.  The result is that the panel is
  * redrawn every four seconds.
  */
  private class RepaintAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      int choice = (int)(Math.random() * 3);
      switch (choice) { // pick an art to be drawn
        case 0:
          currentArt = new LinesArt();
          break;
        case 1:
          currentArt = new OvalsArt();
          break;
        default:
          currentArt = new RectsArt();
          break;
        }
      repaint();  // Call the repaint() method in the JPanel class.
    }
  }
}