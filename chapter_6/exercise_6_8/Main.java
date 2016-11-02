import javax.swing.JFrame;

public class Main extends JFrame {			
	public Main() {
		super("Text Processor");
		setContentPane(new Content());
		setLocation(500, 400);
		setResizable(false);
		pack();

	}

	public static void main(String[] args) {
		Main window = new Main();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}