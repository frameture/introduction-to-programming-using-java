A predicate is a boolean-valued function with one parameter. Some languages use 
predicates in generic programming. Java 7 doesn't, but this exercise looks at 
how predicates might work.

In Java, we could implement "predicate objects" by defining a generic interface:

    public interface Predicate<T> {
      public boolean test( T obj );
    }

The idea is that an object that implements this interface knows how to "test" 
objects of type T in some way. Define a class that contains the following generic 
static methods for working with predicate objects. The name of the class should 
be Predicates, in analogy with the standard class Collections that provides 
various static methods for working with collections.

    public static <T> void remove(Collection<T> coll, Predicate<T> pred)
      // Remove every object, obj, from coll for which
      // pred.test(obj) is true.
   
    public static <T> void retain(Collection<T> coll, Predicate<T> pred)
      // Remove every object, obj, from coll for which
      // pred.test(obj) is false.  (That is, retain the
      // objects for which the predicate is true.)
   
    public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred)
      // Return a List that contains all the objects, obj,
      // from the collection, coll, such that pred.test(obj)
      // is true.
   
    public static <T> int find(ArrayList<T> list, Predicate<T> pred)
      // Return the index of the first item in list
      // for which the predicate is true, if any.
      // If there is no such item, return -1.

(In C++, methods similar to these are included as a standard part of the generic 
programming framework. And Java 8 has a similar predicate interface, and the Collections 
class has a removeIf() method that uses a predicate.)