# Java Memory Model

**The Java Memory Model specifies how the Java virtual machine works with computer's memory(RAM). It specifies how and when different threads can see values written to shared variables by other threads, and how to sychnorize access to shared variables when necessary.**

## The Internal Java Model

The Java memory model used internally in the JVM divides memory between thread stacks and the heap. 

Each thread running in the Java virtual machine has its own thread stack.
The thread stack contains information about what methods the thread has called to reach the current point of execution. As the thread executes its code, the call stack changes.

The thread also contains all local variables for each method being executed. **A thread can only access it's own thread stack. Local varaibles created by a thread are invisable to all other threads than the thread who created it.** Even if two threads are executing the exact same code, the two threads will still create the local variables of that code in each their own thread stack. Thus, each thread has its own version of each local variable.

**All local variables of primitive types ( boolean, byte, short, char, int, long, float, double) are fully stored on the thread stack and are thus not visible to other threads**. One thread may pass a copy of a pritimive variable to another thread, but it cannot share the primitive local variable itself.

**The heap contains all objects created in your Java application, regardless of what thread created the object.** This includes the object versions of the primitive types (e.g. Byte, Integer, Long etc.). It does not matter if an object was created and assigned to a local variable, or created as a member variable of another object, the object is still stored on the heap.

![model2](/Java/images/memory_model/java-memory-model-2.png)

**A local variable may be of a primitive type, in which case it is totally kept on thread stack.**

**A local variable may also be a reference to an object. In that case the reference is stroed on the thread stack, but the object itself is stored on the heap.**

**An object may contain methods and these methods may contain local variables. These local variables are also stored on the thread stack, even if the object method belongs to is stored on the heap.**

**An object's member variables are stored on the heap along with the object itself. That is true both when the member variables is of a primitive type, and if it is a reference to an object.** 

**Static class variables are also stored on the heap along with the class definition.**

**Objects on the heap can be accessed by all threads that have a reference to the object. When a thread has access to an object, it can also get access to that object's member variables. If two threads call a method on the same object at the same time, they will both have access to the object's member variables, but each thread will have its own copy of the local variables.**

![model3](/Java/images/memory_model/java-memory-model-3.png)

## Hardware Memory Architecture

![model4](/Java/images/memory_model/java-memory-model-4.png)

A modern computer often has 2 or more CPUs in it. Some of these CPUs may have multiple cores too. The point is, that on a modern computer with 2 or more CPUs it is possible to have more than one thread running simultaneously insdie your Java application.

**Each CPU contains a set of registers which are essentially in-CPU memory.** The CPU can perform operations much faster on these registers than it can perform on variables in main memory. That is because the CPU can access these registers much faster than it can access main memory.

**Each CPU may also have a CPU cache memory layer.** In fact, **most modern CPUs have a cache memory layer of some size. The CPU can access its cache memory much faster than main memory, but typically not as fast as it can access its internal registers.** So, the CPU cache memory is somewhere in between the speed of the internal registers and main memory. Some CPUs may have multiple cache layers (Level 1 and Level 2).

**A computer also contains a main memory area (RAM). All CPUs can access the main memory. The main memory area is typically much bigger than the cache memories of the CPUs.**

Typically, when a CPU needs to access main memory it will read part of main memory into its CPU cache. It may even read part of the cache into its internal registers and then perform operations on it. When the CPU needs to write the result back to main memory it will flush the value from its internal register to the cache memory, and at some point flush the value back to main memory.

The values stored in the cache memory is typically flushed back to main memory when the CPU needs to store something else in the cache memory. The CPU cache can have data written to part of its memory at a time, and flush part of its memory at a time. It does not have to read / write the full cache each time it is updated. **Typically the cache is updated in smaller memory blocks called "cache lines".** One or more cache lines may be read into the cache memory, and one or mor cache lines may be flushed back to main memory again.

## Bridging The Gap Between The Java Memory Model And The Hardware Memory Architecture

**On the hardware, both the thread stack and the heap are located in main memory. Parts of the thread stacks and heap may sometimes be present in CPU caches and in internal CPU registers.**

![model5](/Java/images/memory_model/java-memory-model-5.png)


Reference:

http://tutorials.jenkov.com/java-concurrency/java-memory-model.html