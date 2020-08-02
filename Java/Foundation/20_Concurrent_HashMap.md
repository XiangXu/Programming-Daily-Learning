# Hash Table Vs Concurrent HashMap and it’s internal working

Even though all properties of HashTable and Concurrent HashMap is similar but "**If it is a thread-safe highly-concurrent implementation is desired, then it is recommended to use ConcurrentHashMap in place of Hashtable. Why??**"

1. Even though both Concurrent HashMap and HashTable are thread safe. But HashTable has **poor performance** in multi threading usage.
2. If one thread allows to perform any kind of operation(put or get), **other thread must and should wait until the operation was completed** by the thread which is working on hash table.

## Internal working of HashTable

![hashTable](https://miro.medium.com/max/1400/0*-bIUqhJOlSkJuw6m.png)

This diagram is seems to be similar to the internal implementation of HashMap, but **Hashtable is synchronized** and provides thread safe like concurrentHashMap but in the performance point of view, HashTable write operation uses **map side lock which means it locks the complete map object**.

Example for better understanding

Thread T1 calls get()/put() operation on Hashtable and **acquires the lock on complete hashtable object**.

Now if Thread T2 calls get()/put() operation, **it has to wait till T1 finishes get()/put() operation and releases the lock on the object**.

![example1](https://miro.medium.com/max/2000/0*BmTbmUY8YXD1v365.png)

## Internal working of Concurrent HashMap

![example2](https://miro.medium.com/max/2000/0*qO64Hu9t5mBN7T4f.png)

### Threads acquiring lock on ConcurrentHashMap for MultiThreading Environment

ConcurrentHashMap works a bit different as **it acquires lock per Segment which means instead of single map wide lock**, it has multiple Segment level locks. It uses a Locking technique name **ReentrantLock**.

So 2 Threads operating on different segments can acquire lock on those segments without interfering with each other and can proceed simultaneously as they are working on separate Segment locks.

Simultaneous Read and Write operations by Multiple Threads on same or different segments of ConcurrentHashMap

* **Read/Get Operation**:- Two Threads T1 and T2 can read data from the same or different segment of ConcurrentHashMap at the same time without blocking each other.

* **Write/Put Operation**:- Two Threads T1 and T2 can write data on different segment at the same time without blocking the other.

But Two threads **can’t write data on same segments at the same time**. One has to wait for other to complete the operation.

![example3](https://miro.medium.com/max/2000/0*EIGQ5ZqOcj-pIgKZ.png)

## Deep Dive of Understanding how the put and gets will carried on Concurrent HashMap

### Constructor for the concurrent HashMap

```java
ConcurrentHashMap map = new ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel)
```

![example4](https://miro.medium.com/max/1400/0*b6rdgX9mZRGRA77I.png)

```
segment array size = 2 to the power x = concurrency level
Bucket array size inside the segment = 2 ^ x ≥ (initialCapacity / concurrencyLevel)
```

If we give concurrency level or initialCapacity = 10 and it will consider concurrency level or initialCapacity as 16 ( next 2 to the power x is 16)

**The number of threads processed at a time in concurrentHashMap is equal to the concurrency level**.

### PUT and GET Operations in Concurrent HashMap

![example5](https://miro.medium.com/max/1400/1*aqbOZowMWf_-svS-K4iP2A.png)

1. Calculation of Hashcode (hash index for the bucket(array) which is present inside the segment) for the key

```java
int hash = hash(key.hashCode());

//CALCULATION OF HASHINDEX 
private static int hash(int h) {
	// Spread bits to regularize both segment and index locations,
	// using variant of single-word Wang/Jenkins hash.
	h += (h <<  15) ^ 0xffffcd7d;
	h ^= (h >>> 10);
	h += (h <<   3);
	h ^= (h >>>  6);
	h += (h <<   2) + (h << 14);
	return h ^ (h >>> 16);
}
```

2. Preparation of HashEntry<K,V>

```java
static final class HashEntry<K,V> {
    final int hash;
    final K key;
    volatile V value;
    volatile HashEntry<K,V> next;
}
```

3. Calculation of Segment Shift and Segment Mask with the Help of Concurrency Level provided in constructor.

```java
private static void SegmentDetails(int concurrencyLevel) {
	int sshift = 0;
	int segmentMask = 0;
	int segmentShift = 0;

	int ssize = 1;
	while (ssize < concurrencyLevel) {
		++sshift;
		ssize <<= 1;
	}
	segmentShift = 32 - sshift;
	segmentMask = ssize - 1;
	System.out.println("Segment array size :" + ssize);
	System.out.println("segmentShift : " + segmentShift);
	System.out.println("segmentMask : " + segmentMask);
}
```

4. Calculate the Segment Level (Index of the Segment Array)

```java
//Segment Implementation
static final class Segment<K,V> extends ReentrantLock implements Serializable {

	//The number of elements in this segment's region.
	transient volatile int count;
	//The per-segment table. 
	transient volatile HashEntry<K,V>[] table;
}
//Segment index calculation 
final Segment<K,V> segmentFor(int hash) {
	return segments[(hash >>> segmentShift) & segmentMask];
}
```

5. Put and Get
   * Put: Based on segment index, HashEntry<K,V>(node) is placed in particular segment then Based on the hashindex, HashEntry<K,V>(node) is placed in array inside the segment which is similarly like how nodes are placed inside the bucket in HashMap.
   * Get: Based on segment index, we figure out particular segment then Based on the hashindex, we will get values from the array inside the segment which is similarly like how we will get values from the bucket in HashMap.

### LoadFactor and Rehashing 

1. ConcurrentHashMap has loadFactor which decides **when exactly to increase the capacity of ConcurrentHashMap** by calculating threshold(initialCapacity*loadFactor) and accordingly rehashing the map.
   
2. Basically, Rehashing is the process of re-calculating the hashcode of already stored entries (Key-Value pairs), to move them to another bigger size map when Load factor threshold of bucket inside the segment is reached. Also It is not only done to distribute items across the new length map, but also when there are too many key collisions which increases entries in one bucket so that get and put operation time complexity remains O(1).

**In ConcurrentHashMap, Every segment is separately rehashed so there is no collision between Thread 1 writing to Segment index 1 and Thread 2 writing to Segment index 4**.

### Methods of Concurrent HashMap is not an Atomic Nature

```java
votingMachine.put("TDP",0L);
votingMachine.put("TDP",new AtomicLong());
```

Use AtomicLong Variable instead of Long to escape from shared-Mutability. we can also used other approaches like synchronized block to solve above problem

Reference

https://medium.com/art-of-coding/hash-table-vs-concurrent-hashmap-and-its-internal-working-b28fc2725bdb
