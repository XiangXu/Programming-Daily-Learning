# Queue Data Structure

A Queue is a linear structure which follows a particular order in which the operations are performed. **The order is First In First Out**. A good example of a queue is any queue of consumers for a resource where the consumer that came first is served first. The difference between stacks and queue is removing. **In stack we remove the item the most recently added; In a queue, we remove the item the least recently added**.

## Operations on Queue

* **Enqueue**: Adds an item to the queue. If the queue is full, then it is said to be Overflow condition.
* **Dequeue**: Removes an item from the queue. The items are popped in the same order which they are pushed. If the queue is empty, then it is said to be an Underflow condition.
* **Front**: Get the front item from queue.
* **Rear**: Get the last item from queue.

## Priority Queue In Java

A PriorityQueue is used when objects are supposed to be processed based on the priority. It is known that a queue follows First-In-First-Out algorithm, but sometimes t**he elements of the queue are needed to be processed according to the priority**, that's when the PriorityQueue comes into play. **The PriorityQueue is based on the priority heap**. The elements of the priority queue are ordered according to the natural ordering, or by a Comparator provided at queue construction time, depending on which constructor is used.

### Basic Operations on PriorityQueue:

* **boolean add(E element)**: This method inserts the specified element into this priority queue.
* **public peek()**: This method retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
* **public poll()**: This method retrieves and removes the head of this queue, or returns null if this queue is empty.
* **public remove(E element)**: This method removes a single instance of the specified element from this queue, if it is pressent. 
* **boolean contains(Object o)**: This method returns true if this queue contains the specified element.
* **boolean offer(E e)**: This method is used to insert a specific element into the priority queue.
* **int size()**: The method is used to return the number of elements present in the set.
* **toArray()**: This method is used to return an array containing all of the elements in this queue.

## Deque

Deque or Double Ended Queue is a generalized version of Queue data structure that **allows insert and delete at both ends**. Since Deque supports both stack and queue operations, it can be used as both.

### Operations on Deque

* **insertFront()**: Adds an item at the front of Deque.
* **insertLast()**: Adds an item at the rear of Deque.
* **deleteFront()**: Deletes an item from front of Deque.
* **deleteLast()**: Deletes an item from rear of Deque.
* **getFront()**: Get the front item from queue.
* **getRear()**: Geht the last item from queue.
* **isEmpty()**: Checks whether Deque is empty or not.
* **isFull()**: Checks whether Deque is full or not.

```java
Deque<String> deque = new LinkedList<String>(); 
// Add at the last 
deque.add("Element 1 (Tail)"); 
  
// Add at the first 
deque.addFirst("Element 2 (Head)"); 
  
// Add at the last 
deque.addLast("Element 3 (Tail)"); 
  
// Add at the first 
deque.push("Element 4 (Head)"); 
  
// Add at the last 
deque.offer("Element 5 (Tail)"); 

// Add at the first 
deque.offerFirst("Element 6 (Head)"); 

System.out.println(deque + "\n"); 

// We can remove the first element 
// or the last element. 
deque.removeFirst(); 
deque.removeLast(); 
```

## Circular Queue

Circular Queue is a linear data structure in which operations are performed based on FIFO principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

![circularQueue](https://media.geeksforgeeks.org/wp-content/uploads/Circular-queue.png)

## Queue Interface In Java

The Queue interface present in the java.util package and extends the Collection interface is used to hold the elements about to be processed in FIFO order.

### Classes which implement the Queue Interface

* **PriorityQueue**

* **LinkedList**: LinkedList is a class which is implemented in the collection framework which in herenly implements the linked list data structure. It is a linear data structure where **the elements are not stored in contiguous locations and every element is seperate object with a data part and address part**. The elements are linked using pointers and addresses. Each element is known as node. Due to the dynamicity and ease of insertions and deletions, they are preferred over the arrays or queues.

* **PriorityBlockingQueue**: It is to be noted that both the implementations, **the PriorityQueue and LinkedList are not thread-safe**.** PriorityBlockingQueue is one alternative implementation if thread-safe implementation is needed**. **PriorityBlockingQueue is an unbounded blocking queue** that uses the same ordering rules as class PriorityQueue and supplies blocking retrieval operations.Since it is unbounded, adding elements may sometimes fail due to resource exhaustion resulting in OutOfMemoryError.



Reference

https://www.geeksforgeeks.org/queue-data-structure/


