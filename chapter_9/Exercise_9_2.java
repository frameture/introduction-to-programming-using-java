// depends on TextIO Class

/**
 * Exercise 7.7 asked you to read a file, make an alphabetical list of all the
 * words that occur in the file, and write the list to another file. In that
 * exercise, you were asked to use an ArrayList<String> to store the words.
 * Write a new version of the same program that stores the words in a binary
 * sort tree instead of in an arraylist.
 *
 */
public class Exercise_9_2 {

  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree(); // data structure for the words
    TextIO.readUserSelectedFile(); // open fileChooser --- user selects file
                                   // to be opened
    String word; // current word
    while (true) {
      word = readNextWord(); // read next word
      if (word == null)
        break;    // no word --- break the loop
      word = word.toLowerCase();
      if (tree.treeContains(tree.root, word))
        continue; // duplicate --- omit it
      tree.treeInsert(word); // add current word to the list
    }
    TextIO.writeUserSelectedFile();
    tree.treeList(tree.root);
  }

  /**
   * Nested class, represents a binary sort tree structure.
   */
  private static class BinaryTree {
    TreeNode root;

    private boolean treeContains(TreeNode root, String item) {
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
    private void treeInsert(String newItem) {
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
    private int countNodes(TreeNode root) {
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
    private void treeList(TreeNode node) {
      if (node != null) {
        treeList(node.left);             // print items from the left subtree.
        TextIO.putln("   " + node.item); // actual printing
        treeList(node.right);            // print items from the right subtree.
      }
    }

    /**
     * Represents a single node used in the linked-object structure
     */
    private class TreeNode {
      String item;          // value that will be stored in the node
      TreeNode left, right; // references to the contiguous nodes
      
      /**
       * TreeNode constructor
       */
      public TreeNode(String item) {
        this.item = item;
      }
    }
  }

  /**
   * Read the next word from TextIO, if there is one. First, skip past any
   * non-letters in the input. If an end-of-file is encountered before a word
   * is found, return null. Otherwise, read and return the word. A word is
   * defined as a sequence of letters. Also, a word can include an apostrophe
   * if the apostrophe is surrounded by letters on each side.
   * 
   * @return the next word from TextIO, or null if an end-of-file is
   *         encountered
   */
  private static String readNextWord() {
    char ch = TextIO.peek(); // Look at next character in input.
    while (ch != TextIO.EOF && !Character.isLetter(ch)) {
      // Skip past non-letters.
      TextIO.getAnyChar(); // Read the character.
      ch = TextIO.peek(); // Look at the next character.
    }
    if (ch == TextIO.EOF) // Encountered end-of-file
      return null;
    // At this point, we know the next character is a letter, so read a
    // word.
    String word = ""; // This will be the word that is read.
    while (true) {
      word += TextIO.getAnyChar(); // Append the letter onto word.
      ch = TextIO.peek(); // Look at next character.

      if (ch == '\'') {
        // The next character is an apostrophe. Read it, and
        // if the following character is a letter, add both the
        // apostrophe and the letter onto the word and continue
        // reading the word. If the character after the apostrophe
        // is not a letter, the word is done, so break out of the loop.
        TextIO.getAnyChar(); // Read the apostrophe.
        ch = TextIO.peek(); // Look at char that follows apostrophe.
        if (Character.isLetter(ch)) {
          word += '\'' + TextIO.getAnyChar();
          ch = TextIO.peek(); // Look at next char.
        } else
          break;
      }
      if (!Character.isLetter(ch)) {
        // If the next character is not a letter, the word is
        // finished, so break out of the loop.
        break;
      }
      // If we haven’t broken out of the loop, next char is a letter.
    }
    return word; // Return the word that has been read.
  }
}
