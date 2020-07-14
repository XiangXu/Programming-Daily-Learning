# Linked list implementation of stack

Instead of using array, we can also use linked list to implement stack. Linked list locates the memory drnamically. However, time complexity in both scenario is same for all the operations i.e. push, pop and peek.

In linked list implementation of stack, the nodes are maintained non-contiguously in the memory. Each node contains a pointer to its immediate successor node in the stack. Stack is said to be overflown if the space left in the memory heap is not enough to create a node.

```java
public class LinkedListStack
{
    private class Node
    {
        int data;
        Node link;
    }

    private Node top;

    public LinkedListStack()
    {
        this.top = null;
    }

    public boolean isEmpty()
    {
        return (top == null);
    }

    public void push(int x)
    {
        Node temp = new Node();

        // check if stack (heap) is full. Then inserting an
        //  element would lead to stack overflow
        if (temp == null) {
            System.out.print("\nHeap Overflow");
            return;
        }

        temp.data = x;
        temp.link = top;
        top = temp;
    }

    public int peek()
    {
        // check for empty stack
        if (!isEmpty()) {
            return top.data;
        }
        else {
            System.out.println("Stack is empty");
            return 0;
        }
    }

    public int pop() // remove at the beginning
    {
        // check for stack underflow
        if (top == null) {
            System.out.print("\nStack Underflow");
            return 0;
        }

        // update the top pointer to point to the next node
        int data = top.data;
        top = (top).link;

        return data;
    }
}
```

Reference

https://www.javatpoint.com/ds-linked-list-implementation-of-stack