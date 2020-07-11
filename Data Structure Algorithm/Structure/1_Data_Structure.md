# Data Structure

## Introduction

**Data Structure can be defined as the group of data elements which provides an efficient way of sorting and organising data int the computer so that it can be used efficiently**. Some examples of Data Structures are arrays, Linked List, Stack, Queue, etc. Data Structures are widely used in almost every aspect of Computer Science i.e. Operating System, Compiler Design, Artifical intelligence, Graphics and may more.

Data Structures are the main part of many computer science algorithms as they enable the programmers to handle the data in an efficient way. It plays a vitle role in enhancing the performance of a software or a program as the main function of the software is to store and retrive the uers's data as fast as possible.

## Basic Terminology

Data structure are building blocks of any program of the software. Choosing the appropriate data structure for a program is the most difficult task for a programmer. Following terminology is used as far as data structure are concerned.

* **Data**: Data can be defined as an elementary value or the collection of values, for example, sutudent's name and its id are the data about the student.

* **Group Items**:  Data items which have subordinate data items are called Group Items, for example, name of a student can have first name and second name.

* **Record**: Record can be defiend as the collection of various data items, for example, if we talk about the student entity, then its name, address, course and marks can be grouped together to form the record for the student.

* **File**: A file is a collection of various records of one type of entity, for example, if there are 60 employees in the class, then there will be 20 records in the related file where each record contains the data about each employee.

**Attribute and Entity**: An entity represents the class of certain objects. It contains various attributes. Each attribute represents the particular property of that entity.

**Field**: Field is a single elementary unit of information representing the attribute of an entity.

## Need of Data Structures

As application are getting complex and amount of data is increasing day by day, there may arrise the following problems:

* **Processing Speed**: To handle very large amount of data, high speed processing is required, but as the data is growing day by day to the billions of files per entity, processor may failed to deal with that much amount of data.

* **Data Search**: Consider an inventory(存货, 清单) size of 106 items in a store, if our application needs to search for a particular item, it needs to traverse 106 items every time, results is slowing down the search process.

* **Multiple Requests**: If thousands of users are searching the data simultaneously on a web server, then there are the chances that a very large server can be failed during the process.

In order to solve the above problems, data structures are used. Data is organized to form a data structure in such a way that all items are not required to be searched and required data can be searched instantly.

## Advantages of Data Structures

* **Efficiency**: Efficiency of a program depends upon the choice of data structures. For example: suppose, we have some data and we need to perform the search for a particular record. In this case, if we organize our data in an array, we will have to search sequentially by element. Hence, using array may not be very efficient here. There are better data structures which can make the search process efficient like ordered array, binary search tree or hash tables.

* **Reusability**: Data structures are reusable, i.e. once we have implemented a particular data structure, we can use it at any other place. Implementation of data structures can be compiled into libraries which can be used by different clients.

* **Abstraction**: Data structure is specified by the ADT which provides a level of abstraction. The client program uses the data structure through interface only, without getting into the implementation details.

## Data Structure Classification

![alt text](https://static.javatpoint.com/ds/images/ds-introduction.png)

### Linear Data Structure

A data structure is called linear if all of its elements are arranged in the linear order. In linear data structures, the elements are stored in no-hierarchical way where each elements has the successors and predecessors except the first and last element.

Types of Linear Data Structures are given below:

* **Arrays**: An array is a collection of similar type of data items and each data item is called an element of the array. The data type of the element may be any valid data type like char, int, float or double. The elements of array share the same variables name but each one carries a different index number known as subscript. The array can be one dimensional, two dimensional or multidimensional.

* **Linked List**: Linked list is a linear data structure which is used to maintain a list in the memory. It can be seen as the collections of nodes stored at non-contigous memory locations. Each node of the list contains a pointer to its adjacent node.

* **Stack**: Stack is a linear list in which insertions and deletions are allowed only at one end, called **top**. A stack is an abstract data type, can be implemented in most of the programming languages. It is named as stack because it behaves like a real world stack, for example: piles of plates or deck o cards etc.

* **Queue**: Queue is a liner list in which elements can be inserted only at one end called **rear** and deleted only at another end called **front**. It is an abstract data structure, similar to stack. Queue is opend at both end therefore it follows First-In-First-Out(FIFO) methodology for storing the data items.

### Non Linear Data Structure

This data structure does not form a sequence. Each item or element is connected with two or more other items in a non-linear arrangement. The data elements are not arranged in sequential structure.

Types of Non Linear Data Structures are given below:

* **Trees**: Trees are multilevel data structures with hierarchical relationship among its elements known as nodes. The bottommost nodes in the hierarchical are called **leaf node** while the topmost node is called **root node**. Each node contains pointers to point adjacent nodes. Tree data structure is based on the parent-child relationship among the nodes. Each node in the tree can have more than on children except the leaf nodes whereas each node can have almost one parent except the root node.

* **Graphs**: Graphs can be defined as the pictorial(有插图的) of the set of elements(represented by vertices) connected by the links known as edges. A graph is different from the tree sense that a graph can have cycle while the tree cannot have the one.

## Operations on Data Structure

1. **Traversing**: Every data structure contains the set of data elements. Traversing the data structure means visiting each element of the data structure in order to perform some specific operations like searching or sorting.

2. **Insertion**: Insertion can be defined as the process of adding the elements to the data structure at any locaiton. If the size of data structure is n then we can only insert n-1 data elements into it.

3. **Deletion**: The process of removing an element from the data structure is called Deletion. We can delete an element from the data structure at any random location.

4. **Searching**: The process of finding the location of an element within the data structure is called Searching. There are two algorithms to perform searching, Linear search and Binary Search.

5. **Sorting**: The process of arranging the data structure in a specific order is known as Sorting. 

6. **Merging**: When two lists List A and List B of size M and N respectively, or similar type of elements, clubbed or joined to produce the third list, then this process is called merging.



Reference

https://www.javatpoint.com/data-structure-introduction




