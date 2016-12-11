// depends on TextIO Class.

/**
 * Nested class, represents a binary sort tree structure.
 */
public class BinaryTree {
  TreeNode root; // root element

  public boolean treeContains(TreeNode root, String item) {
    if (root == null) // empty tree
      return false;
    else if (root.item.equals(item))         // the item is in the root
      return true;
    else if (root.item.compareTo(item) <= 0) // has to be in the right subtree
      return treeContains(root.right, item);
    else
      return treeContains(root.left, item);  // else in the left subtree
  }

  /**
   * Inserts new node to the BinaryTree object. New node is inserted in
   * sorted structure.
   * @param newItem value to be stored in the new node
   */
  public void treeInsert(String newItem) {
    TreeNode runner = root;

    if (newItem == null)
      throw new NullPointerException("The item is 'null'");
    if (runner == null) {
      // empty tree - add root
      root = new TreeNode(newItem);
      return;
    }

    while (true) {
      if (newItem.compareTo(runner.item) < 0) {
        if (runner.left == null) {
          runner.left = new TreeNode(newItem);
          return; // new item has been added to the tree
        } else
          runner = runner.left;
      } else {
        if (runner.right == null) {
          runner.right = new TreeNode(newItem);
          return; // new item has been added to the tree
        } else
          runner = runner.right;
      }
    } 
  }

  /**
   * Counts all nodes in given binary tree structure.
   * @param root Root node which will serve as starting point for counting.
   * @return
   */
  public int countNodes(TreeNode root) {
    if (root == null)
      return 0;
    else // count recursively nodes in the left and right subtrees
      return 1 + countNodes(root.left) + countNodes(root.right);
  }

  /**
   * Recursively prints the node's descendants' values to the output
   * @param node Root of the binary tree, which descendants' values will
   *             be printed to the default destination.  
   */
  public void treeList(TreeNode node) {
    if (node != null) {
      treeList(node.left);             // print items from the left subtree.
      TextIO.putln("   " + node.item); // actual printing
      treeList(node.right);            // print items from the right subtree.
    }
  }
}
