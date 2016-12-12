import java.util.TreeMap;

/**
 * Rewrite the PhoneDirectory class from Subsection 7.4.2 so that it uses a TreeMap 
 * to store directory entries, instead of an array. 
 */
public class Exercise_10_1 {
  
  // nested PhoneDirectory Class -------
  
  /**
   * A PhoneDirectory holds a list of names with a phone number for
   * each name.  It is possible to find the number associated with
   * a given name, and to specify the phone number for a given name.
   */
  public class PhoneDirectory {
    private TreeMap<String, Integer> data;  // Map that holds the name/number pairs.
     
    /**
     * Constructor creates an initially empty directory.
     */
    public PhoneDirectory() {
      data = new TreeMap<>();
    }
     
    /**
     * Looks for a name/number pair with a given name.  If found, the true 
     * is returned, otherwise false. 
     */
    private boolean find(String name) {
      return data.containsKey(name);
    }
     
    /**
     * Finds the phone number, if any, for a given name.
     * @return The phone number associated with the name; if the name does
     *    not occur in the phone directory, then the return value is null.
     */
    public int getNumber(String name) {
      return data.get(name);
    }
     
    /**
     * Associates a given name with a given phone number.  If the name
     * already exists in the phone directory, then the new number replaces
     * the old one.  Otherwise, a new name/number pair is added.  The
     * name and number should both be non-null.  An IllegalArgumentException
     * is thrown if this is not the case.
     */
    public void putNumber( String name, String number ) {
       if (name == null || number == null)
          throw new IllegalArgumentException("name and number cannot be null");
       data.put(name, new Integer(number));
    }
  }
}