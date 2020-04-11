# Objet Oriented Programming

## 4 Primary Concepts:

### Abstraction

**Abstraction** means using simple things to represent complexity. We all know how to turn the TV on, but we don't need to know how it works in order to enjoy it. In java, abstraction means simple things like **objects**, **classes** and **variables** represent more complex underlying code and data. This is important because it lets avoid repeating the same work multiple times. 

### Encapsulation

This is the practice of keeping fields within a class private, then providing access to them via public methods. 
It's a protective barrier that keeps the data and code safe within the class itself. This way, we can re-use objects like code components or variables without allowing open access to the data-system side. 

### Inheritance

This is a sepcial feature of Object Oriented Programming in Java. It lets programmers create new cleasses that share some of attribute of existing classes. This lets us build on previous work without reinventing the wheel.

### Polymorphism 

This Java OOP concept lets programmers use the same word to mean different things in different contexts. Best two examples here are **method overloading** and **method overriding**.

#### Method Overloading vs Method Overriding

**Method Overloading**

```java
add(int, int)
add(int, int, int)

add(int, int)
add(int, float)
```

**Method Overriding**
```java
// A Simple Java program to demonstrate 
// Overriding and Access-Modifiers 
  
class Parent { 
    // private methods are not overridden 
    private void m1() 
    { 
        System.out.println("From parent m1()"); 
    } 
  
    protected void m2() 
    { 
        System.out.println("From parent m2()"); 
    } 
} 
  
class Child extends Parent { 
    // new m1() method 
    // unique to Child class 
    private void m1() 
    { 
        System.out.println("From child m1()"); 
    } 
  
    // overriding method 
    // with more accessibility 
    @Override
    public void m2() 
    { 
        System.out.println("From child m2()"); 
    } 
} 
  
// Driver class 
class Main { 
    public static void main(String[] args) 
    { 
        Parent obj1 = new Parent(); 
        obj1.m2(); 
        Parent obj2 = new Child(); 
        obj2.m2(); 
    } 
} 
```

## Association vs Aggregation vs Composition

1. **Association** 当一个类知道另一个类。
2. **Aggregation** 弱的拥有，体现的是A对象可以包含B对象，但B对象不是A对象的一部分。
3. **Composition** 强的拥有，体现的是严格的部分和整体的关系，部分和整体的生命周期一样。

![alt text][engine]

[engine]: https://i.stack.imgur.com/bfBSY.png


## SOLID-LESS Design Principles:

### Single Responsibility Principle 

**One class should have one and only one responsibility**.


### Open Closed Principle 

**Software components should be open for extension, but closed for modification**.


### Liskov's Substitution Principle 

**Derived types must be completely substitutable for their base types**.


### Interface Segregation(隔离) Principle 

**Client should not be forced to implement unnecessary methods whih they will not use**.


### Dependency Inversion Principle 

**Depend on abastractions not on concertions**

In other words. we should design our software in such a way that various modules can be separated from each other using an abstract layer to bind them together.

In spring framework, all modules are provided as separate components which can work together by simply injected dependencies in other module. 

### Law of Demeter

The Law of Demeter principle states that a module should not have the knowledge on the inner details of the objects it manipulates. In other words, a software component or an object should not have the knowledge of the internal working of other objects or components.





Reference:

https://stackify.com/oops-concepts-in-java/

https://stackify.com/solid-design-principles/

