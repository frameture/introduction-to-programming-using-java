// depends on TextIO Class

/**
 * Represents a binary sort tree structure.
 */
public class BinaryTree {
  public TreeNode root; // root node of the BinaryTree instance

  /**
   * Counts the number of leaves in the tree.
   * @param node Current node under recursion.
   * @return Number of leaves in the tree.
   */
  public int countLeaves(TreeNode node) {
    if (node == null)
      return 0; // node is empty
    else if (node.left == null && node.right == null) 
      return 1; // we have found a leaf
     else 
      return countLeaves(node.left) + countLeaves(node.right);
  }
  
  /**
   * Finds the maximal depth in the given tree.
   * @param node Current node under recursion
   * @param depth Depth of the current node.
   * @return 
   */
  public int maxDepth(TreeNode node, int depth) {
    if (node == null) 
      return 0;
    else if (node.left == null && node.right == null)
      return depth; // we got to the leaf
    else {
      int left = maxDepth(node.left, depth + 1);
      int right = maxDepth(node.right, depth + 1);
      return left > right ? left : right;
    }
  }

  /**
   * Calculates the sum of depths of all leaves in the tree.
   * @param node Current node under recursion.
   * @param depth Depth of the current node.
   * @return Sum of depths of all leaves.
   */
  public int depthSum(TreeNode node, int depth) {
    if (node == null)
      return 0;
    else if (node.left == null && node.right == null) 
      return depth; // we got to the leaf.
    else            // else we are somewhere above in the tree
      return depthSum(node.left, depth + 1) + depthSum(node.right, depth + 1);
  }

  /**
   * Determines if given item is inside the given tree.
   * @param root Root node of the tree.
   * @param item Item to be checked whether is inside the tree.
   * @return true if the tree contains the item.
   */
  public boolean treeContains(TreeNode root, String item) {
    if (root == null) // empty tree
      return false;
    else if (root.item.equals(item))         // the item is in the root
      return true;
    else if (root.item.compareTo(item) <= 0) // has to be in the right
                            // subtree
      return treeContains(root.right, item);
    else
      return treeContains(root.left, item); // else in the left subtree
  }

  /**
   * Inserts new node to the BinaryTree object. New node is inserted in sorted
   * structure.
   * 
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
   * Recursively prints the node's descendants' values to the output. We use
   * here the inpost method which prints the items in ascending order.
   * @param node Root of the binary tree, which descendants' values will be
   *             printed to the default destination.
   */
  public void treeList(TreeNode node) {
    if (node != null) {
      treeList(node.left);  // print items from the left subtree.
      TextIO.putln("   " + node.item); // actual printing
      treeList(node.right); // print items from the right subtree.
    }
  }
}
