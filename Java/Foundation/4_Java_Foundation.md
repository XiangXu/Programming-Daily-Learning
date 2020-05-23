# Java Fundamental Stuff

## final, finally and finalize 

### final

**final with variable**: The value of variable cannot be changed once initialized.

**final with class**: The class cannot be subclassed.

**final with method**: The method cannot be overriden by a subclass.

### finally

The finally keyword is used in association with try/catch block and guarantees that a section of code will be executed, even if an exception is thrown. The finally block will be executed after the try and catch blocks.

### finalize

That is a method that the **Garbage Collector** always call just before the deletion/destorying the object which is eligible for Garbage Collection, so as to perform clean-up activity.

```java
class Hi { 
    public static void main(String[] args) 
    { 
        Hi j = new Hi(); 
  
        // Calling finalize method Explicitly. 
        j.finalize(); 
  
        j = null; 
  
        // Requesting JVM to call Garbage Collector method 
        System.gc(); 
        System.out.println("Main Completes"); 
    } 
  
    // Here overriding finalize method 
    public void finalize() 
    { 
        System.out.println("finalize method overriden"); 
        System.out.println(10 / 0); 
    } 
} 

//exception in thread "main" java.lang.ArithmeticException:
//by zero followed by stack trace.


class RR { 
    public static void main(String[] args) 
    { 
        RR q = new RR(); 
        q = null; 
  
        // Requesting JVM to call Garbage Collector method 
        System.gc(); 
        System.out.println("Main Completes"); 
    } 
  
    // Here overriding finalize method 
    public void finalize() 
    { 
        System.out.println("finalize method overriden"); 
        System.out.println(10 / 0); 
    } 
} 

//finalize method overriden
//Main Completes

```

## Static

The static keyword in Java is **used for memory management mainly**.

The static can be:

1. Varaible (also known as a class variable)
2. Method (also known as a class method)
3. Block
4. Nested Class

### Static Variable

If you declare any variable as static, it is known as a static variable.

* The static variable can be used to refer to the common property of all projects(which is not unique for each object), for example, the company name of employees, colleage name of student, etc.
* The static variable gets memory only once in the class area at the time of class loading. 

```java
class Student{  
     int rollno;  
     String name;  
     String college="ITS";  
}  
```

**Static variable makes program memory efficient, it is shared to all objects**.

### Static Method

If you apply static keyword with any method, it is known as static method.

* A static method belongs to the class rather than the object of a class.
* A static method can be invoked without the need for creating an instance of a class.
* A static method can access static data member and can change the value of it.

### Static Block

* It is used to initialize the static data member.
* It is executed before the main method at the time of classloading.

Why is the Java main method static?

It is because the object is not required to call a static method. If it were a non-static method, JVM creates an object first then call main() method that will lead the problem of extra memory allocation.


## ArrayList vs LinkedList

ArrayList and LinkedList both implement List interface and maintain insertion order. Both are non synchronized classes. 

**Differences**:

|  ArrayList |  LinkedList  |
|---|---|
| ArrayList internally uses a dynamic array to store elements. |  LinkedList internally uses a double linked list to store the elements.   |
| Manipulation with ArrayList is slow because it internally uses an array. If any element is removed from the array, all the bits are shifted in memory. |  Manipulation with LinkedList is faster than ArrayList because it uses a double linked list, so no bit shifting is required in memory.  |
| An ArrayList class can act as a list only because it implements List only. |  LinkedList class can act as a list and queue both because it implements List and Deque interfaces. |
| ArrayList is **better for storing and accessing data**. |  LinkedList is **better for manipulating data**. |


## HashMap vs TreeMap vs LinkedHashMap

**HashMap** is implemented as a hash table, and there is no ordering on keys or values.

**TreeMap** is implemented based on red-black tree structure, and it is ordered by the key.

**LinkedHashMap** preverses the insertion order. 


## HashSet vs TreeSet vs LinkedHashSet

**HashSet** implements set interface, underlying data structure for HashSet is hashtable. Duplicate values are not allowed. 

**TreeSet** is implemented based on red-black tree structure, and it is ordered by the value.

**LinkedHashSet** preverses the insertion order. 




## BigDecimal

The BigDecimal class provides operations on double numbers for arithmetic, scale handling, rounding, comparison, format conversion and hashing. It can handle very large and very small floating point numbers with great precision but compensating with the time complexity a bit.

Let's look at an example:
```java
float a = 1.0f - 0.9f;
float b = 0.9f - 0.8f;
System.out.println(a);// 0.100000024
System.out.println(b);// 0.099999964
System.out.println(a == b);// false loss of precision

BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("0.9");
BigDecimal c = new BigDecimal("0.8");
BigDecimal x = a.subtract(b);// 0.1
BigDecimal y = b.subtract(c);// 0.1
System.out.println(x.equals(y));// true 
```

### Comparison

**a.compareTo(b)** : return -1 means less, 0 means equal, 1 means bigger.
```java
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("0.9");
System.out.println(a.compareTo(b));// 1
```

### Scale
```java
BigDecimal m = new BigDecimal("1.255433");
BigDecimal n = m.setScale(3,BigDecimal.ROUND_HALF_DOWN);
System.out.println(n);// 1.255
```

