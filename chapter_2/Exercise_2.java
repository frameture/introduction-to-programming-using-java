
// working with Math class;
// type casting;
// working with printf() - formatted output

public class Exercise_2 {
	public static void main(String[] args) {
		int dieOne = (int)(6 * Math.random()) + 1;
		System.out.println();
		System.out.println("The first die gives:  " + dieOne);
		
		int dieTwo = (int)(6 * Math.random()) + 1;
		System.out.println("The second die gives: " + dieTwo);
		
		int dice = dieOne + dieTwo;
		System.out.printf("Together they give: %3d %n ", dice);
	}
}
