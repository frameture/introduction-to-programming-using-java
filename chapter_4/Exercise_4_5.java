
// import Mosaic; program is dependent on 'Mosaic' and 'MosaicPanel' classes from the book - chapter 4; 

/**
 * This program opens a window full of randomly colored squares.  A "disturbance"
 * moves randomly around the window changing the color of each
 * square that it visits.  The program runs until the user closes the window.
 */
public class Exercise_4_5 {
    final static int ROWS = 100;        // Number of rows in mosaic.
    final static int COLUMNS = 80;     // Number of columns in mosaic.
    final static int SQUARE_SIZE = 20; // Size of each square in mosaic.
    static int currentRow;    // Row currently containing the disturbance.
    static int currentColumn; // Column currently containing the disturbance.
    /**
     * The main method creates the window, fills it with random colors
     * and then moves the disturbance in a random walk around the window
     * as long as the window is open.
     */
    public static void main(String[] args) {
        Mosaic.open( ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE );
        Mosaic.setUse3DEffect(false);
        currentRow = ROWS / 2;   // start at center of window
        currentColumn = COLUMNS / 2;
        // fillWithRandomColors(); // uncomment in order to start with colored window;
        while (Mosaic.isOpen()) {
        	addGreen(currentRow, currentColumn);
            randomMove();
            Mosaic.delay(1);
        }
    }  // end main
    /**
     * 
     * @param row Row of the square to add green
     * @param col Column of the square to add green
     */
    private static void addGreen(int row, int col) {
    	int green = Mosaic.getGreen(row, col);
    	if (green < 255)	// increasing green color;
    		green += 25;
    	else if(green > 255)	// reaching max color value;
    		green = 255;
    	Mosaic.setColor(row, col, 0, green, 0);
	}
	/**
     * Fills the window with randomly colored squares.
     * Precondition:   The mosaic window is open.
     * Postcondition:  Each square has been set to a random color. 
     */
    static void fillWithRandomColors() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                changeToRandomColor(row, column);  
            }
        }
    }  // end fillWithRandomColors
    /**
     * Changes square's color to a new randomly selected color.
     * Precondition:   The specified rowNum and colNum are in the valid range
     *                 of row and column numbers.
     * Postcondition:  The square in the specified row and column has
     *                 been set to a random color.
     * @param rowNum the row number of the square, counting rows down
     *      from 0 at the top
     * @param colNum the column number of the square, counting columns over
     *      from 0 at the left
     */
    static void changeToRandomColor(int rowNum, int colNum) {
        int red = (int)(256 * Math.random());    // Choose random levels in range
        int green = (int)(256 * Math.random());  //     0 to 255 for red, green, 
        int blue = (int)(256 * Math.random());   //     and blue color components.
        Mosaic.setColor(rowNum, colNum, red, green, blue);  
    }  // end changeToRandomColor
    /**
     * Moves the disturbance in a random direction.
     * Precondition:   The global variables currentRow and currentColumn
     *                 are within the legal range of row and column numbers.
     * Postcondition:  currentRow or currentColumn is changed to one of the
     *                 neighboring positions in the grid -- up, down, left, or
     *                 right from the current position.  If this moves the
     *                 position outside of the grid, then it is moved to the
     *                 opposite edge of the grid.
     */   
    static void randomMove() {
        int directionNum;	// Randomly set to 0, 1, 2, or 3 to choose direction.
        directionNum = (int)(4 * Math.random());
        switch (directionNum) {
        case 0:  // move up 
            currentRow--;
            if (currentRow < 0)
                currentRow = ROWS - 1;	// entering the opposite border
            break;
        case 1:  // move right
            currentColumn++;
            if (currentColumn >= COLUMNS)
                currentColumn = 0;
            break; 
        case 2:  // move down
            currentRow ++;
            if (currentRow >= ROWS)
                currentRow = 0;
            break;
        case 3:  // move left  
            currentColumn--;
            if (currentColumn < 0)
                currentColumn = COLUMNS - 1;
            break; 
        }
    }  // end randomMove
} // end class Exercise_4_5
