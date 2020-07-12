# Linked List

* Linked List can be defined as collection of objects called nodes that are randomly stored in the memory.
* A node contains two fields, data stored at that particular address and the pointer which contains the address of the next node in the memory.
* The last node of the list contains pointer to the null.

![alt text](https://static.javatpoint.com/ds/images/linked-list.png)

## Uses of Linked List

* **The list is not required to be contiguously present in the memory**. The node can reside any where in the memory and linkd together to make a list. This achieves optimized utilization of space.
* List size is limited to the memory size and doesn't need to be declared in advance.
* Empty node cannot be present in the linked list.
* We can store value of primitive types or objects in the singly linked list.

## Why we linked list over array?

Till now, we were using array data structure to organize the group of elements that are to be stored individually in the memory. However, Array has several advantages and disadvantages which must be known in order to decide the data structure which will be used throughout the program.

Array contains following limitations:

1. The size of arry must be known in advance before using it in the program.
2. Increasing size of the array is a time taking process. It is almost impossible to expand the size of array at run time.
3. All the elements in the array need to be contiguously stored in the memory. Inserting any element in the array needs shifting of all its predecessors.

Linked list is the data structure which can overcome all the limitations of an array. Using linked list is useful because,

1. It allocates the memory dynamically. All the nodes of linked list are non-contiguously stored in the memory and linked together with the help of pointers.
2. Sizing is no longer a problem since we do not need to define its size at the time of declaration. List grows as per the program's demand and limited to the available memory space.

## Singly linked list or One way chain

**Singly linked list can be defined as the collection of ordered set of elements**. The number of elements may vary according to the need of the program. A node in the singly linked list consist of two parts: data part and link part. Data part of the node stores actual information that is to be represented by the node while the link part of the node stores the address of its immediate successor.

One way chain or singly linked list can be traversed only in one direction. In other words, we can say that each node contains only next pointer, therefore we can not traverse the list in the reverse direction.

## Complexity 

| Algorithm | Average Case | Worst Case |
|-----------|--------------|------------|
| Access    | O(n)         | O(n)       |
| Search    | O(n)         | O(n)       |
| Insertion | O(1)         | O(1)       |
| Deletion  | O(1)         | O(1)       |

Space Complexity: O(n).


Reference

https://www.javatpoint.com/singly-linked-list