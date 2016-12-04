import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

    private class Content extends JPanel implements MouseListener {
      // window size
	  private final static int WIDTH = 520,
	                           HEIGHT = 520;
	  // named constants for the board, specifies what board's squares mean
	  public final static int EMPTY = 0,
	                          BLACK = 1,
	                          WHITE = 2;
	  private final int SQUARE_WIDTH = 40;
	  private boolean isBlackTurn;  // determines turns
	  private boolean isPlaying; // is the game playable
	  private boolean isWon;     // if true -- draw red line  
	  public int[][] board; // board
	  public ArrayList<Point> fiveDots;
	  private JPanel panel; // panel for the window
	  private JLabel label; // label for the window
	  private String text;
	  private Font font = new Font("SansSerif", Font.BOLD, 25);
	  private JButton newGame, // reference to the buttons
	                  resign;
	  
	  /**
	   * Starts the game and picks which player has the first move.
	   * Also changes the buttons clickability.
	   */
	  private void newGame() {
			board = new int[13][13];           // reset board 
		if (Math.random() > 0.49) { // pick first player
		  isBlackTurn = true;
		  text = "Black's turn";
		} else
		  text = "White's turn";
		isPlaying = true;    // board can be clicked
		label.setText(text); // set label's text
		// change buttons' clickability
		resign.setEnabled(true);
		newGame.setEnabled(false);
		repaint();
	  }
	  
	  /**
	   * Resets the board. Also changes the buttons clickability.
	   */
	  private void resign() {
		label.setText("Click 'New Game' button to begin the game");
		board = new int[13][13];           // reset board 
		fiveDots = new ArrayList<Point>(); // reset fiveDots
		isPlaying = false; // board cannot be clicked
		// change buttons' clickability
		resign.setEnabled(false);
		newGame.setEnabled(true);
		repaint();
	  }
	  
	  /**
	   * Initialize new JButton object.
	   * @return new JButton object
	   */
	  private JButton initButton(String title, Font font, ButtonHandler handler) {
		    JButton button = new JButton(title);
		    button.setFont(font);
		    button.setActionCommand(title);
		    button.setBackground(Color.LIGHT_GRAY);
		    button.setOpaque(true);
		    button.addActionListener(handler);
		    return button;
	  }
	  
	  /**
	   * Initializes panel and label - both will have to be returned to the JFrame
	   * class which will implement Content class. 
	   */
	  private void initElements() {
		    panel = new JPanel();
		    panel.setLayout(new GridLayout(2, 0, 50, 50));
		    panel.setOpaque(false);
		    panel.setPreferredSize(new Dimension(250, 500));

		    ButtonHandler btnHandler = new ButtonHandler();
		    newGame = initButton("New Game", font, btnHandler);
		    panel.add(newGame);
		    resign = initButton("Resign", font, btnHandler);
		    panel.add(resign);

		    label = new JLabel(String.format("%35s", text));
		    label.setFont(font);
	  }
	  
	  public JPanel getJPanel() { return panel; }
	  public JLabel getJLabel() { return label; }
	  
	  protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D)g;
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		    		RenderingHints.VALUE_ANTIALIAS_ON);
		    g2.setStroke(new BasicStroke(1));
		    draw(g);
	        if (isWon) {
	        	Point firstdot = fiveDots.get(0);
	        	Point lastDot = fiveDots.get(4);
	        	 g2.setStroke(new BasicStroke(5));
	        	g.setColor(Color.RED);
	        	g.drawLine(firstdot.x * SQUARE_WIDTH, firstdot.y * SQUARE_WIDTH, lastDot.x * SQUARE_WIDTH, lastDot.y * SQUARE_WIDTH);
	        }
		  }
	  
	  /**
	   * Constructor of the Content object.
	   */
	  public Content() {
		// initialize current JPanel
	    setPreferredSize(new Dimension(WIDTH, HEIGHT - 20));
	    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	    addMouseListener(this);	 
	    
	    initElements(); // initialize panel and label elements
	    resign();       // create first, empty board
	  }
	  
	  /**
	   * Draws the background and the board with dots on it, if exist.
	   * @param g Graphics context
	   */
	  public void draw(Graphics g) {
         drawBgAndEmptyBoard(g);
         fillBoard(g);
	  }
	  
	  /**
	   * Draws the background and the empty board.
	   * @param g Graphics context
	   */
	  private void drawBgAndEmptyBoard(Graphics g) {
		    // draw background
		    g.setColor(Color.GRAY);
		    g.fillRect(0, 0, WIDTH, HEIGHT);
            // draw board
		    g.setColor(Color.BLACK);
		    for (int i = 0; i <= 13; i++) {
		      g.drawLine(SQUARE_WIDTH * i, 0, SQUARE_WIDTH * i, HEIGHT);
		      g.drawLine(0, SQUARE_WIDTH * i, WIDTH, SQUARE_WIDTH * i);
		    }
	  }
	  
	  /**
	   * Draw the content of the board. Draw the black or white dots.
	   * @param g Graphics context
	   */
	  private void fillBoard(Graphics g) {
		    int rowCount = 0, colCount = 0;
		    for (int[] row : board) {
		      colCount = 0;
		      for (int ele : row) {
		        switch(ele) {
		          case 1:
		            g.setColor(Color.BLACK);
		            g.fillOval(colCount * SQUARE_WIDTH + 5, rowCount * SQUARE_WIDTH + 5, SQUARE_WIDTH - 10, SQUARE_WIDTH - 10);
		            break;
		          case 2:
		            g.setColor(Color.WHITE);
		            g.fillOval(colCount * SQUARE_WIDTH + 5, rowCount * SQUARE_WIDTH + 5, SQUARE_WIDTH - 10, SQUARE_WIDTH - 10);
		            break;
		          default:
		            g.setColor(Color.GRAY);
		            g.fillOval(colCount * 40 +5, rowCount*40+5, SQUARE_WIDTH - 10, SQUARE_WIDTH - 10);
		            break;
		        }
		        colCount++;
		      }
		      rowCount++;
		    }
	  }
	  
	  /**
	   * Checks whether there is a valid sequence of 5 consecutive one-color dots.
	   * @param row Row of the first dot
	   * @param col Column of the first dot
	   * @param color Color of the dot
	   */
	  private void checkDots(int row, int col, int color) {
		  if (checkDotsTwoAxis(row, col, color) == 5 || 
			  checkDotsDiagonal(row, col, color) == 5) {
			  isPlaying = false;
			  newGame.setEnabled(true);
			  resign.setEnabled(false);
			  label.setText(color + " won the game!");
			  isWon = true; // draw red line
			  repaint();
		  }
	  }
	  

	  /**
	   * Returns the number of consecutive one-color dots in the valid sequence, 
	   * either vertical or horizontal.
	   * @param row Row of the current square
	   * @param col Column of the current square
	   * @param color Which player's color
	   * @return number of dots in the sequence
	   */
	  private int checkDotsTwoAxis(int row, int col, int color) {
	    int dotsV = 1,
	    	dotsH = 1;	
	    // check horizontally
	    fiveDots = new ArrayList<>();       // reset the sequence  
	    fiveDots.add(new Point(row,  col)); // add first dot
	    // check up
	    for (int i = 1; i < 5; i++)
	      if ((row - i) >= 0 && board[row - i][col] == color) {
	        dotsH++;
	        fiveDots.add(new Point(row - i, col));
	      } else // no more consecutive dots --- break
	    	  break;
	    // check down
	    for (int i = 1; i < 5; i++)
	      if ((row + i) <= 12 && board[row + i][col] == color) {
	        dotsH++;
	        fiveDots.add(new Point(row + i, col));
	      } else // no more consecutive dots --- break
	    	break;
	    
	    if (dotsH == 5) return 5;
	    
	    // check vertically
	    fiveDots = new ArrayList<>();       // reset the sequence  
	    fiveDots.add(new Point(row,  col)); // add first dot
	    // check left
	      for (int i = 1; i < 5; i++)
	        if ((col - i) >= 0 && board[row][col - i] == color) {
	          dotsV++;
		      fiveDots.add(new Point(row, col - i));
		    } else // no more consecutive dots --- break
		      break;
	    // check right
	    for (int i = 1; i < 5; i++)
	      if ((col + i) <= 12 && board[row][col + i] == color) {
	        dotsV++;
		    fiveDots.add(new Point(row, col + i));
		  } else // no more consecutive dots --- break
		    break;
	    return dotsH > dotsV ? dotsH : dotsV;
	  }
	  
	  /**
	   * Returns the number of consecutive one-color dots in the valid sequence, 
	   * either ascending (from bottom left to the top right) or descending (from
	   * top left to the bottom right) diagonal.
	   * @param row Row of the current square
	   * @param col Column of the current square
	   * @param color Which player's color
	   * @return number of dots in the sequence
	   */
	  private int checkDotsDiagonal(int row, int col, int color){
	    int dots1 = 1,
	    	dots2 = 1;	
	    fiveDots = new ArrayList<>();       // reset the sequence  
	    fiveDots.add(new Point(row,  col)); // add first dot
	    // check first diagonal
	    // check up left
	    for(int i = 1; i<5; i++)
	      if((row-i)>=0 &&(col-i)>=0 &&  board[row-i][col-1] == color) {
	        dots1++;
	        fiveDots.add(new Point(row - i, col - i));
		  } else // no more consecutive dots --- break
		    break;
	    //check down right
	    for (int i = 1; i < 5; i++)
	      if ((row + i) <= 12 && (col + i) <= 12 &&  board[row + i][col + 1] == color) {
	        dots1++;
	        fiveDots.add(new Point(row + i, col + i));
		  } else // no more consecutive dots --- break
		    break;

	    fiveDots = new ArrayList<>();       // reset the sequence  
	    fiveDots.add(new Point(row,  col)); // add first dot
	    // check second diagonal
	    // check up right
	    for (int i = 1; i < 5; i++)
	      if ((row + i) <= 12 && (col - i) >= 0 &&  board[row + i][col - 1] == color) {
	        dots2++;
	        fiveDots.add(new Point(row + i, col - i));
	     } else // no more consecutive dots --- break
	       break;
	    // check down left
	    for (int i = 1; i < 5; i++)
	      if ((row - i) >= 0 && (col + i) <= 12 &&  board[row - i][col + 1] == color) {
	        dots2++;
	        fiveDots.add(new Point(row - i, col + i));
		  } else // no more consecutive dots --- break
		    break;
	    return dots1 > dots2 ? dots1 : dots2;
	  }
	  
	  /**
	   * Handles the buttons' clicks 
	   */
	  private class ButtonHandler implements ActionListener {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      String cmd = e.getActionCommand();
		      if (cmd.equals("New Game"))
		         newGame();
		      else if (cmd.equals("Resign")) 
		    	 resign();
		    }
		  }
	  
	  /**
	   * Handles the mousePressed event.
	   */
	  @Override
	  public void mousePressed(MouseEvent e) {
		  if (! isPlaying) // we are not playing
			  return;
	    int y = e.getX() / SQUARE_WIDTH, 
	        x = e.getY() / SQUARE_WIDTH;
	    if (board[x][y] != 0 ) // square isn't empty
	      return;
	    // change current board's square status
	    if (isBlackTurn)
	    	changeSquareStatus(x, y, 1, "White's turn");
	    else
	    	changeSquareStatus(x, y, 2, "Black's turn");
	    // another player's turn
	    isBlackTurn = !isBlackTurn;		
	  }
	  
	  /**
	   * Changes the status of the given square and checks for the winner
	   * @param row Row of the square
	   * @param col COlumn of the square
	   * @param status Status to-be of current square 
	   * @param text Text for the label
	   */
	  public void changeSquareStatus(int row, int col, int status, String text) {
	      board[row][col] = status;
	      label.setText(text);   
	      checkDots(row, col, status);
	      repaint();
	  }
	  
	  @Override
	  public void mouseClicked(MouseEvent e) { }
	  @Override
	  public void mouseReleased(MouseEvent e) { }
	  @Override
	  public void mouseEntered(MouseEvent e) { }  
	  @Override
	  public void mouseExited(MouseEvent e) { }

	}
}