import static java.lang.System.out; // static import;

/**
 * 
 * To "capitalize" a string means to change the first letter of each word in the 
 * string to upper case (if it is not already upper case). For example, a capitalized 
 * version of "Now is the time to act!" is "Now Is The Time To Act!". Write 
 * a subroutine named printCapitalized that will print a capitalized version 
 * of a string to standard output. The string to be printed should be a parameter 
 * to the subroutine. Test your subroutine with a main() routine that gets a line of 
 * input from the user and applies the subroutine to it.
 *
 */

public class Exercise_4_1 {

	public static void main(String[] args) {
		printCapitalized("check how 4 it works but only with one 3 empty space"
				+ " between the words.");
	}
	private static void printCapitalized(String s) {
		
		s = s.trim();	// cutting empty edges of the 's' formal parameter;
		String word;	// processed word;
		int indexBegin = 0,	// index of the first character in the current word;
			indexEnd = 0;	// index of the last character in the current word;
		
		char ch,	// current character;
			 ch2,	// character to capitalize;
			 ch3;	// character already capitalized;
		
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);	
			if (ch == ' '){
				indexEnd = i;	
				word = s.substring(indexBegin, indexEnd).trim();
				ch2 = word.charAt(0);
				ch3 = Character.toUpperCase(ch2);	
				out.printf("%1s %n", ch3 + word.substring(1));
				indexBegin = i;
			}
		} 
		word = s.substring(indexBegin + 1).trim();
		ch2 = word.charAt(0);
		ch3 = Character.toUpperCase(ch2);
		
		out.printf("%1s %n", ch3 + word.substring(1));
	}
}
