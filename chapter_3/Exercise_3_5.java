/**
 * 
 * Suppose that a file contains information about sales figures for a company in 
 * various cities. Each line of the file contains a city name, followed by a 
 * colon (:) followed by the data for that city. The data is a number of type 
 * double. However, for some cities, no data was available. In these lines, the 
 * data is replaced by a comment explaining why the data is missing. For example, 
 * several lines from the file might look like:
 *  
 * San Francisco:  19887.32
 * Chicago:  no report received
 * New York: 298734.12
 * 
 * Write a program that will compute and print the total sales from all the 
 * cities together. The program should also report the number of cities for 
 * which data was not available. The name of the file is "sales.dat".
 *
 */

public class Exercise_3_5 {

	public static void main(String[] args) {
		
		double accuProfit = 0; // accumulated profit from all the cities;
		int cityCounter = 0; // number of cities included in the given file;
		
		// reading the file;
		try{
			TextIO.readFile("profits.txt");
		}catch(Exception e){
			System.out.println("Cannot open the 'profits.txt' file.");
		}
		
		// while there is data in the file - process it;
		while(TextIO.eof() == false){
			String input, // one line of string input from the file;
				   inputProfit; // substring from the 'input' containing profit
								// of the current city  
			input = TextIO.getln();

			double profit = 0; // parsed profit for the current city;
			int colonIndex = 0; // index of the colon char in the input ':';
			int inputLength = input.length(); // length of the input string;
			
			for(int i = 0; i < inputLength; i++){
				char ch = input.charAt(i);
				if(ch == ':')
					colonIndex = i;
			}
				inputProfit = input.substring(colonIndex + 1);
				
				// trying to parse double which represents a profit, if 'inputProfit'
				// doesn't contain a double value then there is no profit assigned to 
				// the current city;
				try{
					profit = Double.parseDouble(inputProfit);	
				}
				catch(IllegalArgumentException e){
					System.out.printf("No data for particular City: %1s. %n",
							input.substring(0,colonIndex));
				}
				if(profit > 0){
					accuProfit += profit;
					cityCounter++;
				}
		}
		System.out.println("\n"
				+ "Average profit out of "+ cityCounter +" cities equals"
				+ " " + accuProfit / cityCounter);

	}

}
