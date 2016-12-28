import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Content extends JPanel implements ActionListener {
  private static final String DATA_FILE_NAME = ".phone_book_demo";
  private static final File USER_HOME_DIR = new File(System.getProperty("user.home"));
  private static final File DATA_FILE = new File(USER_HOME_DIR, DATA_FILE_NAME);
  private static TreeMap<String,String>  phoneBook;
  private static DefaultTableModel dm;
  private static JTable table;
  private static JPanel buttonsPanel;
  
  public Content() {
    phoneBook = new TreeMap<String,String>();
    String[][] toInsert = retrieveBook();  
    buttonsPanel = createButtonsPanel();
    
    dm = new DefaultTableModel( toInsert, new String[] {"Name", "Phone"});
    table = createTable(dm);
    JScrollPane scroll = new JScrollPane(table);
    add(scroll);
  }
  
  private String[][] retrieveBook() {
    String[][] toInsert = new String[5][2];
     if ( ! DATA_FILE.exists() ) {
        System.out.println("No phone book data file found.  A new one");
        System.out.println("will be created, if you add any entries.");
        System.out.println("File name:  " + DATA_FILE.getAbsolutePath());
      }
      else {
        System.out.println("Reading phone book data...");
        Document xmldoc = null;
        try {
          DocumentBuilder docReader = DocumentBuilderFactory.newInstance()
              .newDocumentBuilder();
          xmldoc = docReader.parse(DATA_FILE);
        } catch (Exception e) {
          System.out.println("Error ocurred.");
          System.exit(1);
        }
        try {
          Element rootEle = xmldoc.getDocumentElement();
          if (!rootEle.getNodeName().equals("phone_directory")) {
            System.out.println("Wrong file");
            System.exit(1);
          }
          NodeList list = rootEle.getElementsByTagName("entry");
          for (int i = 0; i < list.getLength(); i++) {
            Element ele = (Element)list.item(i);
            String x = ele.getAttribute("name");
            String y = ele.getAttribute("number");
            phoneBook.put(x, y);
            toInsert[i][0] = x;
            toInsert[i][1] = y;           
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
     return toInsert;
  }
  
  private JPanel createButtonsPanel() {
    JPanel panel = new JPanel( new GridLayout(1, 2) );
    panel.setPreferredSize( new Dimension(400, 50));
    Font font = new Font("SansSerif", Font.BOLD, 24);
    panel.add(createButton("Add row", font));
    panel.add(createButton("Remove row", font));
    return panel;
  }
  
  private JButton createButton(String text, Font font) {
    JButton btn = new JButton(text);
    btn.addActionListener(this);
    btn.setFont(font);
    return btn;
  }
  
  private JTable createTable(DefaultTableModel dm) {
    table = new JTable(dm);  
    table.setFont(new Font("SansSerif", Font.PLAIN, 20));
    table.setAlignmentX(CENTER_ALIGNMENT);
    table.setAlignmentY(CENTER_ALIGNMENT);
    table.setRowHeight(table.getRowHeight() + 15);
    return table;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    stopEditing();
    if (cmd.equals("Add row"))
      dm.addRow(new String[] { "" });
    else {
      int row = table.getSelectedRow();
      if (row != -1)
        dm.removeRow(row);
    }
  }
  
  private static void stopEditing() {
    if (table.getCellEditor() != null)
      table.getCellEditor().stopCellEditing();
  }
    
  public void windowClosing() {
    System.out.println("CLOSING");
    stopEditing();
    phoneBook = new TreeMap<String, String>();

    for (int i = 0; i < table.getRowCount(); i++) {
      String name = (String) table.getValueAt(i, 0);
      String phone = (String) table.getValueAt(i, 1);
      if (name == null || phone == null )
        continue;
      phoneBook.put(name, phone);
    }
  }

  public void windowClosed() {
    stopEditing();
    saveData();
    System.out.println("CLOSED. File saved");
  }
  
  private void saveData() {
    System.out.println("Saving phone directory changes to file " + 
        DATA_FILE.getAbsolutePath() + " ...");
    PrintWriter out = null;
    try {
      out = new PrintWriter(new FileWriter(DATA_FILE));
    } catch (IOException e) {
      System.out.println("ERROR: Can't open data file for output.");
      System.exit(1);
    }
    out.println("<?xml version=\"1.0\"?>");
    out.println("<phone_directory>");

    for (Map.Entry<String, String> entry : phoneBook.entrySet())
      out.println("   <entry name='" + entry.getKey() + "' number='" + 
          entry.getValue() + "' />");

    out.println("</phone_directory>");
    out.flush();
    out.close();
    if (out.checkError())
      System.out.println("ERROR: Some error occurred while writing data file.");
    else
      System.out.println("Done.");
  }
  
  public JPanel getButtonsPanel() { 
    return buttonsPanel;
  }
}
