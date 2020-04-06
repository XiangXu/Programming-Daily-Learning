# Java Garbage Collection

Garbage collection is the process of looking at heap memory, identifying which objects are in use and which are not, and deleting the unused objects. An in use object, or a referenced object, means that some part of your program still maintains a pointer to that object. An unused object, or unreferenced object, is no longer referenced by any part of your program. So the memory used by an unreferenced object can be reclaimed. 

## Step 1: Marking

The first step in the process is called **Marking**. This is where the garbage collector identifies which pieces of memory are in use and which are not.

![alt text][marking]

[marking]: https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide3.png

All objects are scanned in the marking phase to make this determination. This can be a very time consuming process if all objects in a system must be scanned.

## Step 2: Normal Deletion

Normal deletion removes unreferenced objects leaving referenced objects and pointers to free space. 

![alt text][deletion]

[deletion]: https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide1b.png

The memeory allocator holds references to blocks of free space where new object can be allocated. 

## Step 2a: Deletion with Compacting 

The further improve performance, in addition to deleting unreferenced objects, you can also compact the remaining referenced objects. By moving referenced object together, this makes new memory allocation much easier and faster.

![alt text][Compacting]

[Compacting]: https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide4.png


## Why Generational Garbage Collection?

As stated earlier, having to mark and compact all the objects in a JVM is inefficient. As more and more objects are allocated, the list of objects grows and grows leading to longer and longer garbage collection time. However, empirical(以经验为依据的) analysis of applications has shown that most objects are short lived. 

Here is an example of such data. The Y axis shows the number of bytes allocated and the X access shows the number of bytes allocated over time.

![alt text][Empircial]

[Empircial]:https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/ObjectLifetime.gif

As you can see, fewer and fewer objects remain allocated over time. In fact most objects have a very short life as shown by the higher values on the left side of the graph.

## JVM Generations

The information learned from the object allocation behavior can be used to enhance the performance of the JVM. Therefore, the heap is borken up into smaller parts or generations. The heap parts are: Young generation, Old generation and Perment Generation.

![alt text][Generation]

[Generation]:https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide5.png


The **Young Generation** is where all new objects are allocated and aged. When the young generation fills up, this causes a **minor garbage collection**. Minor collections can be optimized assuming a high object mortality(生命有限的) rate. A young generation full of dead objects is collected very quickly. Some surviving objects are aged and eventually move to the old generation. 

**Stop the World Event** - All minor garbage collections are "Stop the World" events. This means that all application threads are stopped until opertaion completes. Minor garbage collections are always Stop the World Event. 

The **Old Generation** is used to store long surviving objects. Typically, a threshold(门槛) is set for young generation object and when that age is met, the object gets moved to the old generation. Eventually the old generation needs to be collected. This event is called a **major garbage collection**.

Major garbage collection are also Stop the World events. Ofen a major collection is much slower because it involves all live objects. So for responsive applications, major garbage collections should be minimied. Also note, that the length of the Stop the World event for a major garbage collection is affected by the kind of garbage collector is used for the old generation space. 

The **Permanent generation** contains metadata required by the JVM to describe the classes and methods used in application. The permanent generation is populated by the JVM at runtime based on classes in use by the application. In addition, Java SE library classes and methods may be stored here.

Classes may get collected (unloaded) if the JVM finds they are no longer needed and space may be needed for other classes. the perment generation is included in full garbage collection.


Reference:

https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html