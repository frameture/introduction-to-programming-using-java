import javax.swing.JFrame;

public class Exercise_8_5 extends JFrame {
  public static void main(String[] args) {
    Exercise_8_5 window = new JFrame();
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
  }
  
  public Exercise_8_5() {
    super("See The Function");
    setLocation(100, 100);
    setBackground(Color.GRAY);
    Content content = new Content();
    setContentPane(content);
    pack();
  }
}