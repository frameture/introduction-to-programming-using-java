/**
 * Suppose that linked lists of integers are made from objects belonging to the class
 * 
 * class ListNode {
 *   int item;       // An item in the list.
 *   ListNode next;  // Pointer to the next node in the list.
 * }
 * 
 * Write a subroutine that will make a copy of a list, with the order of the 
 * items of the list reversed. The subroutine should have a parameter of type 
 * ListNode, and it should return a value of type ListNode. The original list 
 * should not be modified.
 * 
 * You should also write a main() routine to test your subroutine.
 */
public class Exercise_9_3 {
  private static final int N = 20; // number of nodes in linked lists
  public static void main(String[] args) {    
    // create initial linked list of integers
    ListNode previous = null; // will serve as a node.next 
    for (int i = 0; i < N; i++){
      ListNode node = new ListNode();
      node.item = i;
      node.next = previous;
      previous = node;
    }
    
    // print the first linked list
    ListNode head = previous;
    while (previous != null) {
      System.out.println(previous.item);
      previous = previous.next;
    }
  
    // create new, reversed linked-list and print it out
    ListNode newHead = returnReversed(head);
    while (newHead != null) {
      System.out.println(newHead.item);
      newHead = newHead.next;
    }
  }
  
  /**
   * Creates new, reversed linked list.
   * @param head will serve as a last element in new linked list.
   * @return head of new linked-list
   */
  private static  ListNode returnReversed(ListNode head) {
    if (head == null)
      throw new NullPointerException("No node reference. - empty list");
  
    // create new, reversed list
    ListNode previous = null;
    for (ListNode runner = head; runner != null; runner = runner.next) {
      ListNode node = new ListNode();
      node.item = runner.item;
      node.next = previous;
      previous = node;
    }
    return previous;
  }
  
  /**
   * Represents a node that will be used in a linked list. 
   */
  private static class ListNode {
    int item;
    ListNode next;
  }
}