
/**
 *   factorial(0)  =  1
 *   factorial(N)  =  N*factorial(N-1)   for N > 0
 * 
 *   fibonacci(0)  =  1
 *   fibonacci(1)  =  1
 *   fibonacci(N)  =  fibonacci(N-1) + fibonacci(N-2)   for N > 1
 *   
 * Write recursive functions to compute factorial(N) and fibonacci(N) for a given
 * non-negative integer N, and write a main() routine to test your functions.
 */
public class Exercise_9_1 {
  public static void main(String[] args) {
    for (int i = 1; i < 10; i++) {
      System.out.println("Factorial out of " + i + " is " + factorial(i));
      System.out.println("Fibonacci out of " + i + " is " + fibonacci(i));
    }
  }
  
  /**
   * Recursively calculates the factorial function.
   * @param n size of the problem
   * @return value of the function
   */
  private static int factorial(int n) {
    if (n == 0)
      return 1;
    else {
      return factorial(n - 1) * n;
    }
  }
  
  /**
   * Recursively calculates the Fibonacci function
   * @param n size of the problem
   * @return value of the function
   */
  private static int fibonacci(int n) {
    if (n == 0 || n == 1)
      return 1;
    else
      return fibonacci(n - 1) + fibonacci(n - 2);
  }
}