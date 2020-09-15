# CopyOnWriteArrayList In Java

## CopyOnWriteArrayListAPI

The design of the **CopyOnWriteArrayList** uses an interesting technique to make it thread-safe without need for synchronized. When we are using any of the modify methods - such as add() and remove() - the whole content of the CopyOnWriteArrayList is copied into the new internal copy.

**Due to this simple fact, we can iterate over the list in a safe way, even when concurrent modification is happening**.

When we are calling the iterator() method on CopyOnWriteArrayList, we get back an Iterator backed up by the immutable snapshot of the content of the CopyOnWriteArrayList. Its content is an exact copy of data that is inside an ArrayList from the time when the Iterator was created. Even if in the meantime some other thread adds or removed an element from the list, that modification is making a fresh copy of the data that will be used in any further data lookup from that list. 

**The characteristics of this data structure make it particularly useful in cases when we are iterating over it more often than we are modifying it**. If adding elements is a common operation in our scenario, then CopyOnWriteArrayList won't be a good choice – because the additional copies will definitely lead to sub-par performance. 

## Iterating Over CopyOnWriteArrayList While Inserting

```java
// Let's say that we're creating an instance of the CopyOnWriteArrayList that stores integers
CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

// Next, we want to iterate over that array, so we are creating an Iterator instance:
Iterator<Integer> iterator = numbers.iterator();

// After the Iterator is created, we are adding a new element to the numbers list:
numbers.add(10);
```

Keep in mind that, when we create an iterator for the CopyOnWriteArrayList, we get immutable snapshot of the data in the list at that time iterator was called().

**Because of that, while iterating over it, we won't see the number 10 in the iteration**.

## Removing While Iterating Is Not Allowed

The CopyOnWriteArrayList was created to allow for the possibility of safe iterating over elements even when the underlying list gets modified.

Because of the copying mechanism, the remove() operation on the returned Iterator is not permitted – resulting with UnsupportedOperationException:

```java
@Test(expected = UnsupportedOperationException.class)
public void whenIterateOverItAndTryToRemoveElement_thenShouldThrowException() {
    
    CopyOnWriteArrayList<Integer> numbers
      = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});
 
    Iterator<Integer> iterator = numbers.iterator();
    while (iterator.hasNext()) {
        iterator.remove();
    }
}
```

## CopyOnWrite的缺点

* **内存占用问题**: 因为CopyOnWrite的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存，旧的对象和新写入的对象（注意:在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添加到新容器里，而旧容器的对象还在使用，所以有两份对象内存）。如果这些对象占用的内存比较大，比如说200M左右，那么再写入100M数据进去，内存就会占用300M，那么这个时候很有可能造成频繁的Yong GC和Full GC。之前我们系统中使用了一个服务由于每晚使用CopyOnWrite机制更新大对象，造成了每晚15秒的Full GC，应用响应时间也随之变长。    

* **数据一致性问题**: CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写入的的数据，马上能读到，请不要使用CopyOnWrite容器。【当执行add或remove操作没完成时，get获取的仍然是旧数组的元素】 

Reference 

https://www.baeldung.com/java-copy-on-write-arraylist

https://blog.csdn.net/u010002184/article/details/90452918