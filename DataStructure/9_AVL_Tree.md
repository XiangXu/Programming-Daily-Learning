# AVL Tree

AVL Tree is a self-balancing Binary Search Tree where **the difference between heights of left and right subtrees cannot be more than one for all nodes**. 

![AVL](https://media.geeksforgeeks.org/wp-content/cdn-uploads/AVL-Tree1.jpg)

The above tree is AVL because differences between heights of left and right subtrees for every node is less than or equal to 1.

![NO-AVL](https://media.geeksforgeeks.org/wp-content/cdn-uploads/Not-AVL1.jpg)

The above tree is not AVL tree because differences between heights of left and right subtrees for 8 and 12 is greater than 1.

## Why AVL Tree?

Most of the BST operations take O(h) time where h is the height of BST. The cost of these operations may become O(n) for a skwed Binary Search Tree. If we make sure that height of the tree remains O(Logn) after every insertion and deletion, then we can guarantee an upper bound of O(Logn) for all these operations. The height of an AVL tree is always O(Logn) where n is the number of nodes in the tree.

Reference

https://www.geeksforgeeks.org/avl-tree-set-1-insertion/#:~:text=AVL%20tree%20is%20a%20self,than%20or%20equal%20to%201