In order to prevent precision loss, we would recommend to use **BigDecimal(String)**. 


### Arrays.asList()

Convert array into list: 
```java
String[] myArray = { "Apple", "Banana", "Orange" }； 
List<String> myList = Arrays.asList(myArray);
// or
List<String> myList = Arrays.asList("Apple","Banana", "Orange");
```

JDK Source Code:
```java
public static <T> List<T> asList(T... a) {
    return new ArrayList<>(a);
}
```

When you use this method, you need to pass an array of object rather than an arry of primitive.

Let's look at an example:
```java
int[] myArray = { 1, 2, 3 };
List myList = Arrays.asList(myArray);
System.out.println(myList.size());//1
System.out.println(myList.get(0));//address
System.out.println(myList.get(1));//Error：ArrayIndexOutOfBoundsException
int [] array=(int[]) myList.get(0);
System.out.println(array[0]);//1

//Correct way:
Integer[] myArray = { 1, 2, 3 };
```

The list of **Arrays.asList()** returned is not the type of **java.util.ArrayList**, it is an internal class in **java.util.Arrays**. **So you can not modify it by using default List moethods.**
```java
List myList = Arrays.asList(1, 2, 3);
myList.add(4);//Error：UnsupportedOperationException
myList.remove(1);//Error：UnsupportedOperationException
myList.clear();//Error：UnsupportedOperationException

List myList = Arrays.asList(1, 2, 3);
System.out.println(myList.getClass());//class java.util.Arrays$ArrayList
```

#### How to convert array into ArrayList?
```java
List list = new ArrayList<>(Arrays.asList("a", "b", "c"))
```

Java 8:
```java
Integer [] myArray = { 1, 2, 3 };
List myList = Arrays.stream(myArray).collect(Collectors.toList());

int [] myArray2 = { 1, 2, 3 };
List myList = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
```

Apache Commons Collections:
```java
List<String> list = new ArrayList<String>();
CollectionUtils.addAll(list, str);
```

## Difference between Collection.toArray() and Collection.toArray(Object obj[])

One is generic, the other isn't. toArray() will return Object[] while toArray(T[]) will return an array of type T[].

```java
public static void main(String[] args) {
    Object[] baseArray = new ArrayList<String>().toArray();
    System.out.println(baseArray.getClass().getCanonicalName());

    String[] improvArray = new ArrayList<String>().toArray(new String[5]);
    System.out.println(improvArray.getClass().getCanonicalName());
}
//Output:
//java.lang.Object[]
//java.lang.String[]
```

### Do not add/remove in foreach loop. To safely remove from a collection while iterating over it you should use an Iterator. 
```java
List<String> names = ....
Iterator<String> i = names.iterator();
while (i.hasNext()) {
   String s = i.next(); // must be called before you can call i.remove()
   // Do something
   i.remove();
}
```
### Implement Stack
```java
import java.lang.IllegalArgumentException;
import java.util.Arrays;

class MyStack{
    private int[] storage;
    private int capacity;
    private int count;
    private static final int GROW_FACTORY = 2;

    public MyStack()
    {
        this.capacity = 8;
        this.storage = new int [8];
        this.count = 0;
    }

    public MyStack(int initialCapacity)
    {
        if(initialCapacity < 1)
            throw new IllegalArgumentException("Capacity too small");

        this.capacity = initialCapacity;
        this.storage = new int[initialCapacity];
        this.count = 0;
    }

    private void ensureCapacity(){
        int newCapacity = capacity * GROW_FACTORY;
        storage = Arrays.copyOf(storage, newCapacity);
        capacity = newCapacity;
    }

    public void push(int value)
    {
        if(count == capacity)
            ensureCapacity();

        storage[count++] = value;
    }

    public int pop()
    {
        if(count == 0)
            throw new IllegalArgumentException("Stack is empty");
        count --;
        return storage[count];
    }

    public int peek()
    {
        if(count == 0)
            throw new IllegalArgumentException("Stack is empty");
        else
            return storage[count--];
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public int size()
    {
        return count;
    }
}

public class TestStack{
     public static void main(String []args)
     {
        MyStack myStack = new MyStack(3);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        System.out.println(myStack.peek());//8
        System.out.println(myStack.size());//8
        for (int i = 0; i < 8; i++) {
            System.out.println(myStack.pop());
        }
        System.out.println(myStack.isEmpty());//true
        myStack.pop();//报错：java.lang.IllegalArgumentException: Stack is empty.
     }
}
```



Reference:

https://snailclimb.gitee.io/javaguide/#/docs/java/Java%E7%96%91%E9%9A%BE%E7%82%B9?id=11-%e6%ad%a3%e7%a1%ae%e4%bd%bf%e7%94%a8-equals-%e6%96%b9%e6%b3%95

https://www.geeksforgeeks.org/bigdecimal-class-java/

https://stackoverflow.com/questions/16748030/difference-between-arrays-aslistarray-and-new-arraylistintegerarrays-aslist

https://stackoverflow.com/questions/20308285/difference-between-collection-toarray-and-collection-toarrayobject-obj

https://www.geeksforgeeks.org/g-fact-24-finalfinally-and-finalize-in-java/

https://www.geeksforgeeks.org/arraylist-vs-linkedlist-java/

https://www.javatpoint.com/static-keyword-in-java