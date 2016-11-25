import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

private class Content extends JPanel implements ActionListener {
  private StatCalc calc;	  // StatCalc object used for calculations
  private Font font;	 	  // font used in the program
  private JTextField textF; // text input field
  private JLabel label1, label2, label3, label4; // 4 text output labels 

  public Content() {
    super();
    calc = new StatCalc();
    font = new Font("SansSerif", Font.PLAIN, 25);

    setBackground(Color.BLACK);
    GridLayout gL = new GridLayout(6, 1, 0, 10);
    setLayout(gL);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
    JLabel label0 = makeJLabel("    Enter a number and press return.");
    add(label0);

    JPanel panel = new JPanel(); // container for textField and
				 //	buttons
    panel.setBackground(Color.LIGHT_GRAY);
    panel.setOpaque(true);

    textF = new JTextField(15);
    textF.setFont(font);
    textF.addActionListener(this);
    textF.setActionCommand("textF");
    panel.add(textF);			
    panel.add(makeJButton("Enter", this));
    panel.add(makeJButton("Clear", this));
    // adding panel to the Content container
    add(panel);
    // creating and adding JLabels to the Content container
    add(makeJLabel("   Number of Entries :  "+ calc.getCount()));
    add(makeJLabel("   Sum :  "+ calc.getSum()));
    add(makeJLabel("   Average :  "+ "Undefined"));
    add(makeJLabel("   Standard Deviation :  "+ "Undefined"));
  }
  
  // method for creating JButtons
  private JButton makeJButton(String text, ActionListener listener) {
    JButton button = new JButton(text);
    button.setFont(font);
    button.addActionListener(listener);
    return button;
  }
  
  // method for creating JLabels
  private JLabel makeJLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(font);
    label.setBackground(Color.LIGHT_GRAY);
    label.setOpaque(true);
    label.setForeground(Color.BLUE);
    return label;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if (cmd.equals("textF"))
      processInput(false);
    else if (cmd.equals("Enter"))
      processInput(false);
    else if (cmd.equals("Clear"))
      processInput(true);
  }

/**
* The input from JTextField is processed and entered into data if applies.
* If 'Clear' button is clicked - the 'reset' parameter should be set to true 
* @param reset Determines if the calculator should reset its data.
* True if user clicked
*/
  private void processInput(boolean reset) {
    String input = textF.getText();
    if (! input.isEmpty() && Character.isDigit(input.charAt(0))) {
      calc.enter((Double.parseDouble(input)));
      textF.setText("");
    }
    if (reset)
      calc = new StatCalc();
    label1.setText("   Number of Entries :  "+ calc.getCount());
    label2.setText("   Sum :  "+ calc.getSum());
    label3.setText("   Average :  "+ calc.getMean());
    label4.setText("   Standard Deviation :  "+ calc.getStandardDeviation());	
  }
}