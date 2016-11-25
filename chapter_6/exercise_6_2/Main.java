import javax.swing.*;

public class Main extends JFrame {
  private static final int WIDTH = 1000;  // width of the window
  private static final int HEIGHT = 1000; // height of the window

  public static void main(String[] args) {
    JFrame window = new Main();
    window.setSize(WIDTH, HEIGHT);
    window.setLocation(0, 0);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

/**
* Constructor of JFrame window, which is main part of the program's GUI.
*/
  public Main() {
    super("Move Blue and Red Square");
    Content content = new Content();
    setContentPane(content);
    setLocation(0, 0);
  }
}