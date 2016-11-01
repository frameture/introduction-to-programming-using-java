import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Checkerboard class draw the checkerboard and manages the selecting and
 * unselecting of single squares.
 */
public class Checkerboard extends JPanel {
	private boolean[][] squares;  // array of booleans determining if certain
								  // square is selected (true) or not (false)
	private final int WIDTH; 	  // width of a single square
	
	public Checkerboard(int windowWidth) {
		WIDTH = windowWidth / 8;	 // checkerboard width divided by 8 
		squares = new boolean[9][9];
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        drawBoard(g);

        // check which square is selected and mark it by a yellow border
        for (int i = 1; i < 9; i++) {
        	for (int j = 1; j < 9; j++) {
        		if (squares[i][j] == true) {
        			g.setColor(Color.YELLOW);
        			int x = i * WIDTH - WIDTH; // x coordinate for current rect
        			int y = j * WIDTH - WIDTH; // y coordinate for current rect
        			g.drawRect(x, y, WIDTH, WIDTH);
        			g.drawRect(x + 1, y + 1, WIDTH - 2, WIDTH - 2);
        			g.drawRect(x + 2, y + 2, WIDTH - 4 , WIDTH - 4);
        			g.drawRect(x + 3, y + 3, WIDTH - 6, WIDTH - 6);
        			g.drawRect(x + 4, y + 4, WIDTH - 8, WIDTH - 8);
        		}
        	}
        }
	}
	
	private void drawBoard(Graphics g) { 
        for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
        			g.setColor(Color.BLACK);
        			g.fillRect(i * WIDTH, j * WIDTH, WIDTH, WIDTH);
        		}
        		else {
        			g.setColor(Color.RED);
        			g.fillRect(i * WIDTH, j * WIDTH, WIDTH, WIDTH);
        		}
        	}
	}

	private MouseListener getMouseListener() {
		MouseAdapter listener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);	
				int squareX, squareY; // horizontal X and  vertical Y squares 
				squareX = (e.getX() + WIDTH) / WIDTH; // eg. (235 + 100) / 100
													  // = 335 / 100 = 3
				squareY = (e.getY() + WIDTH) / WIDTH;
				
				try {	
					if (squares[squareX][squareY] == false)
						squares[squareX][squareY] = true;
					else squares[squareX][squareY] = false;
				} catch (ArrayIndexOutOfBoundsException ex) {
					System.out.println(ex);
					System.out.println("squareX = " + squareX + ", squareY = " + squareY);
				};
				repaint();
			}
		};
		return listener;
	}
}