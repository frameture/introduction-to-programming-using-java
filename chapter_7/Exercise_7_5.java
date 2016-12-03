// depends on TextIO class

/**
 * Write a program that will read a sequence of positive real numbers entered 
 * by the user and will print the same numbers in sorted order from smallest to 
 * largest. The user will input a zero to mark the end of the input. Assume that
 * at most 100 positive numbers will be entered. Do not use any built-in function 
 * such as Arrays.sort(). Do the sorting yourself.
 */
public class Exercise_7_5 {
  public static void main(String[] args) {
    System.out.println("Please type positive real numbers, each confirmed by return button."
                     + "to end typing type '0' and confirm by clicking return.");

    double[] data = new double[100]; // keeps user's entries
    int length = setUserData(data);  // fill the array
    sortDoubles(length, data);       // sort the array

    for (int i = 0; i < length; i++) // print sorted sequence
      System.out.println("" + (i + 1) + ". element is: " + data[i]);
  }

  /**
  * Input subroutine. Gets the sequence entries from the user.
  * @param array Array of doubles to be filled with entries
  * @return Number of non-empty entries
  */
  private static int setUserData(double[] array) {
    int count = 0; // keeping number of entries
    for (int i = 0; i < 100; i++) {
      double input = TextIO.getlnDouble();
      if (input < 0) {       // we consider only positive numbers
        System.out.println("Type only positive numbers.");
        input = TextIO.getlnDouble();
      } else if (input == 0) // user ends the sequence
        break;
      array[i] = input;
      count++;
    }
    return count; 
  }

  /**
  * Sorts the given array of numbers.
  * @param length How many elements are non-empty in the data
  * @param data User's entries
  */
  private static void sortDoubles(int length, double[] data) {
    for (int last = length - 1; last > 0; last--) {
      int maxLoc = 0;
      for (int j = 1; j <= last; j++) 
        if (data[j] > data[maxLoc])
          maxLoc = j;
      double temp = data[last];
      data[last] = data[maxLoc];
      data[maxLoc] = temp;
    }
  }
}
