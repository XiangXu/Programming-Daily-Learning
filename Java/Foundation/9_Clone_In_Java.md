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

In this example, we will see that when we only have primitive data types with use the clone() method by default will give a different cloned object.

```java
import java.util.Objects;

public class Student implements Cloneable {

    private String name;
    private int rollNumber;

    Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
      //not doing anything
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
}

public class DeepVsShallowTesting {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student1 = new Student(1, "Rio");
        Student student2 = (Student) student1.clone();

        System.out.println((student1.equals(student2)) ? "equal" : "not equal");        //not equal

        //this will only update the roll number of student 1
        student1.setRollNumber(2);
        System.out.println(student1.getRollNumber() + " " + student2.getRollNumber());  //2 1
    }
}
```

Let's try to add a custom Book class in our student object and see what happens keeping everything else fixed.

```java
public class Book implements Cloneable {

    private String name;
    private int pages;
    private float price;

    public Book(String name, int pages, float price) {
        this.name = name;
        this.pages = pages;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Student implements Cloneable {

    private String name;
    private int rollNumber;
    private Book book;

    Student(int rollNumber, String name, Book book) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.book = book;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Book getBook() {
        return book;
    }
}

public class DeepVsShallowTesting {

    public static void main(String[] args) throws CloneNotSupportedException {
        Book book = new Book("Harry Potter", 120, 120.54F);
        Student student1 = new Student(1, "Rio", book);
        Student student2 = (Student) student1.clone();

        System.out.println((student1.equals(student2)) ? "equal" : "not equal");        //not equal
        System.out.println((student1.getBook().equals(student2.getBook())) ? "equal" : "not equal");        //equal

        //this will only update the roll number of student 1
        student1.setRollNumber(2);
        System.out.println(student1.getRollNumber() + ", " + student2.getRollNumber());  //2 1
        //but this will update both the object's book data since book is a custom class
        student1.getBook().setName("Harry Potter New");
        System.out.println(student1.getBook().getName() + ", " + student2.getBook().getName());  //Harry Potter New, Harry Potter New
    }
}
```

As we can see, we got the same reference to the book object present in the Student class since we didn't write the cloning logic inside clone() method of Student class.

**Shallow clone is default implementation in Java. In overriden clone the method, if you are not cloning all the object types(not primitives), then you are making a shallow copy**.

To correctly implement this, we will ask the compiler to clone the book object as well in clone() of Student class and for that, clone() of Book also need to be overridden and hence we can achieve the different reference for our complete Student object - this is what we call **deep copy** because both original and cloned objects can change independently of each other.

```java
public class Book implements Cloneable {

    private String name;
    private int pages;
    private float price;

    public Book(String name, int pages, float price) {
        this.name = name;
        this.pages = pages;
        this.price = price;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //since this contains only primitive data types, we need not write anything here
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Student implements Cloneable {

    private String name;
    private int rollNumber;
    private Book book;

    Student(int rollNumber, String name, Book book) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.book = book;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student clonedStudent = (Student)super.clone();
        clonedStudent.setBook((Book)clonedStudent.getBook().clone());
        return clonedStudent;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
 }

 public class DeepVsShallowTesting {

    public static void main(String[] args) throws CloneNotSupportedException {
        Book book = new Book("Harry Potter", 120, 120.54F);
        Student student1 = new Student(1, "Rio", book);
        Student student2 = (Student) student1.clone();

        System.out.println((student1.equals(student2)) ? "equal" : "not equal");        //not equal
        System.out.println((student1.getBook().equals(student2.getBook())) ? "equal" : "not equal");        //not equal

        //this will only update the roll number of student 1
        student1.setRollNumber(2);
        System.out.println(student1.getRollNumber() + ", " + student2.getRollNumber());  //2 1
        //this will only update the book object of student 1
        student1.getBook().setName("Harry Potter New");
        System.out.println(student1.getBook().getName() + ", " + student2.getBook().getName());  //Harry Potter New, Harry Potter
    }
}
```

As we can see, everything works perfectly here but we can also see that deep copying is a very expensive process as compared to the shallow copy and it is also very cumbersome(笨重的) if we have so many nested classes.

So the slolution is: Lazy Copy.

## Lazy Copy

A lazy copy is **a combination of both Shallow Copy and Deep Copy**. When initially copying an object, a shallow copy is used. A counter is also used to track how many objects share the data. **When the program wants to modify an object, it can determine if the data is shared(by examining the counter) and can do a deep copy if necessary**. The lazy copy looks to the outside just a deep copy but tasks advantage of the speed of a shallow copy whenever possible. The downsides are rather high but constant base costs because of the counter. Also, it certain situations, circular references can also cause problems.

## Cloning using Serialization

Serialization is another easy way of deep cloning. In this method, you juse serialize the object to be clone and de-serialize it.

This method has cons more than the pros 

Reference:

https://medium.com/@ganeshraj020794/cloning-in-java-shallow-vs-deep-vs-lazy-and-a-small-hack-to-clone-java-objects-204bdba0f3f8



