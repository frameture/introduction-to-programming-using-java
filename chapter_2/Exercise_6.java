/**
 * This exercise asks you to write a program that tests some of the built-in
 * subroutines for working with Strings. The program should ask the user to 
 * enter their first name and their last name, separated by a space. Read the
 * user's response using TextIO.getln(). Break the input string up into two
 * strings, one containing the first name and one containing the last name.
 * You can do that by using the indexOf() subroutine to find the position of
 * the space, and then using substring() to extract each of the two names.
 * Also output the number of characters in each name, and output the user's
 * initials. (The initials are the first letter of the first name together with
 * the first letter of the last name.) A sample run of the program should look
 *  something like this:
 *
 *		Please enter your first name and last name, separated by a space.
 *		? Mary Smith
 *		Your first name is Mary, which has 4 characters
 *		Your last name is Smith, which has 5 characters
 *		Your initials are MS
 *
 *
 */
public class Exercise_6 {

	public static void main(String[] args) {
		String input, firstNam, lastNam;
		
		System.out.println("Please enter your first name and last name,"
				+ " separated by a space.");
		input = TextIO.getln().trim();
		
		// finding a space in the input in order to separate substrings;
		int index = input.indexOf(' ');
		// separating first and last names;
		firstNam = input.substring(0, index);
		lastNam = input.substring(index + 1);
		
		// output
		System.out.printf("Your first name is %s whiich has %d characters \n",
				firstNam, firstNam.length());
		System.out.printf("Your last name is %s whiich has %d characters \n",
				lastNam, lastNam.length());
		System.out.printf("Your initials are %s%s", firstNam.charAt(0),
				lastNam.charAt(0));
	}
	
}
