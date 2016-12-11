     In Subsection 9.4.2, I say that "if the [binary sort] tree is created by 
	 inserting items in a random order, there is a high probability that the 
	 tree is approximately balanced." For this exercise, you will do an 
	 experiment to test whether that is true.

     The depth of a node in a binary tree is the length of the path from the 
	 root of the tree to that node. That is, the root has depth 0, its children 
	 have depth 1, its grandchildren have depth 2, and so on. In a balanced 
	 tree, all the leaves in the tree are about the same depth. For example, in 
	 a perfectly balanced tree with 1023 nodes, all the leaves are at depth 9. 
	 In an approximately balanced tree with 1023 nodes, the average depth of all 
	 the leaves should be not too much bigger than 9.

     On the other hand, even if the tree is approximately balanced, there might 
	 be a few leaves that have much larger depth than the average, so we might 
	 also want to look at the maximum depth among all the leaves in a tree.

     For this exercise, you should create a random binary sort tree with 1023 
	 nodes. The items in the tree can be real numbers, and you can create the 
	 tree by generating 1023 random real numbers and inserting them into the 
	 tree, using the usual treeInsert() method for binary sort trees. Once you 
	 have the tree, you should compute and output the average depth of all the 
	 leaves in the tree and the maximum depth of all the leaves. To do this, you 
	 will need three recursive subroutines: one to count the leaves, one to find 
	 the sum of the depths of all the leaves, and one to find the maximum depth. 
	 The latter two subroutines should have an int-valued parameter, depth, that 
	 tells how deep in the tree you've gone. When you call this routine from the 
	 main program, the depth parameter is 0; when you call the routine recursively, 
	 the parameter increases by 1.
