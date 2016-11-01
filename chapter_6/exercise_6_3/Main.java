import javax.swing.*;

public class Main extends JFrame {
	public static void main(String[] args) {
		JFrame window = new Main();
		window.setLocation(0, 0);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Main(){
		super("Roll dice");
		Content content = new Content();
		setContentPane(content);
	}
}