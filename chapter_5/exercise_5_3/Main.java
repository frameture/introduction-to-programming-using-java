public class Exe_5_6_Rewrite_4_7 {
	    // array of questions
		public static AdditionQuestion[] question = new AdditionQuestion[10];
		public static int[] answer = new int[10];	// array for user's answers
		public static void main(String[] args) {
			// constructing AdditionQuestion objects
			for (int i = 0; i < 10; i++) {
				question[i]= new AdditionQuestion();
			}		
			openQuiz();
			askQuestion();
			gradeAnswers();
		}
		private static void gradeAnswers() {
			int score = 0;
			for (int i = 0; i < 10; i++) {
				System.out.printf("%nThe answer for %1s. question is: "
						+ "%1s + %1s = %s %n", (i + 1), question[i].a, 
						question[i].b, question[i].getCorrectAnswer());
				System.out.printf("and your answer was %1s%n", answer[i]);
				if (question[i].getCorrectAnswer() == answer[i]) { // correct answer
					score += 10;
					System.out.printf("%nCorrect answer! +10 points!%n");
				} else
					System.out.printf("%nWrong answer!%n");
			}
			System.out.printf("%nYour score is: %1s.", score);	
		}
		private static void askQuestion() {	
			for (int i = 0; i < 10; i++) {
				System.out.println("------------------------------------");
				System.out.printf("The %1s. question is: ", (i + 1));
				System.out.println(question[i].getQuestion());
				answer[i] = TextIO.getlnInt();
				System.out.println();
			}
			System.out.printf("%nThank you for your answers.%n%nPlease check your score!%n");
		}
		private static void openQuiz() {
			System.out.printf("%n %25s %1s %n","","Welcome in the addition quiz!");
			System.out.printf("%n %1s %n %1s %n ","You will be asked ten questions. Each question"
							 + " will consist of two randomly chosen numbers.",
							 "Your task is to calculate correctly the equation."
							 + " Each correctly answered question gives 10 points.");
			System.out.printf("%n Please type your answer after '=' sign.%n%n");
		}
	}
