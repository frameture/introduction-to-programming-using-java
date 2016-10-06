/**
 * 
 * The hexadecimal digits are the ordinary, base-10 digits '0' through '9' plus 
 * the letters 'A' through 'F'. In the hexadecimal system, these digits represent 
 * the values 0 through 15, respectively. Write a function named hexValue that 
 * uses a switch statement to find the hexadecimal value of a given character. 
 * The character is a parameter to the function, and its hexadecimal value is 
 * the return value of the function. You should count lower case letters 'a' 
 * through 'f' as having the same value as the corresponding upper case letters. 
 * If the parameter is not one of the legal hexadecimal digits, return -1 as the 
 * value of the function.
 *
 */

public class Hex_Value {
	public static void main(String[] args) {
		String input = TextIO.getlnWord();	// user's input;
		int hexValue = 0;	// return value;
		System.out.println("Please type a hexadecimal integer");
		for (int i = 0; i < input.length(); i++) {
			hexValue = hexValue * 16 + hexValue(input.charAt(i)); // calculating the hex integer
			if (hexValue < 0){	// error case
				System.out.println("Incorrect hexadecimal integer. Program ends.");
				return;
			}
		}
		System.out.println("Hexadecimal value (base-10) of hexadecimal integer "
		+ input + " is " + hexValue);
	}
	/**
	 * 
	 * @param chToHex Variable of type 'char' to be translated
	 * @return Value of a char in hexadecimal digit 'int'; returns -1 if parameter is of wrong value
	 */
	private static int hexValue(char chToHex) {
		switch(chToHex){
			case '0':
				return 0;
			case '1':
				return 1;
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			case 'a':
			case 'A':
				return 10;
			case 'b':
			case 'B':
				return 11;
			case 'c':
			case 'C':
				return 12;
			case 'd':
			case 'D':
				return 13;
			case 'e':
			case 'E':
				return 14;
			case 'f':
			case 'F':
				return 15;	
				default:	// default error case;
					return -1;		
			}
	}	
}
