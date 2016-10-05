/**
 * 
 * Suppose that a file named "testdata.txt" contains the following information:
 * The first line of the file is the name of a student. Each of the next three
 * lines contains an integer. The integers are the student's scores on three exams. 
 * Write a program that will read the information in the file and display (on 
 * standard output) a message that contains the name of the student and the 
 * student's average grade on the three exams. The average is obtained by adding
 * up the individual exam grades and then dividing by the number of exams. 
 *
 */
public class Exercise_7 {

	public static void main(String[] args) {		
		// reading the file;
		TextIO.readFile("C:/Users/lenovo/testdata.txt");
		
		String studentNam;
		int exam1, exam2, exam3;
		double mean;
		
		// reading and assigning values
		studentNam = TextIO.getln();
		exam1 = TextIO.getInt();
		exam2 = TextIO.getInt();
		exam3 = TextIO.getInt();
		mean = (double)(exam1 + exam2 + exam3) / 3;
		
		// output 
		System.out.printf("Student %s's average exam score is %1.1f", studentNam, mean);
	}

}
