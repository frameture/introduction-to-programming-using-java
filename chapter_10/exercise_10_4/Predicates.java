import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Predicates Class. 
 * @param <T> Parameterized type that will be tested in the Predicate<T>.test(). 
 */
public class Predicates<T> {
  /**
   * Removes all objects from the collection that satisfy the condition 
   * specified by the Predicate.
   * @param coll Collection of objects.
   * @param predicate Predicate that provides the condition.
   */
  public static <T> void remove(Collection<T> coll, Predicate<T> predicate) {      
    Iterator<T> iter = coll.iterator();
    while (iter.hasNext())
      if (predicate.test( iter.next() ))
        iter.remove();
  }

  /**
   * Retain only the objects in the collection that satisfy the condition 
   * given in the Predicate.
   * @param coll Collection of objects
   * @param predicate Predicate with the specified condition.
   */
  public static <T> void retain(Collection<T> coll, Predicate<T> predicate) {      
    Iterator<T> iter = coll.iterator();
    while(iter.hasNext())
      if ( ! predicate.test(iter.next()) )
        iter.remove();
  }

  /**
   * Returns a collection of objects on which the Predicate's condition is true.
   * @param coll Collection of objects
   * @param predicate Predicate with the specified condition.
   * @return Collection of objects that satisfy the condition.
   */
  public static <T> List<T> collect(Collection<T> coll, Predicate<T> predicate) {
    List<T> list = new ArrayList<T>(coll); // Duplicate the collection and 
    retain(list, predicate);                    // call retain() on it.

    return list;
  }
  
  /**
   * Returns the index of the first item in list for which the predicateicate is 
   * true, if any. 
   * @param list Collection of objects.
   * @param predicate Predicate with the specified condition.
   * @return Index of the found object. If there is no such item, returns -1.
   */
  public static <T> int find(ArrayList<T> list, Predicate<T> predicate){
    for (T obj : list) {
      if (predicate.test(obj))
        return list.indexOf(obj);
    }
    return -1; // No such element.
  }
  
  /**
   * Nested interface that provides the test() method for working with the
   * static methods of the Predicates Class.  
   * @param <T> Parameterized type of the objects under testing.
   */
  public interface Predicate<T> {
    /**
     * Test the object to check whether the specified condition is true.
     * @param obj Object on which the method checks the condition.
     * @return True if condition on given object is true.
     */
    public boolean test(T obj);
  }
}
