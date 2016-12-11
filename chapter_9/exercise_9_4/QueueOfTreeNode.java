import java.util.ArrayList;

/**
 * Represents an implementation of an abstract data structure - queue. 
 */
public class QueueOfTreeNode {
  // ArrayList is used to represent a list of nodes in the queue.
   private ArrayList<TreeNode> nodes = new ArrayList<>(); 

  /**
   * Adds new node to the queue - to the end
   * @param node tree node to be added to the queue
   */
  void enqueue(TreeNode node) {
    if (node == null) // empty object
      throw new NullPointerException();
    // copy the node and add it no the queue
    TreeNode newNode = new TreeNode(node.item);
    newNode.left = node.left;
    newNode.right = node.right;
    nodes.add(newNode);  // add new node to the queue 
  }
  
  /**
   * Returns first element from the queue.
   * @return first tree node
   */
  TreeNode dequeue(){
    if (nodes.size() == 0)
      throw new IllegalStateException("Empty queue.");    
    return nodes.remove(0); // return first node
  }
  
  /**
   * Finds out if the queue is empty.
   * @return true is queue is empty
   */
  boolean isEmpty() {
    return nodes.size() == 0;
  }
}