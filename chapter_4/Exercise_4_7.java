public class Exercise_4_7 {
  // global variables
  public static int[] firstNum = new int[10], // array of first operands
  secondNum = new int[10],	                  // array of second operands
  answer = new int[10];	                      // array of user's answers
  
  public static void main(String[] args) {
    openQuiz();
    askQuestion();
    gradeAnswers();
  }
  
/**
* Method checks and grades the answers.
*/
  private static void gradeAnswers() {
    int score = 0;
    for (int i = 0; i < 10; i++) {
      int result = firstNum[i] + secondNum[i];
      System.out.printf("%nThe answer for %1s. question is: %1s + %1s = 1%s %n",
      (i+1), firstNum[i], secondNum[i], result);
      System.out.printf("and your answer was %1s%n", answer[i]);
      if (result == answer[i]) {
        score += 10;
      System.out.printf("%nCorrect answer! +10 points!%n");
      } else
        System.out.printf("%nWrong answer!%n");
    }
    System.out.printf("%nYour score is: %1s.", score);	
  }
  
/**
* Method prepares and ask the questions. The operands of the question's 
* equation are in the range from 0 to 200.
*/
  private static void askQuestion() {
    for (int i = 0; i < 10; i++) {
      System.out.println("------------------------------------");
      System.out.printf("The %1s. question is: ", (i + 1));
      firstNum[i] = (int)(201 * Math.random());
      secondNum[i] = (int)(201 * Math.random());
      System.out.printf("%1s + %1s = ", firstNum[i], secondNum[i]);
      answer[i] = TextIO.getlnInt();	
      System.out.println();
    }
    System.out.printf("%nThank you for your answers.%n%nPlease check your score!%n");
  }
  
/**
* Method opens the quiz in the form of short briefing.
*/
  private static void openQuiz() {
    System.out.printf("%n %25s %1s %n","","Welcome in the addition quiz!");
    System.out.printf("%n %1s %n %1s %n ","You will be asked ten questions. Each question"
        + " will consist of two randomly chosen numbers.",
          "Your task is to calculate correctly the equation."
        + " Each correctly answered question gives 10 points.");
    System.out.printf("%n Please type your answer after '=' sign.%n%n");
  }
}