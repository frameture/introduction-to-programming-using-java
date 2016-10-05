/**
 * 
 * Write a program that reads one line of input text and breaks it up into words.
 * The words should be output one per line. A word is defined to be a sequence 
 * of letters. Any characters in the input that are not letters should be discarded.
 *
 */

public class Exercise_3_4 {

	public static void main(String[] args) {
		
		System.out.println("Please type in a sequence of words.");
		
		String input; // user's input;
		int inputLength; // length of the input string;
		boolean isWord = true; // flag variable for checking if current word
							   // is eligible for output;
			
		input = TextIO.getln();
		inputLength = input.length();
		
		char ch;
		String word = "";
		for(int i = 0; i < inputLength; i++) {
			
			ch = input.charAt(i);
			// concatenating the current word;
			word += ch;
			
			// searching for the end of word;
			if(ch == ' ' || i == inputLength - 1) {
				if(isWord)
					System.out.println(word);
				else
					isWord = true;
				word = "";
			}
			
			// checking if current word contains only letters;
			if(! Character.isLetter(ch) && ch != ' ') {
				isWord = false;
			}
		
		}
	}
		
}
