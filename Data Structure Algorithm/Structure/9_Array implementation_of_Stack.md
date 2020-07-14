# Array implementation of Stack

In array implementation, the stack is formed by using the array. All the operations regarding the stack are performed using arrays. Let's see how each operation can be implemented on the stack using array data structure.

```java
public class ArrayStack
{
   private static final int MAX = 100;
   private int top;
   private int a[] = new int[MAX];

    public boolean isEmpty()
    {
        return top < 0;
    }

    public ArrayStack()
    {
        top = -1;
    }

    public boolean push(int x)
    {
        if(top > MAX-1)
        {
            System.out.println("Stack Overflow");
            return false;
        }
        else
        {
            a[++top] = x;
            System.out.println(x + " pushed into stack");
            return true;
        }
    }

    public int pop()
    {
        if(top < 0)
        {
            System.out.println("Stack Underflow");
            return 0;
        }
        else
        {
            return a[top--];
        }
    }

    public int peek()
    {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        else {
            int x = a[top];
            return x;
        }
    }
}
```

Reference

https://www.javatpoint.com/ds-array-implementation-of-stack
