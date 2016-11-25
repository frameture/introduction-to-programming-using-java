/**
 * 
 * How many random people do you have to select before you find three people 
 * who share the same birthday? (That is, all three people were born on the 
 * same day in the same month, but not necessarily in the same year.)
 *
 */
public class Exercise_3_7_1 {
  public static void main(String[] args) {
    int counter = 0;
    int date1, date2, date3;
    while(true) {
      date1 = (int)(365 * Math.random() + 1);
      date2 = (int)(365 * Math.random() + 1);
      date3 = (int)(365 * Math.random() + 1);
      counter++;
      if (date1 == date2 && date2== date3)
        break;
      }
      counter *= 3;
      System.out.println("There were needed "+ counter +" random people to find "
			 + "three with same birthday date.");
  }
}
