# HashMap  

## Map and HashMap

**Map is a collection which stores elements as key-value pairs. A map cannot contain duplicate keys and each key can map to at most one value**. The map interface includes methods for basic operations such as put, get, remove, containsKey, containsValue, size, and empty, bulk operations such as putAll and clear, and collection views such as keySet, entrySet, and values.

HashMap implements Map interface in Java. **It is not synchronized and it is not thread safe**. HashMap works with Hashing, to understand hashing, we should understand firs about HashFunction, HashValue and Bucket.

## What is Hashing?

Let's consider an array that is not sorted and the problem is searching a value in the array. The search requires comparing all elements of the array. So, the time complexity is O(n). If the array is sorted, a binary search can reduce the time complexity to O(logn). Also, the search can be faster if there is a function which returns an index for each element in the array. In that case the time complexity is reduced to a constant time O(1). Such a function is called **Hash Function**. A hash function is a function which for a given array, generates a **Hash Value**.

Java has a hash function which called **hashCode()**. **The hashCode() method is implemented in the Object class and therefore each class in java inherits it**. The hash code provides the hash value. Here is the implementation of hashCode method in Object class.

```java
public native int hashCode();
```

## What is bucket?

A bucket is used to store key-value pairs. A bucket can have miltiple key-value pairs. **In hashMap, bucket uses simple linkedlist to store objects**.

## HashMap implementation inside Java

In HashMap, get(Object key) calls hashCode() on the key object and uses the returned hashValue to find bucket location when keys and values are stored as an **Entry**.

```java
public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
}
 
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

get(Object key) also checks whether the key is null or not. There can be only one null key in HashMap. If the key is null, then the null key always map to hash 0, then inddex 0. If the key is not null, then it will call hash function on the object.

Now, the hashValue is used to find the bucket location at which Entry object is stored. Entry object stores in the bucket as (hash, key, value, bucket index). Then the value object is returned.

**What about if two keys have the same hashcode**? Here, the implementation of **equals()** method for key object is become important.

**The bucket is is a linkedlist but not java.util.LinkedList**. **HashMap has its own implementation of the linkedlist**. Therefore, it traverses through linkedlist and compares keys in each entry using **key.equals()** until equals() returns true. Then the value object is returned.

Also, if when two keys are same and have same hashcode, then the previous key-value pair is replaced with the current key-value pair.

It is important that in Map, any class can serve as a key if and only if it overrides the equals() and hashCode() method. Also, **it is the best practice to make the key class an immutable class**.

##  HashMap performance

An instance of HashMap has two attributes that affect its performance: **initial capacity and load factor**.

**The capacity is the number of buckets in the hashMap**. The initial capacity is the capacity when the hashMap is created.

**The load factor is a measure of how full the HashMap is allowed to get, before its capacity is automatically increased**. When the number of entries in the HashMap exceeds the product of the load factor and the current capacity, the hashMap is rehashed. Then, the HashMap has approximately twice the number of buckets. In HashMap class, **the default value of load factor is 0.75**.

## HashMap in Java 8

**Since Java 8 if HashMap contains more than 7 elements in the same bucket linked list transforms to a tree and time complecity changes to O(log n)**.

Java 8 has come with the following improvements/changes of HashMap objects in case of high collisions.

* The alternative String hash function added in Java 7 has been removed.
* Buckets containing a large of number of colliding keys will stored their entries in a balanced tree instead of a linked list after certain threshold is reached.

How linked list is replaced with binary tree?

**In Java 8, HashMap replaces linkedlist with a binary tree when the number of element in a backet reaches certain threshold. While converting the list to binary tree, hashcode is used as a branching variable. If there are two different hashcodes in the same bucket, one is considered bigger goes to the right of the tree and other one to the left. But when both the hashcode are equal, HashMap assumes that the keys are comparable, and compares the key to determine the direction so that some order can be maintained. It is a good practice to make the keys of HashMap comparable**.

Reference

https://examples.javacodegeeks.com/core-java/maphashmap-works-internally-java/

https://www.nagarro.com/en/blog/post/24/performance-improvement-for-hashmap-in-java-8#:~:text=In%20worst%20case%20scenario%2C%20when,Java%207%20has%20been%20removed.