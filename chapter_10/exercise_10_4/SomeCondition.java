public class SomeCondition implements Predicates.Predicate<Integer> {
  @Override
  public boolean test(Integer obj) {  
    return (obj % 2) == 1; // Find whether the integer is odd. 
  }  
}
