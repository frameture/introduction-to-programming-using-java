public class LinkedList {
   private ListNode head;
   private int size;
  
   /**
    * Adds new node to the list. If there is a node with specified
    * key, then the node's item will be replaced.
    * @param item Value of the node to be stored in the list.
    * @param key Key of the node to be stored in the list.
    */
   void add(String item, String key){
    if (head == null) {
      // Add first element to the list.
      head = new ListNode(item, key);
      size++;
    }
    else {
      // searching for next empty spot in the list
      ListNode runner = head;
      
      while(runner != null) {
        if (runner.key.equals(key)) {
          runner.item = item;
          return;
        }
        runner = runner.next;
      }
      runner = new ListNode(item, key);
      size++;
    }
  }
   
  /**
   * Removes the entry with specified key from the list.
   * @param key Key of the entry to be removed from the list.
   * @throws IllegalStateException If list is empty.
   */
  void remove(String key) {
    if (size == 0) {
      throw new IllegalStateException("Empty list.");
    }
    
    else if(head.key.equals(key)){
      head = null;  
      size--;
    } else {
      ListNode previous = head;
      ListNode runner = head.next;
      while( runner != null) {
        if (runner.key.equals(key)) {
          previous.next = runner.next;
          size--;
           } else {
             previous = runner;
             runner = runner.next;
           }
       }
    }
  }
  
  /**
   * Returns the value of the entry with specified key. If the key is not
   * included in the list then 'null' is returned.
   * @param key Key of the searched entry.
   * @throws IllegalStateException If the list is empty.
   * @return Value of the searched entry
   */
  String get(String key ){
    if (size == 0) {
      throw new IllegalStateException("Empty list.");
    }          
    ListNode runner = head;
    while( runner != null) {
      if (runner.key.equals(key))
        return runner.item;
      else
         runner = runner.next;      
       }
    return null; // Key not found.
  }
  
  /**
   * Returns the number of elements in the given linked list.
   * @return Number of elements in the linked list.
   */
  public int size() {
    return size;
  }
  
  /**
   * Nested class representing the node of the linked list.
   */
  private class ListNode {  
    ListNode next;
    String item;
    String key;
    
    public ListNode(String item, String key){
      this.item = item;
      this.key = key;
    }  
  }
}
