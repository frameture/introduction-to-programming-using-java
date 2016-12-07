import java.lang.Character;

/**
 * Class is intended to translate from Roman values to Arabic and vice versa
 */
public class RomanNumeral {
  // static list of corresponding Arabic and Roman numerals
  private static Translation[] translations = createTranslations(); 
  private int arabic;   // Arabic representation of the number
  private String roman; // Roman representation of the number

  /**
  * Translates the Arabic value to Roman value.
  * @param arabic Arabic numeral to be translated into Roman.
  */
  public RomanNumeral(int arabic) {
    StringBuilder roman = new StringBuilder(""); // builder of Roman numeral
    if (arabic < 1 || arabic > 3999)             // out-of-range error case
      throw new IllegalArgumentException("Number has to be in the range 1 - 3999.");
	  
    this.arabic = arabic;          // remember Arabic value
	
    for (int i = 0; i < 13; i++) { // translate from Arabic to Roman
      while (arabic >= translations[i].arabic) {
        arabic -= translations[i].arabic; 
        roman.append(translations[i].roman);
      }
    }
    // assign values to RomanNumeral object's instance variables
    this.roman = roman.toString();
  }

  /**
  * Translates the Roman value to Arabic value.
  * @param arabic Arabic numeral to be translated into Roman.
  */
  public RomanNumeral(String roman) {
    roman = roman.toUpperCase(); // to upper case

    checkForError(roman);        // check for illegal input
    this.roman = roman;          // remember the Roman value
	
    int prevValue = 0;           // Arabic value of [j - 1] Roman numeral
    for (int i = 0; i < roman.length(); i++) {
      // search for translation
      for (int j = 0; j < 13; j++) {
        String ch = "" + roman.charAt(i); // next character
        if (ch.equals(translations[j].roman)) {       // if preceding character was
          if (translations[j].arabic > prevValue) { // of smaller Arabic value, then
            arabic = arabic - 2 * prevValue;      // it is a Roman of 2-characters
          }
          this.arabic += translations[j].arabic;
          prevValue = translations[j].arabic;
          break; // translation found --- break
        }
      }			
    }
  }

  /**
  * Method checks for illegal Roman numeral.
  * @param roman Roman numeral
  */
  private void checkForError(String roman) {
    int countChars = 1;
    char ch = roman.charAt(0);
    for (int i = 1; i < roman.length(); i++) {
      if (Character.isDigit(ch))
        throw new NumberFormatException("Input cannot contain digits!");
      else if (!Translation.containsChar(ch))
        throw new IllegalArgumentException("Invalid Roman character '" + ch + "'!");
      else if (roman.charAt(i) == ch)
        countChars++;
      else {
        ch = roman.charAt(i);
        countChars = 1;
      }
      if (countChars >= 4)
        throw new IllegalArgumentException("Illegal Roman numeral. '" 
                        + ch + "' occurred " + countChars + " times.");
    }
    if (!Translation.containsChar(roman.charAt(roman.length() - 1)))
      throw new IllegalArgumentException("Invalid Roman character '" 
                           + roman.charAt(roman.length() - 1) + "'!");
  }

  /**
  * Static method to create an list of Translation objects
  * @return list of Translation objects.
  */
  private static Translation[] createTranslations() {
    Translation[] t = new Translation[13];

    t[0] = new Translation(1000, "M");
    t[1] = new Translation(900, "CM");
    t[2] = new Translation(500, "D");
    t[3] = new Translation(400, "CM");
    t[4] = new Translation(100, "C");
    t[5] = new Translation(90, "XC");
    t[6] = new Translation(50, "L");
    t[7] = new Translation(40, "XL");
    t[8] = new Translation(10, "X");
    t[9] = new Translation(9, "IX");
    t[10] = new Translation(5, "V");
    t[11] = new Translation(4, "IV");
    t[12] = new Translation(1, "I");

    return t;
  }

  /**
  * Nested class representing an object of corresponding Arabic and Roman numbers.
  */
  public static class Translation {	
    private int arabic;
    private String roman;
    public static char[] chars = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' }; // legal characters

    public Translation(int arabic, String roman) {
      this.arabic = arabic;
      this.roman = roman;
    }

    /**
    * Static method to find whether given character is a valid Roman character
    */
    public static boolean containsChar(char ch) {
      for (int i = 0; i < chars.length; i++)
        if (chars[i] == ch)
          return true;
        return false;
    }
  }

  // two getter methods
  public int toInt() { return arabic; }
  public String toString() { return roman; }
}