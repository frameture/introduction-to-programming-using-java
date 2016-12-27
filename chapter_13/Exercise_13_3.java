import java.awt.event.*;
import javax.swing.*;

/**
 * The StopWatchLabel component from Subsection 13.4.5 displays the text "Timing..." 
 * when the stop watch is running. It would be nice if it displayed the elapsed 
 * time since the stop watch was started. For that, you need to create a Timer. 
 * (See Subsection 6.4.1.) Add a Timer to the original source code, StopWatchLabel.java, 
 * to drive the display of the elapsed time in seconds. Create the timer in the 
 * mousePressed() routine when the stop watch is started. Stop the timer in the 
 * mousePressed() routine when the stop watch is stopped. The elapsed time won't 
 * be very accurate anyway, so just show the integral number of seconds. You only 
 * need to set the text a few times per second. For my Timer method, I use a delay 
 * of 200 milliseconds for the timer.
 */
public class Exercise_13_3 extends JLabel implements MouseListener {
    private long startTime; 
    private boolean running; 
    private Timer timer;
    
    public Exercise_13_3() {
        super("Click to start the timer.", JLabel.CENTER);
        addMouseListener(this);
    }

    /**
     * Tells whether the timer is currently running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * React when the user presses the mouse by starting
     * or stopping the timer and changing the text that
     * is shown on the label.
     */
    public void mousePressed(MouseEvent evt) {
        if (running == false) {
                // Record the time and start the timer.
        	startTime = evt.getWhen();
        	long start = System.currentTimeMillis();
        	timer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setText("" + ((System.currentTimeMillis() - start) / 1000) 
							+ " Seconds passed.");	
				}
			});
        	timer.start();
            running = true; 
        }
        else {
            running = false;
            timer.stop();
        }
    }

    public void mouseReleased(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
}
