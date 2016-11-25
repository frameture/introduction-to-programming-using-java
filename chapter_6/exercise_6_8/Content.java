import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

private class Content extends JPanel implements ActionListener {
  private JTextArea textArea;            // textArea where text is written	
  private JLabel label1, label2, label3; // output labels

  public Content() {
    setLayout(new BorderLayout());
    setBackground(Color.BLACK);

    textArea = new JTextArea(15, 40);
    textArea.setMargin(new Insets(5, 5, 5, 5));
    textArea.setFont(new Font("SansSerif", Font.PLAIN, 18));

    JScrollPane scroll = new JScrollPane(textArea);	
    add(scroll, BorderLayout.CENTER);

    JPanel panel = new JPanel(new GridLayout(4, 1, 0, 5));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    panel.setBackground(Color.BLACK);
    add(panel, BorderLayout.SOUTH);

    JButton processButton = new JButton("Process the text");
    processButton.setFont(new Font("Serif", Font.BOLD, 25));
    processButton.addActionListener(this);
    panel.add(processButton);

    label1 = makeLabel("  Number of Lines: 0");
    panel.add(label1);
    label2 = makeLabel("  Number of Words: 0");
    panel.add(label2);	
    label3 = makeLabel("  Number of Chars: 0");
    panel.add(label3);
  }

  private JLabel makeLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("SansSerif", Font.PLAIN, 25));
    label.setBackground(Color.LIGHT_GRAY);
    label.setOpaque(true);
    label.setForeground(Color.BLUE);
    return label;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String input = textArea.getText(); // text input from textArea
    int lines = 0;                     // number of lines in the input
    int words = 0;                     // number of words in the input	
    char ch;                           // Character processed in the loop
    boolean isWord = false;            // used to find words in the input

    for (int i = 0; i < input.length(); i++) {
      ch = input.charAt(i);			  
      if (Character.isLetterOrDigit(ch)) // is a word?
        // the numbers count as words
        isWord = true;		 
      if (ch == '\n') { // new line character
        lines++;
        if (isWord) {
          words++;
          isWord = false;
        }
      }			
      if(ch == ' ') {  // empty space - divisor
        if (isWord) {
          words++;
          isWord = false;
        }
      }
    }
    if (lines > 0) // add first line
      lines += 1;

    label1.setText("  Number of Lines: " + lines);
    label2.setText("  Number of Words: " + words);
    label3.setText("  Number of Chars: " + input.length());	
  }
}