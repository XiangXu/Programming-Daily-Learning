# How does the Java 'for each' loop work?

The foreach loop added in Java 5(also called the "enhanced for loop"), is **equivalent to using java.util.Iterator**. - It is syntactic sugar for the same thing.

Therefore, when you reading each element, **one by one in order, a foreach should be chosen over an iterator**, as it is more convenient and concise(简明的).

There are situation where you must use an **Iterator** directly. For example, attempting to delete an element while using a foreach can result in ConcurrentModificationException.

**foreach**

```java
for(int i : intList) {
   System.out.println("An element in the list: " + i);
}
```

**Iterator**

```java
Iterator<Integer> intItr = intList.iterator();
while(intItr.hasNext()) {
   System.out.println("An element in the list: " + intItr.next());
}
```

## foreach vs for: Basic Differences

The only practical difference between foreach and for is that, in the case of indexable objects, you do not have access to the index.

Although you could manually create a seperate index int-variable with foreach, it is not recommanded, since variable-scope is not ideal, and the basic for loop is simply the standard and expected format for this use case.

## foreach vs for Performance 

When accessing collections, **a foreach is significantly faster than basic for loop's array access**. When accessing arrays, however at least with primitive and wrapper-arrays access via indexes is dramatically faster. 

Reference:

https://stackoverflow.com/questions/85190/how-does-the-java-for-each-loop-work/22114571#22114571