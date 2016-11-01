import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

private class Content extends JPanel implements MouseListener {
	private Die die, die2; // Die objects
	
	public Content() {
		die = new Die(50, 50, 200);
		die2 = new Die(300, 300, 200);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(550, 550));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		addMouseListener(this); // Content class as a listener
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		// turn on antialiasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		die.draw(g);
		die2.draw(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		die.roll();
		die2.roll();
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