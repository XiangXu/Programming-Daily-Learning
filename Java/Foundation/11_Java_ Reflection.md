# Java Reflection

In Java, reflection allows us to inspect and manipulate classes, interfaces, constructors, methods and fields at rum time.

## Create objects of the class named Class

We can create objects of **class** by 

* **using forName() Method**

**forName()** takes a string argument(name of a class) and returns an object of **class**. The returned object refers to the class specified by the string.

```java
Class Dog{}

Class c1 = Class.forName("Dog");
```

* using getClass() Method

The **getClass()** method uses the object of a particular class to create a new object of **class**.

```java
Dog d1 = new Dog();
Class c1 = d1.getClass();
```

* **using .class**

We can also create objects of **Class** by using the .class extension.

```java
Class c1 = Dog.class;
```

Once the objects of Class are created, we can use these objects to perform reflection.

## Get Interfaces

We can use the **getInterfaces()** method of Class to collect information about the interfaces implemented by the class. The method returns an array of interfaces.






Reference

https://www.programiz.com/java-programming/reflection

