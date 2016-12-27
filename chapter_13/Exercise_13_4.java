import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 * 
 * The custom component example MirrorText.java, from Subsection 13.4.5, uses an 
 * off-screen canvas to show mirror-reversed text in a JPanel. An alternative 
 * approach would be to draw the text after applying a transform to the graphics 
 * context that is used for drawing. (See Subsection 13.2.5.) With this approach, 
 * the custom component can be defined as a subclass of JLabel in which the 
 * paintComponent() method is overridden. Write a version of the MirrorText 
 * component that takes this approach. The solution is very short, but tricky. 
 * Note that the scale transform g2.scale(-1,1) does a left-right reflection 
 * through the left edge of the component.
 *
 */
public class Exercise_13_4 extends JLabel {

	@Override
	protected void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g.create();
      g2.translate(getWidth(),0);
      g2.scale(-1, 1);
      super.paintComponent(g2);
      g2.dispose();
	}
	
	public Exercise_13_4(String text){
		super(text, JLabel.CENTER);
	}
}
