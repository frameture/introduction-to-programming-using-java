import javax.swing.JFrame;

public class Main extends JFrame {	
	private final int WIDTH_HEIGHT = 1200;
	public Main() {	
		super("Checkerboard");
		Checkerboard board = new Checkerboard(WIDTH_HEIGHT);
		setContentPane(board);
		board.setPreferredSize(new Dimension(WIDTH_HEIGHT, WIDTH_HEIGHT));
		board.addMouseListener(board.getMouseListener());	
	}
	
	public static void main(String[] args){
		JFrame window = new Main();
		window.setLocation(0,0);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}