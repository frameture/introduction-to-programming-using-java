public class Test {
	public static void main(String[] args) {
		StatCalc calc = new StatCalc();		// object of StatCalc class
		System.out.printf("%n"
				+ "Welcome to the 'Statistics Calculator'.%n"
				+ "The calculator will return you results of several statitcs methods such as average%n"
				+ "out of data input by you.%n"
				+ "You can input your numbers one by one, each confirmed with 'enter'.%n%n");
		while (true) {	// work while user inputs next numbers
			System.out.println("Please type un the number (e.g. 10.5; 6.0) and click 'enter'."
					+ " In order to get results tyupe '0' -(zero) and click 'enter' ");
			double input = TextIO.getlnDouble();
			if (input == 0)
				break;
			else{
				calc.enter(input);
			}
		}
		System.out.printf("%nThe average out of input numbers is: %1s%n", calc.getMean());
		System.out.printf("%nThe sum out of input numbers is: %1s%n", calc.getSum());
		System.out.printf("%nTotal input nmbers quantity is: %1s%n", calc.getCount());
		System.out.printf("%nThe standard deviation out of input numbers is: %1s%n", calc.getStandardDeviation());
		System.out.printf("%nThe maximal number out of input numbers is: %1s%n", calc.getMaxEnter());
		System.out.printf("%nThe minimal number out of input numbers is: %1s%n", calc.getMinEnter());
	}
}
