public class Exercise_5_7 {
		// Nested classes and interfaces
	/**
	 * 
	 * Interface that defines the structure for two Classes that will
	 * implement it.
	 *
	 */
	public interface IntQuestion {
		/**
		 * Returns String representing the current IntQustion object.
		 * @return The question in the form of String is returned
		 */
	    public String getQuestion();
	    /**
	     * Returns answer as an integer of current IntQustion object.
	     * @return Correct answer is returned
	     */
	    public int getCorrectAnswer();
	    /**
	     * 
	     * @return First operand of current IntQuestion object is returned
	     */
	    public int getA();
	    /**
	     * @return Second operand of current IntQuestion object is returned
	     */
	    public int getB();
	}
	/** 
	 * Implements IntQuestion interface. Represents addition question to the quiz
	 *
	 */
	static class AdditionQuestion implements IntQuestion{
	    private int a, b;  // The numbers in the problem.
	    public AdditionQuestion() { // constructor
	        a = (int)(Math.random() * 50 + 1);
	        b = (int)(Math.random() * 50);
	    }
	    public String getQuestion() {
	        return "What is " + a + " + " + b + " =";
	    }
	    public int getCorrectAnswer() {
	        return a + b;
	    }
		public int getA() {
			return a;
		}
		public int getB() {
			return b;
		}
	}
	/** 
	 * Implements IntQuestion interface. Represents subtraction question to 
	 * the quiz
	 *
	 */
	static class SubtractionQuestion implements IntQuestion{
	    public int a, b;  // The numbers in the problem.
	    public SubtractionQuestion() { // constructor
		     a = (int)(Math.random() * 50 + 1);
		     b = (int)(Math.random() * 50);
		     if (a < b) {
		    	 int temp = a; //temporary integer
		    	 a = b;
		    	 b = temp;
		     }	    	 	
	    }
	    public String getQuestion() {
	        return "What is " + a + " - " + b + " ?";
	    }
	    public int getCorrectAnswer() {
	        return a - b;
	    }
		public int getA() {
			return a;
		}
		public int getB() {
			return b;
		}
	}
	public static IntQuestion[] question = new IntQuestion[10];
	public static int[] answer = new int[10];
	public static void main(String[] args) {
		openQuiz();
		askQuestion();
		gradeAnswers();
	}
	private static void gradeAnswers() {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			// AdditionQuestion instance case
			if (question[i] instanceof AdditionQuestion)
			System.out.printf("%nThe answer for %1s. question is: %1s + %1s = "
					+ "%s %n", (i + 1), question[i].getA(), question[i].getB(), 
					question[i].getCorrectAnswer());
			else if (question[i] instanceof SubtractionQuestion)
				System.out.printf("%nThe answer for %1s. question is: %1s - %1s"
						+ " = %s %n", (i + 1), question[i].getA(), question[i]
								.getB(), question[i].getCorrectAnswer());	
			else
				System.out.printf("%nThe answer for %1s. question is: %1s - %1s"
						+ " = %s %n", (i + 1), question[i].getA(), question[i]
								.getB(), question[i].getCorrectAnswer());	
			System.out.printf("and your answer was %1s%n",answer[i]);
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
		for (int i = 0; i < 10; i++) {
			switch((int)(Math.random() * 2)) {
			case 0:	
				question[i] = new AdditionQuestion();
				break;	
			default:
				question[i] = new SubtractionQuestion();
				break;
			}
			question[9] = new IntQuestion() { // anonymous inner class
			    public String getQuestion() {
			        return "What is the answer to the ultimate question " +
			                 " of life, the universe, and everything?";
			    }
			    public int getCorrectAnswer() {
			        return 42;
			    }
				@Override
				public int getA() {
					return 0;
				}
				@Override
				public int getB() {
					return 0;
				}
			};
		}
	}
}
