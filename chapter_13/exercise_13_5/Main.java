import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class Main extends JFrame implements WindowListener {
  private Content content;

  public Main() {
    super("Phone Directory");
    content = new Content();
    add(content, BorderLayout.CENTER);
    add(content.getButtonsPanel(), BorderLayout.NORTH);
    addWindowListener(this);
  }

  public static void main(String[] args) {
    JFrame window = new Main();
    window.setLocation(800, 400);
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    window.setVisible(true);
    window.pack();
  }

  @Override
  public void windowClosing(WindowEvent e) {
    content.windowClosing();
  }

  @Override
  public void windowClosed(WindowEvent e) {
    content.windowClosed();
  }

  public void windowOpened(WindowEvent e) { }
  public void windowIconified(WindowEvent e) { }
  public void windowDeiconified(WindowEvent e) { }
  public void windowActivated(WindowEvent e) { }
  public void windowDeactivated(WindowEvent e) { }
}
