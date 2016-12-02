import java.util.ArrayList;

/** Write a subroutine that creates an ArrayList containing several different 
 * random integers in the range from 1 up to some specified maximum. The number 
 * of integers and the maximum allowed value for the integers should be parameters 
 * to the subroutine. Write a main() routine to test your subroutine.
 */
public class Exercise_7_1 {
  public static void main(String[] args) {
    ArrayList<Integer> list = makeList(15, 15);
  for (int ele : list)
    System.out.println(ele);
  }

  private static ArrayList<Integer> makeList(int elements, int maxValue ) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < elements; i++)
      list.add(new Integer( (int)(Math.random() * maxValue) + 1 ));
    return list;
  }
}

 