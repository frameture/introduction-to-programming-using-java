  
      /*
	 * Write an animation subroutine that demonstrates both cyclic and oscillating motions 
	 * at various speeds. For cyclic motion, you can use a square that moves across 
	 * the drawing area, then jumps back to the start, and then repeats the same 
	 * motion over and over. For oscillating motion, you can do something similar, 
	 * but the square should move back and forth between the two edges of the 
	 * drawing area
	 */
  public class Exercise_3_9 {
	
    public void drawFrame(Graphics g, int frameNumber, int width, int height) {
    	  
    	int squareSize = 100;	// size of the square;
    	int heightForCycl = height + squareSize;	// distance to cover in cycle mode;
    	int heightForOsci = height - squareSize;	// distance to cover in oscillating mode;
        int distancePerFrame;	// distance that each square will cover per each frame; 
        
        // 3 cyclic squares
        // black square;
        distancePerFrame = frameNumber % heightForCycl; 
        g.fillRect(0, distancePerFrame - squareSize, squareSize, squareSize); // drawing a square;
        g.drawLine(squareSize, 0, squareSize, height);	// drawing a separator;
          
        // blue square;
        distancePerFrame = (frameNumber % (heightForCycl * 2)) / 2;
        g.setColor(Color.BLUE);
        g.fillRect(squareSize, distancePerFrame - squareSize, squareSize, squareSize);
        g.drawLine( 2 * squareSize, 0, 2 * squareSize, height);
        
        // red square;
        distancePerFrame = (frameNumber % (heightForCycl * 4)) / 4;
        g.setColor(Color.RED);
        g.fillRect(2 * squareSize, distancePerFrame - squareSize, squareSize, squareSize);
        g.drawLine(3 * squareSize, 0, 3 * squareSize, height);
         
        // 3 oscillating squares 
        // green square;
        distancePerFrame = frameNumber % (2 * heightForOsci);
        if (distancePerFrame > heightForOsci)
        	distancePerFrame = ( 2 * heightForOsci) - distancePerFrame;
        
        g.setColor(Color.GREEN);
        g.fillRect(3 * squareSize, distancePerFrame, squareSize, squareSize);
        g.drawLine(4 * squareSize, 0, 4 * squareSize, height);
          
        // pink square;
        distancePerFrame = frameNumber % (4 * heightForOsci);          
        if (distancePerFrame > 2 * heightForOsci)	// determining oscillator direction;
        	distancePerFrame = (4 * heightForOsci) - distancePerFrame;
        
        g.setColor(Color.PINK);
        g.fillRect(4 * squareSize, distancePerFrame / 2, squareSize, squareSize);
        g.drawLine(5 * squareSize, 0, 5 * squareSize, height);
          
        // yellow square  
        distancePerFrame = frameNumber % (8 * heightForOsci);          
        if (distancePerFrame > 4 * heightForOsci)
        	distancePerFrame = (8 * heightForOsci) - distancePerFrame;
          
        g.setColor(Color.YELLOW);
        g.fillRect(5 * squareSize, distancePerFrame / 4, squareSize, squareSize);
        g.drawLine(6 * squareSize - 1, 0, 6 * squareSize - 1, height);
    }

  }
