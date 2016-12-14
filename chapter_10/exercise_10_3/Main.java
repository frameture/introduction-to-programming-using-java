public class Main {
  private static final int N = 3; // Number of key/value pairs that will be 
                                  // put to newly created HashMap
  public static void main(String[] args) {
    HashMap map = new HashMap();

    // Fill up the map with N random integers.
    for (int i = 0; i < N; i++)
      // Add new key/value pair to the hashMap. Key is the integer in in the
      // range from 0 - N. Key is the random integer in range 0 - 9.
      map.put("" + i, "" + (int)(Math.random() * 10) );
    
    // Print the content of the map.
    for (int i = 0; i < map.size(); i++) {
      String index = ""+ i;
      System.out.print("Value = " + map.get(index) );
      System.out.println(" Code = " + index.hashCode() + " key = " + i );
    } 
    
    // Remove first two pairs and output the content of the HashMap.
    map.remove(""+ 2);
    map.remove(""+ 1);
    System.out.println("\nAfter removing");
    
    for (int i = 0; i < map.size(); i++){
      String index = ""+ i;
      System.out.print("Value = " + map.get(index) );
      System.out.println(" Code = " + index.hashCode() + " key = " + i );
    } 
  }
}
