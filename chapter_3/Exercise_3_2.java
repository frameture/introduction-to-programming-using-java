/**
 * 
 * Which integer between 1 and 10000 has the largest number of divisors, and how
 * many divisors does it have? Write a program to find the answers and print out
 * the results. It is possible that several integers in this range have the same, 
 * maximum number of divisors. Your program only has to print out one of them. 
 *
 */

public class Exercise_3_2 {

	public static void main(String[] args) {
		
		// number of integers in search;
		int nToDivide = 10000, 	
			// integer with the highest number of divisors;		
		    intWithMaxDiv = 1,
		    // maximal number of divisors; 
			maxDivisors = 1;
		
		// looping through nToDivide;
		for (int i = 1; i <= nToDivide; i++) {
			// current number of divisors;
			int currDivisors = 0;
			// looping with counting the number of divisors;
			for (int i2 = 1; i2 <= nToDivide; i2++) {
				if (i % i2 == 0)
					currDivisors++;
				else if (i / i2 < 1)
					break;
				// saving the highest numbers;
				if (currDivisors > maxDivisors) {
					intWithMaxDiv = i;
					maxDivisors = currDivisors;
				}
			}
		}
		System.out.printf("\n Integer with the highest number of divisors is %1s"
				+ " and has %1s divisors.",
				intWithMaxDiv, maxDivisors);
	}

}
