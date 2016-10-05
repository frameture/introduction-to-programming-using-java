         /**
         *
         * Write a subroutine that draws a checkerboard. 
         *
         */
  public class Exercise_3_8 {
    private void drawFrame(Graphics g, int frameNumber, int width, int height) {
        int plusXY = 100; // size of a square;
        Color black = Color.BLACK,
        		red = Color.RED; 
        for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		if ((j % 2 == 0 && i % 2 == 0) || (j % 2 == 1 && i % 2 == 1)) {
        			g.setColor(black);
        			g.fillRect(i * plusXY, j * plusXY, plusXY, plusXY);
        		}
        		else{
        			g.setColor(red);
        			g.fillRect(i * plusXY, j * plusXY, plusXY, plusXY);
        		}
        	}
    	}
   }
