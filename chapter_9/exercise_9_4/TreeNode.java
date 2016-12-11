/**
 * Represents a single node used in the linked-object structure
 */
public class TreeNode {
  String item;          // value that will be stored in the node
  TreeNode left, right; // references to the contiguous nodes
  
  /**
   * TreeNode constructor
   */
  public TreeNode(String item) {
    this.item = item;
  }
}
