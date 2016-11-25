/**
 * 
 * How many different people do you have to check before you've found at least 
 * one person with a birthday on each of the 365 days of the year?
 *
 */

public class Exercise_3_7_3 {
  public static void main(String[] args) {
    // booleans of occurred dates;
    boolean[] dates = new boolean[365];	
    int peopleCounter = 0, 
    birthdayCounter = 0;

    // looping until 365 different birthdays are found;
    int date;
    while (birthdayCounter < 365) {
      date = (int)(365 * Math.random());
      peopleCounter++;
      if (dates[date] == false ) {
        birthdayCounter++;
        dates[date] = true;
      }
    }
    System.out.println("There were " + (birthdayCounter) + " people "
        + "with different birthday dates and it took " + peopleCounter +
        " random people to find them.");	
  }
}
