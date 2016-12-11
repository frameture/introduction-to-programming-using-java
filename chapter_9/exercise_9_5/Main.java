public class Exercise_9_5 {
  private static final int N = 1023; // number of nodes in the tree
  
  public static void main(String[] args) {
     BinaryTree tree = new BinaryTree();
    for (int i = 0; i < N; i++) // creating binary tree with random strings
      tree.treeInsert("" + Math.random());
    
    int leaves = tree.countLeaves(tree.root);   // count the leaves
    int depthSum = tree.depthSum(tree.root, 0); // find the sum of all depths
    int maxDepth = tree.maxDepth(tree.root, 0); // find the maximal depth
    
    // see whether the binary sort tree is nicely balanced
    printStats(leaves, depthSum, maxDepth);     
  }

  /**
   * Prints the data to the Command Line.
   * @param leaves
   * @param depthSum
   * @param maxDepth
   */
  private static void printStats(int leaves, int depthSum, int maxDepth) {
    System.out.println("-----------------------------------------------");
    System.out.println("  There are " + leaves + " leaves in the tree");
    System.out.println("-----------------------------------------------");
    System.out.println("  Average depth is " + depthSum / leaves );
    System.out.println("-----------------------------------------------");
    System.out.println("  Max depth is " + maxDepth);
  }
}
