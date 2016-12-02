/**Suppose that M is a two-dimensional array that has R rows and C columns. The 
 * transpose of M is defined to be an array T that has C rows and R columns such 
 * that T[i][j] = M[j][i] for each i and j. Write a function that takes an array of 
 * type int[][] as a parameter, and returns the transpose of that array. (Assume 
 * that the parameter is a typical 2D array in which all the rows have the same 
 * length.) Also write a subroutine to print a 2D array of integers in neat rows 
 * and columns, and include a main() routine to test your work.
 */
public class Exercise_7_2 {
  public static void main(String[] args) {
    int[][] M = { {1,2,3,4,5},
                  {10,20,30,40,50},
                  {100,200,300,400,500},
                  {1000,2000,3000,4000,5000}
                };
    print2D(M);
    print2D(transpose(M));
  }

  /**
  * Subroutine returns transposed version of given 2D array.
  * T[j][i] = M[i][j]
  * @param M 2D array to be transposed
  * @return Transposed 2D array
  */
  public static int[][] transpose(int[][] M) {
    int[][] T = new int[M[0].length][M.length];
    for (int i = 0; i < M.length; i++) 
      for (int j = 0; j < M[i].length; j++ ) 
        T[j][i] = M[i][j];
    return T;
  }

  /*Subroutine for printing the 2D array */
  public static void print2D(int[][] array) {
    for (int[] rows : array) {
      for (int ele : rows)
        System.out.print(ele + " ");
    System.out.println("");
    }
  }
}

 