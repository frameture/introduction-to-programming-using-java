/**
 * 
 *  Exercise 3.2 asked you to find the number in the range 1 to 10000 that has 
 *  the largest number of divisors. You only had to print out one such number. 
 *  Revise the program so that it will print out all numbers that have the maximum 
 *  number of divisors. Use an array as follows: As you count the divisors for each 
 *  number, store each count in an array. Then at the end of the program, you can 
 *  go through the array and print out all the numbers that have the maximum count. 
 *  The output from the program should look something like this:
 *
 * Among integers between 1 and 10000,
 * The maximum number of divisors was 64
 * Numbers with that many divisors include:
 *  7560
 *  9240
 *
 */

public class Exercise_3_6 {
  public static void main(String[] args) {	
    // number of integers in search;
    int nToDivide = 10000, 	
    // maximal number of divisors; 
    maxDivisors = 1;
    // integer array holding values of divisors per each number of the nToDivide; 
    int[] divisors = new int[nToDivide]; 

    // looping through nToDivide;
    for (int i = 1; i <= nToDivide; i++) {
      // current number of divisors;
      int currDivisors = 0;
      // looping with counting the number of divisors;
        for (int i2 = 1; i2 <= nToDivide; i2++) {
          if (i % i2 == 0)
            currDivisors++;
          else if (i / i2 < 1) {
            divisors[i] = currDivisors;
            break;
          }
          // saving the highest numbers;
          if (currDivisors > maxDivisors) {
            maxDivisors = currDivisors;
          }
        }
    }
    System.out.println("Among integers between 1 and 10000,");
    System.out.println("The maximum number of divisors was " + maxDivisors);
    System.out.println("Numbers with that many divisors include: \n");

    for (int i = 0; i < divisors.length; i++) {
      if(divisors[i] == maxDivisors) {
        System.out.println("   " + i );
      }
    }
  }
}
