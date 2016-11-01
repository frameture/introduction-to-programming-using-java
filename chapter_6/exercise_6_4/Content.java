import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Main GUI content of the program is created in this class.
 * All drawing is done here. The Timer object that will manage the 
 * animation, is also constructed in this class.
 */
private class Content extends JPanel implements ActionListener {
	private final int ANIMATION_FRAMES = 10;  // frames of animations
	private Die die, die2; 					  // Die objects
	private Timer timer;					  // starts an animation
	private int frames;						  // current animation's frame
	private JPanel panel;					  // this panel will be returned
											  // to the JFrame class by calling
											  // getPanel() method

	public Content() {
		die = new Die(50, 50, 200);
		die2 = new Die(300, 300, 200);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(550, 550));
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 0));	
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		JButton button = new JButton("Roll the dice");
		button.setPreferredSize(new Dimension(getWidth(), 50));
		button.setFont(new Font("Serif", Font.BOLD, 27));
		button.addActionListener(this); // Content object will listen to
										// ActionEvents, if one occurs the
										// Content's actionPerformed() method
										// will be called
		panel.add(button, BorderLayout.SOUTH);
		
		// construct new Timer object which manages animation frames
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frames < ANIMATION_FRAMES) {
					die.roll();
					die2.roll();
					repaint();
					frames++;
				}
				else
					timer.stop();
			}
		});
	}
	/**
	 * @return JPanel object. If no panel exists - null is returned
	 */
	public JPanel getPanel() {
		if (panel != null)
			return panel;
		else
			return null;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		   Graphics2D g2 = (Graphics2D)g;
		   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                               RenderingHints.VALUE_ANTIALIAS_ON);
		die.draw(g);
		die2.draw(g);
		g.fillRect(0, getHeight() - 3, getWidth(), 3);
	}
	/**
	 * This method fires a timer which start the actual animation
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		frames = 0;	   // reset animation 
		timer.start(); // button clicked - start animation
	}
}