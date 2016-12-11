     Subsection 9.4.1 explains how to use recursion to print out the items in 
     a binary tree in various orders. That section also notes that a non-recursive 
     subroutine can be used to print the items, provided that a stack or queue is 
     used as an auxiliary data structure. Assuming that a queue is used, here is 
     an algorithm for such a subroutine:

            Add the root node to an empty queue
            while the queue is not empty:
                Get a node from the queue
                Print the item in the node
                if node.left is not null:
                    add it to the queue
                if node.right is not null:
                    add it to the queue

    Write a subroutine that implements this algorithm, and write a program to test 
    the subroutine. Note that you will need a queue of TreeNodes, so you will need 
    to write a class to represent such queues.

    (Note that the order in which items are printed by this algorithm is different 
    from all three of the orders considered in Subsection 9.4.1.)
