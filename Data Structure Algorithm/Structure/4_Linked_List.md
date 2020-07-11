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

## Operations on Singly Linked List

### Insertion

The insertion into a singly linked list can be performed at different positions. Based on the position of the new node being inserted, the insertion is categorized into the following categories.

#### Insertion in singly linked list at beginning

1. Allocate the space for the new node and store data into the data part of the node. This will be done by the following statements. 
```
ptr = (struct node *) malloc(sizeof(struct node *));  
ptr → data = item 
```

2. Make the link part of the new node pointing to the existing first node of the list. This will be done by using the following statement.
```
ptr->next = head;  
```

3. At the last, we need to make the new node as the first node of the list this will be done by using the following statement.
```
head = ptr;
```

#### Insertion in singly linked list at the end

In order to insert node at the last, there are two following scenarios which need to be implemented.

1. The node is being added to an empty list.
2. The node is being added to the end of the linked list.

In the first case:

1. The condition (head == NULL) gets satisfied. Hence, we just need to allocate the space for the node by using malloc statement in C. Data and the link part of the node are set up by using the following statements.
```
ptr->data = item;  
ptr -> next = NULL;  
```

2. Since, ptr is the only node that will be inserted in the list hence, we need to make this node pointed by the head pointer of the list. This will be done by using the following Statements.
```
Head = ptr;
```

In the second case:

1. The condition Head = NULL would fail, since Head is not null. Now, we need to declare a temporary pointer temp in order to traverse through the list. temp is made to point the first node of the list.
```
Temp = head;
```

2. Then, traverse through the entire linked list using the statements:
```
while (temp→ next != NULL)  
    temp = temp → next;  
```

3. At the end of the loop, the temp will be pointing to the last node of the list. Now, allocate the space for the new node, and assign the item to its data part. Since, the new node is going to be the last node of the list hence, the next part of this node needs to be pointing to the null. We need to make the next part of the temp node (which is currently the last node of the list) point to the new node (ptr).
```
temp = head;  
while (temp -> next != NULL)  
{  
    temp = temp -> next;  
}  
temp->next = ptr;  
ptr->next = NULL;  
```

#### Insertion in singly linked list after specified Node

1. In order to insert an element after the specified number of nodes into the linked list, we need to skip the desired number of elements in the list to move the pointer at the position after which the node will be inserted. This will be done by using the following statements.
```
temp=head;  
for(i=0;i<loc;i++)  
{  
    temp = temp->next;  
    if(temp == NULL)  
    {  
        return;  
    }  
    
}
```

2. Allocate the space for the new node and add the item to the data part of it. This will be done by using the following statements.
```
ptr = (struct node *) malloc (sizeof(struct node));  
ptr->data = item;  
```

3. Now, we just need to make a few more link adjustments and our node at will be inserted at the specified position. Since, at the end of the loop, the loop pointer temp would be pointing to the node after which the new node will be inserted. Therefore, the next part of the new node ptr must contain the address of the next part of the temp (since, ptr will be in between temp and the next of the temp). This will be done by using the following statements.
```
ptr→ next = temp → next   
temp ->next = ptr;   
```

### Deletion and Traversing

#### Deletion in singly linked list at beginning
```
ptr = head;  
head = ptr->next;  
```

#### Deletion in singly linked list at the end

There are two scenarios in which, a node is deleted from the end of the linked list.

1. There is only one node in the list and that needs to be deleted.
2. There are more than one node in the list and the last node of the list will be deleted.

In the first case:

the condition head → next = NULL will survive and therefore, the only node head of the list will be assigned to null. This will be done by using the following statements.
```
ptr = head  
head = NULL  
free(ptr)  
```

In the second case:

The condition head → next = NULL would fail and therefore, we have to traverse the node in order to reach the last node of the list.

For this purpose, just declare a temporary pointer temp and assign it to head of the list. We also need to keep track of the second last node of the list. For this purpose, two pointers ptr and ptr1 will be used where ptr will point to the last node and ptr1 will point to the second last node of the list.

```
ptr = head;   
while(ptr != NULL)  
{  
    ptr1 = ptr;  
    ptr = ptr ->next;  
}  
ptr1->next = NULL;  
free(ptr);  
```

#### Deletion in singly linked list after the specified node

In order to delete the node, which is present after the specified node, we need to skip the desired number of nodes to reach the node after which the node will be deleted. We need to keep track of the two nodes. The one which is to be deleted the other one if the node which is present before that node. For this purpose, two pointers are used: ptr and ptr1.
```
ptr=head;  
for(i=0;i<loc;i++)  
{  
    ptr1 = ptr;       
    ptr = ptr->next;  
        
    if(ptr == NULL)  
    {  
        printf("\nThere are less than %d elements in the list..",loc);  
        return;  
    }  
}  
```

Now, our task is almost done, we just need to make a few pointer adjustments. Make the next of ptr1 (points to the specified node) point to the next of ptr (the node which is to be deleted).
```
ptr1 ->next = ptr ->next;  
free(ptr);  
```

#### Traversing in singly linked list

Traversing is the most common operation that is performed in almost every scenario of singly linked list. Traversing means visiting each node of the list once in order to perform some operation on that. This will be done by using the following statements.

```
ptr = head;   
while (ptr!=NULL)  
{  
    ptr = ptr -> next;  
}  
```

####  Searching in singly linked list

Searching is performed in order to find the location of a particular element in the list. Searching any element in the list needs traversing through the list and make the comparison of every element of the list with the specified element. If the element is matched with any of the list element then the location of the element is returned from the function.



Reference

https://www.javatpoint.com/singly-linked-list