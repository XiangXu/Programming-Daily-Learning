# Java Static Keyword

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

### Static Nested Class

Check details in 8_Java_Inner_Class.md

Reference

https://www.javatpoint.com/static-keyword-in-java