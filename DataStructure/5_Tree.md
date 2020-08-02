## Tree

### Introduction

* **Tree**: Unlike Arrays, Linked Lists, Stack and queues, which are linear data structures, trees are hierarchical data structures.

* **Tree Vocabulary(词汇)**: The topmost node is called root of the tree. The elements that are directly under an element are called its children. The element directly above something is called its parent.

### Why Tree

1. One reason to use trees might be because you want to store information that naturally forms a hierarchy.
2. Trees(with some ordering e.g. BST) provide moderate access/search(quicker than linked list and slower than arrays).
3. Trees provide moderate insertion/deletion(quicker than arrays and slower than unordered linked list).
4. Like Linked List and unlike Arrays, Trees don't have an upper limit on number of nodes as nodes are linked using pointers.

### Tree Traversals(Inorder, Preorder and Postorder)

Unlike linear data structures(Array, Linked List, Queues, Stacks, etc) which have only one logical way to traverse them, trees can be traversed in different ways.

1. Depth First Traversals:

* Preorder(Root, Left, Right)
* Inorder(Left, Root, Right) 
* Postorder(Left, Right, Root)

2. Breadth First Search

Reference 

https://www.geeksforgeeks.org/binary-tree-data-structure/#traversals

