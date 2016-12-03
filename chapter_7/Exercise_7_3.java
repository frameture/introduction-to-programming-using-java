import java.util.Arrays;

/** In Subsection 7.4.4, it is mentioned that the standard sorting method 
  * Arrays.sort() is much faster and efficient than selection sort. Write 
  * a program to test this claim. To be specific, your program should create 
  * a large array filled with random real numbers. It should use both Arrays.sort() 
  * and selectionSort() to sort the array, and it should time how long it takes 
  * to perform each sort. Furthermore, it should do the same thing for a large 
  * array of random Strings. To find the times, you can use System.currentTimeMillis()
  */
public class Exercise_7_3 {
  private static final int PROBLEMS = 10000; // arrays length
	  
  public static void main(String[] args) {
	  doDoubles();
	  doStrings();
  }
  
  private static void doDoubles() {
    double[] array, arrayCopy; // two identical arrays of random, not sorted 
                               // double variables; needed for comparison of 
                               // time consumed by two sorting methods
    array = randomDoubles(PROBLEMS);                       
    arrayCopy = Arrays.copyOf(array, array.length); 

    /* Time difference between two sorting methods: selectionSort() and Array.sort() */
    long sortTime = sortingTime(array, true),
         selectionSortTime = sortingTime(arrayCopy, false),
         diff = selectionSortTime - sortTime;

    System.out.println("Time of Arrays.sort() = " + sortTime + 
                       " Time of selection sort = " + selectionSortTime + 
                       " and the difference is " + diff);
  }
  
  private static void doStrings() {
    String[] array, arrayCopy; // two identical arrays of random, not sorted 
                               // double variables; needed for comparison of 
                               // time consumed by two sorting methods
    array = randomStrings(PROBLEMS);                       
    arrayCopy = Arrays.copyOf(array, array.length); 

    /* Time difference between two sorting methods: selectionSort() and Array.sort() */
    long sortTime = sortingTime(array, true),
         selectionSortTime = sortingTime(arrayCopy, false),
         diff = selectionSortTime - sortTime;

    System.out.println("Time of Arrays.sort() = " + sortTime + 
                       " Time of selection sort = " + selectionSortTime + 
                       " and the difference is " + diff);
  }
  
  /**
   * Creates an array of random doubles.
   * @param elements specifies the length of newly created array
   * @return not sorted array of random double elements
   */
  private static double[] randomDoubles(int elements) {
    double[] array = new double[elements];
    for (int i = 0; i < array.length; i++) {
      double chance = Math.random();
      if (chance < 0.25) 
        array[i] = Math.random() * (1000);	
      else if (chance <= 0.50)
        array[i] = Math.random() * (1001);	
      else if (chance <= 0.75)
        array[i] = Math.random() * (1002);
      else
        array[i] = Math.random() * (1003);	
    }
   return array;
 }
  
  /**
   * Creates an array of random Strings. Each string may be of length between 1 and 10
   * @param elements specifies the length of newly created array
   * @return not sorted array of random String elements
   */
  private static String[] randomStrings(int elements) {
    String[] array = new String[elements];
    for (int i = 0; i < array.length; i++) {
      int randomLength = (int) (Math.random() * 16) + 10; // 10 - 25 length
      StringBuilder builder = new StringBuilder();
        for (int j = 0; j < randomLength; j++) {
          /* Pick random integer in the range of 65-90 and create a character 
          * out of it. The range 65-90 represents characters set of A-Z. 65 = 'A' */
          int randomChar = (int) (Math.random() * 26) + 65; 
           builder.append((char) randomChar);
        }
        array[i] = builder.toString(); // add current word to the array
    }
   return array;
 }
  
  /**
   * Measures time consumed for sorting given array of doubles.  
   * @param array of doubles
   * @param isStandardSort specifies which sorting method should be used
   * @return time consumed for sorting given array
   */
  private static long sortingTime(double[] array, boolean isStandardSort) {
    long start = 0;  // starting time
    if (isStandardSort) { 
      start = System.currentTimeMillis(); 
      Arrays.sort(array);
    } else {	
      start = System.currentTimeMillis();
      selectionSort(array);
    }
    return System.currentTimeMillis() - start; // measure time consumed
  }
  
  /**
   * Measures time consumed for sorting given array of strings.  
   * @param array of strings
   * @param isStandardSort specifies which sorting method should be used
   * @return time consumed for sorting given array
   */
  private static long sortingTime(String[] array, boolean isStandardSort) {
    long start = System.currentTimeMillis();   // starting time
    if (isStandardSort)  
      Arrays.sort(array);
    selectionSort(array);
    return System.currentTimeMillis() - start; // measure time consumed
  }

  /**
   * Sort an array of real numbers using the selection sort algorithm.
   */
  private static void selectionSort(double[] numbers) {
    for (int top = numbers.length-1; top > 0; top-- ) {
      int maxloc = 0;
      for (int i = 1; i <= top; i++) {
        if (numbers[i] > numbers[maxloc])
          maxloc = i;
      }
      double temp = numbers[top];
      numbers[top] = numbers[maxloc];
      numbers[maxloc] = temp;
    }
  }
          
  /**
   * Sort an array of strings using the selection sort algorithm.
   */
  private static void selectionSort(String[] numbers) {
    for (int top = numbers.length-1; top > 0; top-- ) {
      int maxloc = 0;
      for (int i = 1; i <= top; i++) {
        if (numbers[i].compareTo(numbers[maxloc]) > 0)
          maxloc = i;
      }
      String temp = numbers[top];
      numbers[top] = numbers[maxloc];
      numbers[maxloc] = temp;
    }
  }
}