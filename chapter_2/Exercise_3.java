
// working with TextIO class (Input/Output class written by Eck
// to enable easier working with Input in Java);
// first interactive feature - TextIO.getlnWord();

public class Exercise_3 {
	public static void main(String[] args) {
		String name, nameUp;
		
		System.out.println("Please write your name.");
		
		name = TextIO.getlnWord();
		nameUp = name.toUpperCase();
		
		System.out.printf("Hello %s !!!%n",nameUp);
	}
}
