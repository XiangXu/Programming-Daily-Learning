# Red-Black Tree

Red-Black Tree is a self-balancing Binary Search Tree(BST) where every node follows rules.

1. Every node has a color either red or black.
2. Root of tree is always black.
3. There are not two adjacent red nodes(A red node cannot have a red parent or red child).
4. Every path from a node(including root) to any of its descendant NULL node has the same number of black nodes.

![RBT](https://www.geeksforgeeks.org/wp-content/uploads/RedBlackTree.png)

## Why Red-Black Tree?

Most of the BST operations(e.g., search, max, min, insert, delete..etc) take o(h) time where h is the height of the BST. The cost of these operations may become O(n) for a skewed(歪曲的) Binary tree. If we make sure that height of the tree remains O(Logn) after every insertion and deletion, then we can guarantee an upper bound of O(Logn) for all these operations. The height of a Red-Black tree is always O(Logn) where n is the number of nodes in the tree.

## Compare with AVL Tree

**The AVL Trees are more balanced compared to Red-Black Trees**, but they my cause more rotations during the insertion and deletion. So if your application involves many frequent insertions and deletions, then Red Black trees should be preferred. And if the insertions and deletions are less frequent and search is a more frequent operation, then AVL tree should be preferred over Red-Black Tree.

## How does a Red-Black Tree ensure balance?

![example](/DataStructure/Images/example1.png)

Reference

https://www.geeksforgeeks.org/red-black-tree-set-1-introduction-2/