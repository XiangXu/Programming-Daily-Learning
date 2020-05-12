# Java Garbage Collection

Garbage collection is the process of looking at heap memory, identifying which objects are in use and which are not, and deleting the unused objects. An in use object, or a referenced object, means that some part of your program still maintains a pointer to that object. An unused object, or unreferenced object, is no longer referenced by any part of your program. So the memory used by an unreferenced object can be reclaimed. 

## Step 1: Marking

The first step in the process is called **Marking**. This is where the garbage collector identifies which pieces of memory are in use and which are not.

![marking](/Java/images/garbage_collection/Slide3.png)

All objects are scanned in the marking phase to make this determination. This can be a very time consuming process if all objects in a system must be scanned.

## Step 2: Normal Deletion

Normal deletion removes unreferenced objects leaving referenced objects and pointers to free space. 

![deletion](/Java/images/garbage_collection/Slide1b.png)

The memeory allocator holds references to blocks of free space where new object can be allocated. 

## Step 2a: Deletion with Compacting 

The further improve performance, in addition to deleting unreferenced objects, you can also compact the remaining referenced objects. By moving referenced object together, this makes new memory allocation much easier and faster.

![Compacting](/Java/images/garbage_collection/Slide4.png)


## Why Generational Garbage Collection?

As stated earlier, having to mark and compact all the objects in a JVM is inefficient. As more and more objects are allocated, the list of objects grows and grows leading to longer and longer garbage collection time. However, empirical(以经验为依据的) analysis of applications has shown that most objects are short lived. 

Here is an example of such data. The Y axis shows the number of bytes allocated and the X access shows the number of bytes allocated over time.

![Empircial](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/ObjectLifetime.gif)

As you can see, fewer and fewer objects remain allocated over time. In fact most objects have a very short life as shown by the higher values on the left side of the graph.

## JVM Generations

The information learned from the object allocation behavior can be used to enhance the performance of the JVM. Therefore, the heap is borken up into smaller parts or generations. The heap parts are: Young generation, Old generation and Perment Generation.

![Generation](/Java/images/garbage_collection/Slide5.png)


The **Young Generation** is where all new objects are allocated and aged. When the young generation fills up, this causes a **minor garbage collection**. Minor collections can be optimized assuming a high object mortality(生命有限的) rate. A young generation full of dead objects is collected very quickly. Some surviving objects are aged and eventually move to the old generation. 

**Stop the World Event** - All minor garbage collections are "Stop the World" events. This means that all application threads are stopped until opertaion completes. Minor garbage collections are always Stop the World Event. 

The **Old Generation** is used to store long surviving objects. Typically, a threshold(门槛) is set for young generation object and when that age is met, the object gets moved to the old generation. Eventually the old generation needs to be collected. This event is called a **major garbage collection**.

Major garbage collection are also Stop the World events. Often a major collection is much slower because it involves all live objects. So for responsive applications, major garbage collections should be minimied. Also note, that the length of the Stop the World event for a major garbage collection is affected by the kind of garbage collector is used for the old generation space. 

The **Permanent generation** contains metadata required by the JVM to describe the classes and methods used in application. The permanent generation is populated by the JVM at runtime based on classes in use by the application. In addition, Java SE library classes and methods may be stored here.

Classes may get collected (unloaded) if the JVM finds they are no longer needed and space may be needed for other classes. the perment generation is included in full garbage collection.


## The generational Garbage Collection Process

Now that you understand why the heap is separted into different generations. It is time to look at how exactly these spaces interact. The pictures that follow walks through the object allocation and aging process in the JVM.

1. First, any objects are allocated to the eden space. Both survivor spaces start out empty. 

![First](/Java/images/garbage_collection/Slide13.png)

2. When the eden space fills up, a minor garbage collection is triggered.

![Second](/Java/images/garbage_collection/Slide14.png)

3. Referenced objects are moved to the first survivor space. Unreferenced objects are deleted when the eden space is cleared.

![Third](/Java/images/garbage_collection/Slide6.png)

4. At the next time minor GC, the same thing happens for the eden space. Unreferenced objects are deleted and referenced objects are moved to a survivor space. However, in this case, they are moved to the second survivor space(s1). In addition, objects from the last minor GC on the first survivor space(s0) have their age incremented and get moved to S1. Once all surviving objects have been moved to S1, both S0 and eden are cleared. Notice we now have differently aged object in the survivor space.

![Fourth](/Java/images/garbage_collection/Slide8.png)

5. At the next minor GC, the same process repeats. However this time the survivor spaces switch. Referenced objects are moved to S0. Surviving objects are aged. Eden and S1 are cleared.

![Fifth](/Java/images/garbage_collection/Slide9.png)


6. This slide demonstrates promotion. After a minor GC, when aged objects reach a certain age threshold(8 in this example) they promoted from young generatino to old generation.

![Sixth](/Java/images/garbage_collection/Slide7.png)

7. As minor GCs continue to occure objects will continue to be promoted to the old generation sapce.

![Seventh](/Java/images/garbage_collection/Slide10.png)

8. So that pretty much convers the entire process with the young generation. Eventually, a major GC will be performed on the old generation which cleans up and compacts that space.

![Eighth](/Java/images/garbage_collection/Slide11.png)



## PermGen vs Metaspace

### PermGen

**PermGen is a special heap space seperated from the main memory heap**.

The JVM keeps track of loaded class metadata in the PermGen. Additionally, the JVM stores all the static content in this memory section. This includes all the static methods, primitive variables, and references to the static objects.

Furthermore, it contains data about byte code, names and JIT information. Before Java 7, the **String Pool was also part of this memory**. The disadvantages of the fixed pool size: the JVM placed the Java String Pool in the PermGen space, which has a fixed size — it can't be expanded at runtime and is not eligible for garbage collection.

The default maximum memory size for 32-bit JVM is 64 MB and 82 MB for the 64-bit version.

However, we can change the default size with the JVM options:

-XX:PermSize=[size] is the initial or minimum size of the PermGen space
-XX:MaxPermSize=[size] is the maximum size

With its limited memory size, PermGen is involved in generating the famous OutOfMemoryError. Simply put, the class loaders aren't garbage collected properly and, as a result, generated a memory leak.

### Metaspace

From Java 8 version, Metaspace has been implemented to replace the older PermGen meemory space. **This native memory region grows automatically by default**.

* MetaspaceSize and MaxMetaspaceSize – we can set the Metaspace upper bounds.
  
* MinMetaspaceFreeRatio – is the minimum percentage of class metadata capacity free after garbage collection.

* MaxMetaspaceFreeRatio – is the maximum percentage of class metadata capacity free after a garbage collection to avoid a reduction in the amount of space.

Reference:

https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html

https://www.baeldung.com/java-permgen-metaspace