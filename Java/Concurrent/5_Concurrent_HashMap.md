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

### JDK1.8

JDK1.8 的 ConcurrentHashMap 不在是 Segment 数组 + HashEntry 数组 + 链表，而是 Node 数组 + 链表 / 红黑树。不过，Node 只能用于链表的情况，红黑树的情况需要使用 TreeNode。当冲突链表达到一定长度时，链表会转换成红黑树。

ConcurrentHashMap 取消了 Segment 分段锁，采用 CAS 和 synchronized 来保证并发安全。数据结构跟 HashMap1.8 的结构类似，数组+链表/红黑二叉树。Java 8 在链表长度超过一定阈值（8）时将链表（寻址时间复杂度为 O(N)）转换为红黑树（寻址时间复杂度为 O(log(N))。

synchronized 只锁定当前链表或红黑二叉树的首节点，这样只要 hash 不冲突，就不会产生并发，效率又提升 N 倍。

## HashMap 的长度为什么是 2 的幂次方

为了能让 HashMap 存取高效，尽量较少碰撞，也就是要尽量把数据分配均匀。我们上面也讲到了过了，Hash 值的范围值-2147483648 到 2147483647，前后加起来大概 40 亿的映射空间，只要哈希函数映射得比较均匀松散，一般应用是很难出现碰撞的。但问题是一个 40 亿长度的数组，内存是放不下的。所以这个散列值是不能直接拿来用的。用之前还要先做对数组的长度取模运算，得到的余数才能用来要存放的位置也就是对应的数组下标。这个数组下标的计算方法是“ (n - 1) & hash”。（n 代表数组长度）。这也就解释了 HashMap 的长度为什么是 2 的幂次方。

这个算法应该如何设计呢？

我们首先可能会想到采用%取余的操作来实现。但是，重点来了：“取余(%)操作中如果除数是 2 的幂次则等价于与其除数减一的与(&)操作（也就是说 hash%length==hash&(length-1)的前提是 length 是 2 的 n 次方；）。” 并且 采用二进制位操作 &，相对于%能够提高运算效率，这就解释了 HashMap 的长度为什么是 2 的幂次方

Reference

https://medium.com/art-of-coding/hash-table-vs-concurrent-hashmap-and-its-internal-working-b28fc2725bdb

https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/collection/Java%E9%9B%86%E5%90%88%E6%A1%86%E6%9E%B6%E5%B8%B8%E8%A7%81%E9%9D%A2%E8%AF%95%E9%A2%98.md#1410-concurrenthashmap-%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E7%9A%84%E5%85%B7%E4%BD%93%E5%AE%9E%E7%8E%B0%E6%96%B9%E5%BC%8F%E5%BA%95%E5%B1%82%E5%85%B7%E4%BD%93%E5%AE%9E%E7%8E%B0
