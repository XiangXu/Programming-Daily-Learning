# Autoboxing and Unboxing in Java

## Autoboxing

**Converting a primitive value into an object of the corresponding wrapper class is called autoboxing**.

For example, converting int to Integer class. The Java compiler applies autoboxing when a primitive is:

* Passed as a parameter to a method that **expected an object** of the corresponding wrapper class.
* Assigned to a variable of the corresponding **wrapper class**.

## Unboxing

**Converting an object of a wrapper type to its corresponding primitive value is called unboxing**.

For example conversion of Integer to int. The Java compiler applies unboxing when an object of a wrapper class is:

* Passed a parameter to a method that **expected a value** of the corresponding primitive type.
* Assigned a variable of corresponding **primitive type**.

The following table lists the primitive types and their corresponding wrapper classes, which are used by Java compiler for autoboxing and unboxing:

![boxing](https://media.geeksforgeeks.org/wp-content/uploads/Wrapper.png)

## Advantages of Autoboxing / Unboxing:

* Autoboxing and Unboxing lets developers write cleaner code, making it easier to read.
* The technique let us use primitive types and Wrapper class objects interchangeably and we do not need to perform any typecasting explicitly.

Reference

https://www.geeksforgeeks.org/autoboxing-unboxing-java/