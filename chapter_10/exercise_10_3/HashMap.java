public class HashMap { 
  private LinkedList[] hashMap = new LinkedList[10000];
  private int size; // number of elements in the hashMap;
  
  /**
   * Looks for given key in the hash table, and brings the value associated
   * with the key, if no key found or if the hash table is empty - null is 
   * returned.
   * @param key Key of entry from the hash table to be fetched. 
   * @return Value of the fetched entry.
   */
  public String get(String key) {    
    if (hashMap[key.hashCode()].size() > 0)
      return hashMap[key.hashCode()].get(key);
    else 
      return null;
  }
  
  /**
   * Adds new key/value pair to the hash table.
   * @param key Key of the key/value pair
   * @param value Value of the key/value pair.
   */
  public void put(String key, String value){
    if (key == null || value == null)
      throw new NullPointerException("Key or value of key/value pair is empty.");
    
    int code = key.hashCode();
    if (hashMap[code] == null) {
      // There is no list yet at given code. Add new, and add new item to the list.
      hashMap[code] = new LinkedList(); 
      hashMap[code].add(value, key);
      size++;
    } else {
      hashMap[code].add(value, key);
      size++;
    }
  }

  /**
   * Removes given key/value pair from the hash table.
   * @param key Key of the key/value pair
   */
  public void remove(String key){ 
    int code = key.hashCode();
    
    if (containsKey(key)) {
      if (hashMap[code].size() == 1) {
        // There is only one node in given list. Nullify it.
        hashMap[code] = null;
        size--;
      }
      else {
        hashMap[code].remove(key);
        size--;
      }  
    }
  }
  
  /**
   * Finds out if the hash table contain specified key.
   * @param key Key of the key/value pair.
   * @return True if the hash table contains given key.
   */
  public boolean containsKey(String key){
    return hashMap[key.hashCode()].get(key) != null;
  }
  
  /**
   * Returns the number of key/value pair in the hash table.
   * @return Number of elements in given hash table.
   */
  public int size(){
    return size;
  }
}