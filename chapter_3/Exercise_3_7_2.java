/**
 * 
 * Suppose you choose 365 people at random. How many different birthdays will 
 * they have? (The number could theoretically be anywhere from 1 to 365).
 *
 */
public class Exercise_3_7_2 {
  public static void main(String[] args) {
    // holding boolean values in order to check whether particular 
    // birthday date has occurred before or not;
    boolean[] dates = new boolean[365]; 	
    int sameBirthdayCount = 0; // keeping number of repeated birthdays;

    int date;
    for (int i = 0; i < 365; i++) {
      date = (int)(365 * Math.random());
        if ( dates[date] == true ) {
          sameBirthdayCount++;
        }
        else
          dates[date] = true;
    }
    System.out.println("There were " + (365 - sameBirthdayCount) + 
    " people with different birthday dates.");	
  }
}
