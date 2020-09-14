# Fail-fast and Fail-safe iterations in Java Collections

Using interations we can traverse over the collection objects. This iterators can be either fail-fast or fail-safe.

**Fail-Safe iterators means they will not throw any exception even if the collection is modified while iterating over it. Whereas Fail-Fast interators throw an exception(ConcurrentModificationException) if the collection is modified while iterating over it**.

Consider an example:

```java
ArrayList<Integer> integers = new ArrayList<>();
integers.add(1);
integers.add(2);
integers.add(3);
Iterator<Integer> itr = integers.iterator();
while (itr.hasNext()) {
    Integer a = itr.next();
    integers.remove(a);
}
```

**As arrayLists are fail-fast above code will throw an exception**.

First a while have value = 1, then 1 will be removed in same iteration. Next when we try to get next(), as the modification is made to the list, it will throw exception here.

However if we use an fail-safe collection CopyOnWriteArrayList then no exception will occur:

```java
List<Integer> integers = new CopyOnWriteArrayList<>();
integers.add(1);
integers.add(2);
integers.add(3);
Iterator<Integer> itr = integers.iterator();
while (itr.hasNext()) {
    Integer a = itr.next();
    integers.remove(a);
}
```

## Fail-Fast Interators internal working

Every fail fast collection has a **modCount** field, to represent how many times the collection has changed/modified. So at every modification of this collection we increment the modCount value. For example the modCount is incremental in below cases:

1. When one or more elements are removed.
2. When one or more elements are added.
3. When the collection is replaced with other collection.
4. When the collection is sotred.

So everytime there is some change in the collection structure, the mod count is incremented.

Now the iterators stores the modCount value in the initialization as below:

```java
int expectedModCount = modCount;
```

Now while the iteration is going on, expectedModCount will have old value of modCount. If there is any change made in the collection, the modCount will change and then an exception is thrown using

```java
if (modCount != expectedModCount)
    throw new ConcurrentModificationException();
```

This code is used in most of the iterator methods

1. next()
2. remove()
3. add()

So if we make any changes to the collection, the modCount will change, and expectedModCount will not be hence equal to the modCount. Then if we use any of the above methods of iterator, the ConcurrentModificationException will be thrown.

Note: If we remove/add the element using the remove() or add() of iterator instead of collection, then in that case no exception will opccur. It is because the remove/add methods of iterators call the remove/add method of collection internally, and also it reasigns the expectedModCount to new modCount value.

```java
ArrayList.this.remove(lastRet);
cursor = lastRet;
lastRet = -1;
expectedModCount = modCount;

// and

ArrayList.this.add(i, e);
expectedModCount = modCount;
```

So the code below is safe as we are removing element from the interator here:

```java
Iterator<Integer> itr = integers.iterator();
while (itr.hasNext()) {
    if (itr.next() == 2) {
        // will not throw Exception
        itr.remove();
    }
}
```

Whereas the below code will throw an exception as we are removing the element from the collection here:

```java
Iterator<Integer> itr = integers.iterator();
while (itr.hasNext()) {
    if (itr.next() == 3) {
        // will throw Exception on
        // next call of next() method
        integers.remove(3);
    }
}
```

## Fail-Safe Iterators internal working

Unlike the fail-fast iterators, these iterators traverse over the clone of the collection. So even if the original collection gets structurally modified, no exception will be thrown.

In case of CopyOnWriteArrayList the original collections is passed and is stored in the iterator:

```java
public Iterator<E> iterator() {
    return new COWIterator<E>(getArray(), 0);
}
```

Here iterator() method returns the iterator of the CopyWriteArrayList. As we can see, it passes the getArray() in the constructor of the interator. This getArray() has all the collection elements. Now the iterator(COWIterator) will save this to traverse upon.

```java
COWIterator(Object[] elements, int initialCursor) {
    cursor = initialCursor;
    snapshot = elements;
}
```

So the original collection elements are saved in the snapshot field variable.

So all the iterator methods will work on this snapshot method. So even if there is any change in the original collection, no exception will be thrown. But note the the iterator will not reflect the latest state of the collection.

```java
Iterator<Integer> itr = integers.iterator();
while (itr.hasNext()) {
    int a = itr.next();
    if (a == 1) {
        integers.remove(Integer.valueOf(a));
    }
    System.out.print(a);
}
```

Note: although it does not throw any exception, but the downsides of this iterator are:
1. They will not reflect the latest state of the collection.
2. It requires extra memory as it clones the collection.


Reference

https://medium.com/@mr.anmolsehgal/fail-fast-and-fail-safe-iterations-in-java-collections-11ce8ca4180e#:~:text=Fail%2Dsafe%20iterators%20means%20they,modified%20while%20iterating%20over%20it.

