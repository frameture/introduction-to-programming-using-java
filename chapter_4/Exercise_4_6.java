
// import Mosaic; program dependent on 'Mosaic' and 'MosaicPanel' classes form the book
/**
 * This program opens a window full of randomly colored squares. One random square
 * is picked and its color copied then one of its neighboring square is selected and its
 * color changed to the color of previously selected square. The program runs until
 * the user closes the window.
 */
public class Exercise_4_6 {
  final static int ROWS = 20;        // Number of rows in mosaic.
  final static int COLUMNS = 30;     // Number of columns in mosaic.
  final static int SQUARE_SIZE = 20; // Size of each square in mosaic.
  static int currentRow = 0;         // Row currently containing the disturbance.
  static int currentColumn = 0;      // Column currently containing disturbance.
  
/**
* The main program creates the window, fills it with random colors,
* and then starts to select one random square and give its color to one of
* its neighbors as long as the window is open.
*/
  public static void main(String[] args) {
    Mosaic.open( ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE );
    fillWithRandomColors();
    while (Mosaic.isOpen()) {
      pickOne();
      copyColor();
      Mosaic.delay(0);	// increase the parameter value to let the squares to 
	                	// live longer
    }  
  }  // end main
  
/**
* Picks one random square from the mosaic.
*/
  private static void pickOne() {
    currentRow = (int)(20 * Math.random());
    currentColumn = (int)(30 * Math.random());	
  }
  
/**
* Copies color of the current square and changes the color of one
* of its neighbors.
*/
  private static void copyColor() {
    int blue, green, red; 	// colors; 
    blue = Mosaic.getBlue(currentRow, currentColumn);
    green = Mosaic.getGreen(currentRow, currentColumn);
    red = Mosaic.getRed(currentRow, currentColumn);
    changeNeighborColor(blue, green, red);
  }
  
/**
* Picks on of the neighboring squares and changes its color.
* @param blue Value of blue component
* @param green Value of green component
* @param red Value of red component
*/
  private static void changeNeighborColor(int blue, int green, int red) {
    int directionNum,	// Randomly set to 0, 1, 2, or 3 to choose direction.
    neighborRow = currentRow,	// row of neighboring square; 
    neighborCol = currentColumn;	// column of neighboring square;
    directionNum = (int)(4 * Math.random());
    switch (directionNum) {
      case 0:  // pick upper neighbor
        if (neighborRow < 0)
          neighborRow = ROWS - 1;
        break;
      case 1:  // pick right neighbor
        neighborCol++;
        if (neighborCol >= COLUMNS)
          neighborCol = 0;
        break; 
      case 2:  // pick lower neighbor
        neighborRow ++;
        if (neighborRow >= ROWS)
          neighborRow = 0;
        break;
      case 3:  // pick left neighbor  
        neighborCol--;
        if (neighborCol < 0)
          neighborCol = COLUMNS - 1;
        break; 
    }
    Mosaic.setColor(neighborRow, neighborCol, red, green, blue);
  } // end changeNeighborColor
  
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
* Changes one square to a new randomly selected color.
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
* Move the disturbance.
* Precondition:   The global variables currentRow and currentColumn
*                 are within the legal range of row and column numbers.
* Postcondition:  currentRow or currentColumn is changed to one of the
*                 neighboring positions in the grid -- up, down, left, or
*                 right from the current position.  If this moves the
*                 position outside of the grid, then it is moved to the
*                 opposite edge of the grid.
*/  
  static void randomMove() {
    int directionNum; // Randomly set to 0, 1, 2, or 3 to choose direction.
    directionNum = (int)(4 * Math.random());
    switch (directionNum) {
      case 0:  // move up 
        currentRow--;
        if (currentRow < 0)
          currentRow = ROWS - 1;
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
} // end class Exercise_4_6
