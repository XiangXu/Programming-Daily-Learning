# Lterator in Java

## Introduction

An iterator is one of many ways we can traverse a collection, and as every option, it has its pros and conds. It was first introduced in Java 1.2 as a replacement of Enumerations and 

* introduced improved method names.
* **made it possible to remove elements from a collection we'are iterating over**.
* doesn't guarantee iteration order.

## The Iterator Interface

To start, we need to obtain an Iterator from a Collection; this is done by calling iterator() method.

```java
List<String> items = new ArrayList<>();
Iterator<String> iter = items.iterator();
```

The iterator has three core methods

### hasNext()

The hasNext() method can be used for checking if there's at least one element left to iterator over. It's designed to be used as a condition in while loops:

```java
while (iter.hasNext()) {
    // ...
}
```

### next()

The next() method can be used for stepping over the next element and obatining it.

```java
String next = iter.next();
```

**It is good practice to use hasNext() before attempting to call next()**.

Iterators for Collections don't guarantee iteration in any particular order unless particular implementation provides it.

### remove()

Finally, if we want to **remove the current element from the collection**, we can use the remove:

```java
iter.remove();
```

**This is a safe way to remove elements while iterating over a collection without a risk of a ConcurrentModificationException**.

```java
while (iter.hasNext()) {
    String next = iter.next();
    System.out.println(next);
 
    if( "TWO".equals(next)) {
        iter.remove();				
    }
}
```

## Iterating With Lambda Expressions

Since Java 8, we have the forEachRemaining method that allows the use of lambdas to processing remaining elements:

```java
iter.forEachRemaining(System.out::println);
```

## The ListIterator Interface

ListIterator is an extension that adds new functionality for iterating over lists.

we can provide a starting position which in this case is the end of the List.

```java
ListIterator<String> listIterator = items.listIterator(items.size());
```

### hasPrevious() and previous()

ListIterator can be used for backward traversal so it provides equivalents of hasNext() and next():

```java
while(listIterator.hasPrevious()) {
    String previous = listIterator.previous();
}
```

### nextIndex() and previousIndex()

Additionally, we can traverse over indices and not actual elements:

```java
String nextWithIndex = items.get(listIterator.nextIndex());
String previousWithIndex = items.get(listIterator.previousIndex());
```

### add()

The add method, which, as the name suggests, allows us to add an element **before the item that would be return by next() and after the one returned by previous()**:

```java
listIterator.add("FOUR");
```

### set()

It lets us replace the element that was returned in the call to next() or previous():

```java
String next = listIterator.next();
if( "ONE".equals(next)) {
    listIterator.set("SWAPPED");
}
```

It's important to note that **this can only be executed if no prior calls to add() or remove() were made**.

Reference

https://www.baeldung.com/java-iterator