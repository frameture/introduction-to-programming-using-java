import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
  public Main() {
    super("Go Moku Game");
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout(20, 20));
    panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
    Content content = new Content();
    panel.add(content, BorderLayout.WEST);
    panel.add(content.getJPanel(), BorderLayout.EAST);
    panel.add(content.getJLabel(), BorderLayout.SOUTH);
    panel.setLocation(0,0);
    add(panel);
  }

  public static void main(String[] args) {	
    Main window = new Main();
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(600,200);
    window.pack();
    window.setResizable(false);	
  }
}