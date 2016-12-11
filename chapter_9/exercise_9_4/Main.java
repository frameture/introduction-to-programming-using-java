public class Main {
  private static final int N = 5; // number of elements in the binary tree

  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree(); // binary tree
    
    // create the binary tree of random strings
    for (int i = 0; i < N; i++) {
      String item =  "" + (int) (Math.random() * 10);
      tree.treeInsert(item);
    }
    System.out.println("Binary tree elements:");
    tree.treeList(tree.root);
    System.out.println("---------------------");
    
    traverseTreeWithQueue(tree.root);
  }
  
  /**
   * Prints the values of binary tree nodes'. Method is not recursive.
   * @param root Root element from the tree desired to be traversed through
   *        and its content printed. 
   */
  private static void traverseTreeWithQueue(TreeNode root) {
    QueueOfTreeNode queue = new QueueOfTreeNode();
    
    System.out.println("Traversing the tree using queue. Elements:");
    queue.enqueue(root); // add root to the queue
    // traverse through the tree
    while (!queue.isEmpty()) {
      TreeNode node = queue.dequeue();
      System.out.println(node.item);
      if (node.left != null)
        queue.enqueue(node.left);
      if (node.right != null)
        queue.enqueue(node.right);
    }
  }
}
