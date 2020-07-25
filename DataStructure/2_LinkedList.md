# Linked List

A linked list is a linear data structure, in which **the elements are not stored at contiguous memory locations**. The elements in a linked list are linked using pointers. In simple words, a linked list consists of nodes where each node contains a data field and a reference to the next node in the list.

![linkedlist](https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2013/03/Linkedlist.png)

Like arrays, Linked List is a linear data structure. Unlike arrays, linked list elements are not stored at a contiguous location, the elements are linked using pointers.

## Why Linked List?

Arrays can be used to store linear data of similar types, but arrays have the following limitations.

1. The size of the arrays is fixed. So we must know the upper limit on the number of elements in advance. Also, generally, the allocated memory is equal to the upper limit irrespective of the us-age.
2. Inserting a new element in an array of elements is expensive because the room has to be created for the new elements and to create room existing elements have to be shifted.

### Advantages over arrays

1. Dynamic size.
2. Ease of inertion / deletion.

### Drawbacks

1. Random access is not allowed. We have to access elements sequentially starting from the first node. So we **cannot do binary search with linked lists efficiently with its default implementation**.
2. Extra memeory space for a pointer is required with each element of the list.
3. Not cache friendly. Since array elements are contiguous locations, there is locality of reference which is not there in case of linked lists.

## Circular Linked List

**Circular Linked List is a linked list where all nodes are connected to form a circle**. There is no NULL at the end. A circular linked list can be a singly circular linked or doubly circular linked list.

![circularLinkedList](https://media.geeksforgeeks.org/wp-content/uploads/CircularLinkeList.png)

## Doubly Linked List

**A Doubly Linked List contains an extra pointer, typically called previous pointer, together with the next pointer and data which are there in singly linked list**.

![doublyLinkedList](https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2014/03/DLL1.png)

Reference

https://www.geeksforgeeks.org/data-structures/linked-list/