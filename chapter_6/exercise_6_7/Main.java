import javax.swing.JFrame;

public class Main extends JFrame {		
  public static void main(String[] args) {
    JFrame window = new Main();
    window.setBackground(Color.BLACK);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.pack();
  }

  public Main(){
    super("Statistics Calculator");
    Content content = new Content();
    setContentPane(content);
    setLocation(0, 0);
    setPreferredSize(new Dimension(700,500));	
  }
}