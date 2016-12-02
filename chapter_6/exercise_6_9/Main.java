import javax.swing.JFrame;

public class Main extends JFrame {
  private final int WIDTH = 800;  // window width
  private final int HEIGHT = 800; // window height

  public Main(){
    super("Draw Polygon");
    setContentPane(new Content(WIDTH, HEIGHT));
    setLocation(500, 700);
    setSize(WIDTH, HEIGHT);
    setResizable(false);	
  }

  public static void main(String[] args) {
    JFrame window = new Main();
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}