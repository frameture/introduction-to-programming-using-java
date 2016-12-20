import java.io.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * The sample program PhoneDirectoryFileDemo.java, from Subsection 11.3.2, stores 
 * name/number pairs for a simple phone book in a text file in the user's home 
 * directory. Modify that program so that it uses an XML format for the data. 
 * The only significant changes that you will have to make are to the parts of 
 * the program that read and write the data file. Use the DOM to read the data, 
 * as discussed in Subsection 11.5.2. You can use the XML format illustrated in 
 * the following sample phone directory file:
 *
 * <?xml version="1.0"?>
 * <phone_directory>
 *  <entry name='barney' number='890-1203'/>
 *  <entry name='fred' number='555-9923'/>
 * </phone_directory>
 *
 * (This is just a short exercise in basic XML processing; as before, the program 
 * in this exercise is not meant to be a useful phone directory program.)
 */

public class Exercise_11_5 {

  /**
   * The name of the file in which the phone book data is kept.  The
   * file is stored in the user's home directory.  The "." at the
   * beginning of the file name means that the file will be a
   * "hidden" file on Unix-based computers, including Linux and
   * Mac OS X.
   */
  private static String DATA_FILE_NAME = ".phone_book_demo";
  static boolean changed = false;  // Have any changes been made to the directory?

  public static void main(String[] args) {
    TreeMap<String,String>  phoneBook;   // Phone directory data structure.
                       // Entries are name/number pairs.

    phoneBook = new TreeMap<String,String>();

    /* Create a dataFile variable of type File to represent the
     * data file that is stored in the user's home directory.
     */

    File userHomeDirectory = new File( System.getProperty("user.home") );
    File dataFile = new File( userHomeDirectory, DATA_FILE_NAME );


    /* If the data file already exists, then the data in the file is
     * read and is used to initialize the phone directory.  The format
     * of the file must be as follows:  Each line of the file represents
     * one directory entry, with the name and the number for that entry
     * separated by the character '%'.  If a file exists but does not
     * have this format, then the program terminates; this is done to
     * avoid overwriting a file that is being used for another purpose.
     */
    Document xmlDoc = restoreFile(dataFile);
    if (xmlDoc != null)
      restoreData(xmlDoc, phoneBook);

    readCommands(phoneBook);

    if (changed)
      saveData(dataFile, phoneBook);
  }
  
  /**
   * Checks whether there is a current saved copy of the file containing XML data.
   * If so, the reference to the parsed document is returned. 
   */
  private static Document restoreFile(File dataFile) {
    Document xmlDoc = null;
    if ( ! dataFile.exists() ) {
      System.out.println("No phone book data file found.  A new one");
      System.out.println("will be created, if you add any entries.");
      System.out.println("File name:  " + dataFile.getAbsolutePath());
    }
    else {
      System.out.println("Reading phone book data...");
      try {
        DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        xmlDoc = docReader.parse(dataFile);
      } catch (Exception e) {
        System.out.println("Error ocurred.");
        System.exit(1);
      }
    }
    return xmlDoc;
  }
  
  /**
   * Restores the data from XML parsed document represented as DOM.
   */
  private static void restoreData(Document xmlDoc, TreeMap<String, String> phoneBook) {
    try {
      Element rootEle = xmlDoc.getDocumentElement();
      if (! rootEle.getNodeName().equals("phone_directory") ) {
        System.out.println("Wrong file");
        System.exit(1);
      }
      
      NodeList list = rootEle.getElementsByTagName("entry");
      for (int i = 0; i < list.getLength(); i++) {
        Element ele = (Element)list.item(i);
        String x = ele.getAttribute("name");
        String y = ele.getAttribute("number");
        phoneBook.put(x, y);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }  
  }
  
  /**
   * Read commands from the user and carry them out, until the
   * user gives the "Exit from program" command.
   */
  private static void readCommands(TreeMap<String, String> phoneBook) {
    String name, number;  // Name and number of an entry in the directory

    Scanner in = new Scanner(System.in);

    mainLoop: while (true) {
      System.out.println("\nSelect the action that you want to perform:");
      System.out.println("   1.  Look up a phone number.");
      System.out.println("   2.  Add or change a phone number.");
      System.out.println("   3.  Remove an entry from your phone directory.");
      System.out.println("   4.  List the entire phone directory.");
      System.out.println("   5.  Exit from the program.");
      System.out.println("Enter action number (1-5):  ");
      int command;
      if ( in.hasNextInt() ) {
        command = in.nextInt();
        in.nextLine();
      }
      else {
        System.out.println("\nILLEGAL RESPONSE.  YOU MUST ENTER A NUMBER.");
        in.nextLine();
        continue;
      }
      switch(command) {
      case 1:
        System.out.print("\nEnter the name whose number you want to look up: ");
        name = in.nextLine().trim().toLowerCase();
        number = phoneBook.get(name);
        if (number == null)
          System.out.println("\nSORRY, NO NUMBER FOUND FOR " + name);
        else
          System.out.println("\nNUMBER FOR " + name + ":  " + number);
        break;
      case 2:
        System.out.print("\nEnter the name: ");
        name = in.nextLine().trim().toLowerCase();
        if (name.length() == 0)
          System.out.println("\nNAME CANNOT BE BLANK.");
        else if (name.indexOf('%') >= 0)
          System.out.println("\nNAME CANNOT CONTAIN THE CHARACTER \"%\".");
        else { 
          System.out.print("Enter phone number: ");
          number = in.nextLine().trim();
          if (number.length() == 0)
            System.out.println("\nPHONE NUMBER CANNOT BE BLANK.");
          else {
            phoneBook.put(name,number);
            changed = true;
          }
        }
        break;
      case 3:
        System.out.print("\nEnter the name whose entry you want to remove: ");
        name = in.nextLine().trim().toLowerCase();
        number = phoneBook.get(name);
        if (number == null)
          System.out.println("\nSORRY, THERE IS NO ENTRY FOR " + name);
        else {
          phoneBook.remove(name);
          changed = true;
          System.out.println("\nDIRECTORY ENTRY REMOVED FOR " + name);
        }
        break;
      case 4:
        System.out.println("\nLIST OF ENTRIES IN YOUR PHONE BOOK:\n");
        for ( Map.Entry<String,String> entry : phoneBook.entrySet() )
          System.out.println("   " + entry.getKey() + ": " + entry.getValue() );
        break;
      case 5:
        System.out.println("\nExiting program.");
        break mainLoop;
      default:
        System.out.println("\nILLEGAL ACTION NUMBER.");
      }
    } in.close();
  }
  
  /**
   * Before ending the program, write the current contents of the
   * phone directory, but only if some changes have been made to
   * the directory.
   */
  private static void saveData(File dataFile, TreeMap<String, String> phoneBook) {
    System.out.println("Saving phone directory changes to file " + 
        dataFile.getAbsolutePath() + " ...");
    PrintWriter out;
    try {
      out = new PrintWriter( new FileWriter(dataFile) );
    }
    catch (IOException e) {
      System.out.println("ERROR: Can't open data file for output.");
      return;
    }
    out.println("<?xml version=\"1.0\"?>");
    out.println("<phone_directory>");
    
    for ( Map.Entry<String,String> entry : phoneBook.entrySet() )
      out.println("   <entry name='" + entry.getKey() + "' number='" + entry.getValue() + "' />" );
    
    out.println("</phone_directory>");
    out.flush();
    out.close();
    if (out.checkError())
      System.out.println("ERROR: Some error occurred while writing data file.");
    else
      System.out.println("Done.");
  }
}
