import java.util.ArrayList;

public class Main {
  private static final int N = 20; // Number of integers to be in the collection.
  
  public static void main(String[] args) {  
    ArrayList<Integer> list = new ArrayList<Integer>();
      // Create a collection of integers.
    for (int i = 0; i < N; i++)
      list.add( (int)(Math.random() * N * N) );
    
      // Provide a SomeCondition object which implements the Predicates.Predicate 
      // interface that allows for working with static methods from Predicates Class.
    testPredicatesBySeeing(list, new SomeCondition());
  }

  /**
   * Process the given collection of object and print the changes.
   * @param list Collection of integers to be processed.
   * @param predicate Predicate object that provides the specified condition.
   */
  private static void testPredicatesBySeeing(ArrayList<Integer> list, 
                                                     SomeCondition predicate) {
    System.out.println("Complete list: " + list);
    
      // Index of the first odd integer.
    int index = Predicates.find(new ArrayList<Integer>(list), predicate);
    System.out.println("Index of first odd integer is: " + index);
    
    ArrayList<Integer> collected = (ArrayList<Integer>) Predicates.collect(list, predicate);
    System.out.println("All odd integers are:  " + collected);
    
    Predicates.remove(list, predicate);
    System.out.println("All even integers are: " + list);
  }
}
