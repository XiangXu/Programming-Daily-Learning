# Clone In Java

## What is Cloning? Copy Constructor & Assigment Operator

**Cloning is creating a copy of the original object. We can use the assigment operator(=) or copy constructor to achieve this**.

Copy Constructor provides a **deep copy** of the object whereas the assigment operator provides us with a **shallow copy** of the original object.

```java
package com.example.java.cloning;

import java.util.Objects;

public class Student {

    private String name;
    private int rollNumber;

    //normal constructor
    private Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    //copy constructor
    private Student(Student targetStudent) {
        this.rollNumber = targetStudent.rollNumber;
        this.name = targetStudent.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNumber == student.rollNumber && name.equals(student.name);
    }

    /***
     * By default, integer value is mostly derived from memory address of the object in heap (but it’s not mandatory always)
     * @return an unique integer value for the object in runtime.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, rollNumber);
    }

    public static void main(String[] args) {
        //cloning using copy constructor
        Student student1 = new Student(1, "Sergio Marquina");
        Student student2 = new Student(student1);

        //cloning using assignment operator
        Student student3 = new Student(2, "Andres");
        Student student4 = student3;

        //this compares memory addresses of both the objects
        System.out.println((student1 == student2) ? "equal" : "not equal");     //not equal
        System.out.println((student3 == student4) ? "equal" : "not equal");     //equal

        /*
         * It’s default implementation simply check the object references of two objects to verify their equality.
         * By default, two objects are equal if and only if they are stored in the same memory address.
         */
        System.out.println((student1.equals(student2)) ? "equal" : "not equal");    //equal 
    }
}
```

## clone() method, Shallow, Deep Copy, and Lazy Copy

We all know that Object is the parent class of all the classes in Java and the clone() method of this class whenever invoked, JVM does the following things:

1. If the class has only primitive data type members then a completely new copy of the object will be created and the reference to the new object copy will be returned.
   
2. If the class contains members of any class type then **only the object references to those members are copied** and hence the member references in both the original object as well as the cloned object refer to the same object.

Although, we can always override clone() as per our implementation.

## Lazy Copy

A lazy copy is **a combination of both Shallow Copy and Deep Copy**. When initially copying an object, a shallow copy is used. A counter is also used to track how many objects share the data. **When the program wants to modify an object, it can determine if the data is shared(by examining the counter) and can do a deep copy if necessary**. The lazy copy looks to the outside just a deep copy but tasks advantage of the speed of a shallow copy whenever possible. The downsides are rather high but constant base costs because of the counter. Also, it certain situations, circular references can also cause problems.

## Cloning using Serialization

Serialization is another easy way of deep cloning. In this method, you juse serialize the object to be clone and de-serialize it.

This method has cons more than the pros 

Reference:

https://medium.com/@ganeshraj020794/cloning-in-java-shallow-vs-deep-vs-lazy-and-a-small-hack-to-clone-java-objects-204bdba0f3f8



