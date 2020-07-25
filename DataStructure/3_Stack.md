# Stack Data Structure

Stack is a linear data structure which follows a particular order in which the operations are preformed. The order may be LIFO(Last In First Out) or FILO(First In Last Out).

Mainly the following three basic operations are performed in the stack:

* **Push**: Adds an item in the stack. If the stack is full, then it is said to be an Overflow condition.
* **Pop**: Removes an item from the stack. The items are popped in the reversed order in which they are pushed. If the stack is empty, the it is said to be a Underflow condition.
* **Peek or Top**: Returns top element of stack.
* **isEmpty**: Returns true if stack is empty, else false.

**push(), pop(), isEmpty() and peek() all take O(1) time**. We do not run any loop in any of these operations.

## Stack class in Java

```java
Stack<Integer> stack = new Stack<Integer>();
```

Method in Stack class:

1. **Object push(Object element)**: Pushes an element on the top of the stack.
2. **Object pop()**: Removes and returns the top element of the stack. An ‘EmptyStackException’ exception is thrown if we call pop() when the invoking stack is empty.
3. **Object peek()**: Returns the element on the top of the stack, but does not remove it.
4. **boolean empty()** : It returns true if nothing is on the top of the stack. Else, returns false.
5. **int search(Object element)** : It determines whether an object exists in the stack. If the element is found, it returns the position of the element from the top of the stack. Else, it returns -1.

Reference

https://www.geeksforgeeks.org/stack-data-structure/#operations