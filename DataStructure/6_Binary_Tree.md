# Binary Tree

**A tree whose elements have at most 2 children is called a binary tree**. Since each element in a binary tree can have only 2 children, we typically name them the left and right child.

![binaryTree](https://www.geeksforgeeks.org/wp-content/uploads/binary-tree-to-DLL.png)

A Binary Tree contains following parts.
1. Data
2. Pointer to left child
3. Pointer to right child

## Types of Binary Tree

* **Full Binary Tree**: A Binary Tree is a full Binary Tree if **every node has 0 or 2 children**.

* **Complete Binary Tree**: A Binary Tree is a complete Binary Tree if **all levels are completely filled except possibily that the last level and the last level has all keys as left as possible**.

* **Perfect Binary Tree**: A Binary Tree is a Perfect Binary Tree in which **all the internal nodes have two children and all left nodes are at same level**.

* **Balanced Binary Tree**: A Binary Tree is balanced if **the height of the tree is O(logn) where n is the number of nodes**.

* **A degenerate(恶化) (or pathological) tree**: A Tree where every internal node has one child. Such trees are performance-wise same as linked list.

Reference

https://www.geeksforgeeks.org/binary-tree-data-structure/#traversals