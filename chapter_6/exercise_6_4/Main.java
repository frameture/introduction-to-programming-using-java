import javax.swing.JFrame;

public class Main extends JFrame { 
	public static void main(String[] args) {
		JFrame window = new Main();
		window.setLocation(1000, 500);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Main(){
		super("Roll dice");
		Content content = new Content();
		JPanel panel = content.getPanel();
		panel.add(content, BorderLayout.CENTER);
		setContentPane(panel);
	}