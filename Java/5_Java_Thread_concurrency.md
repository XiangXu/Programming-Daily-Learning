# Java Thread Synchronization(同时发生的)

When we start two or more threads whithin a program, there may be a situation when multiply threads try to access the same resource and finally they can produce unforeseen(未预料到的) result due to concurrency issues. For example, if multiple threads try to write within a same file then they may corrupt the data because one of the threads can override data or while one thread is opening the same file at the same time another thread might be closing the same file.

So there is a need to synchronize the action of multiple threads and make sure that only one thread can access the resource at a given point in time. This is implemented using a concept called **monitors**. Each object in Java is associated with a mintor, which thread can lock or unlock. Only one thread at a time may hold a lockon a monitor.

Java programming language provides a very handy way of creating threads and synchronizing their task by using **synchronized** blocks. You keep shared resources within this block. Following is the general form of the synchronized statement −

Syntax
```java
synchronized(objectidentifier) {
   // Access shared variables and other shared resources
}
```

Here, the objectidentifier is a reference to an object whose lock associates with the monitor that the synchronized statement represents. Now we are going to see two examples, where we will print a counter using two different threads. When threads are not synchronized, they print counter value which is not in sequence, but when we print counter by putting inside synchronized() block, then it prints counter very much in sequence for both the threads.

### Multithreading Exmaple without Synchronizatoin
```java
package fundamental.threadstudy;

class PrintDemo {
    public void printCount() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   "  + i  + " Thread name: " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class MultithreadThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    PrintDemo  PD;

    MultithreadThreadDemo( String name,  PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        PD.printCount();
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

public class MultithreadingDemo
{
    public static void main(String[] args) {

        PrintDemo PD = new PrintDemo();

        MultithreadThreadDemo T1 = new MultithreadThreadDemo( "Thread - 1 ", PD );
        MultithreadThreadDemo T2 = new MultithreadThreadDemo( "Thread - 2 ", PD );

        T1.start();
        T2.start();

        // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch ( Exception e) {
            System.out.println("Interrupted");
        }
    }
}
/**
Starting Thread - 1 
Starting Thread - 2 
Counter   ---   5 Thread name: Thread - 2 
Counter   ---   5 Thread name: Thread - 1 
Counter   ---   4 Thread name: Thread - 2 
Counter   ---   4 Thread name: Thread - 1 
Counter   ---   3 Thread name: Thread - 2 
Counter   ---   3 Thread name: Thread - 1 
Counter   ---   2 Thread name: Thread - 2 
Counter   ---   2 Thread name: Thread - 1 
Counter   ---   1 Thread name: Thread - 2 
Counter   ---   1 Thread name: Thread - 1 
Thread Thread - 1  exiting.
Thread Thread - 2  exiting.

```

### Multithreading Exmaple with Synchronizatoin
```java
package fundamental.threadstudy;

class PrintDemo {
    public void printCount() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   "  + i  + " Thread name: " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class MultithreadThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    private final PrintDemo PD;

    MultithreadThreadDemo( String name,  PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        synchronized (PD) {
            PD.printCount();
        }

        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

public class MultithreadingDemo
{
    public static void main(String[] args) {

        PrintDemo PD = new PrintDemo();

        MultithreadThreadDemo T1 = new MultithreadThreadDemo( "Thread - 1 ", PD );
        MultithreadThreadDemo T2 = new MultithreadThreadDemo( "Thread - 2 ", PD );

        T1.start();
        T2.start();

        // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch ( Exception e) {
            System.out.println("Interrupted");
        }
    }
}

/**
Starting Thread - 1 
Starting Thread - 2 
Counter   ---   5 Thread name: Thread - 1 
Counter   ---   4 Thread name: Thread - 1 
Counter   ---   3 Thread name: Thread - 1 
Counter   ---   2 Thread name: Thread - 1 
Counter   ---   1 Thread name: Thread - 1 
Counter   ---   5 Thread name: Thread - 2 
Counter   ---   4 Thread name: Thread - 2 
Counter   ---   3 Thread name: Thread - 2 
Counter   ---   2 Thread name: Thread - 2 
Counter   ---   1 Thread name: Thread - 2 
Thread Thread - 1  exiting.
Thread Thread - 2  exiting.
**/
```

### Interthread Communication
|  Method |  Description  |
|---|---|
| public void wait() | Causes the current thread to wait until another thread invokes the notify()   |
| public void notify() | Wakes up a single thread that is waiting on this object's monitor |
| public void notifyAll() | Wakes up all the threads that called wait( ) on the same object |

Those methods have been implemented as **final** methods in Objects, so they are available in all classes. All three methods can be called only from within a **synchronized** context.
```java
package fundamental.threadstudy;

class Chat
{
    private boolean flag = false;

    synchronized void Question(String msg)
    {
        if(flag)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println(msg);
        flag = true;
        notify();
    }

    synchronized void Answer(String msg) {
        if (!flag)
        {
            try
            {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(msg);
        flag = false;
        notify();
    }
}

class T1 implements Runnable
{
    private Chat m;
    private String[] s1 = { "Hi", "How are you ?", "I am also doing fine!" };

    T1(Chat m1)
    {
        this.m = m1;
        new Thread(this, "Question").start();
    }

    public void run()
    {
        for (String s : s1) {
            m.Question(s);
        }
    }
}

class T2 implements Runnable {
    private Chat m;
    private String[] s2 = { "Hi there", "I am good, what about you?", "Great!" };

    T2(Chat m2) {
        this.m = m2;
        new Thread(this, "Answer").start();
    }

    public void run() {
        for (String s : s2) {
            m.Answer(s);
        }
    }
}


public class ThreadNotifyDemo {
    public static void main(String[] args) {
        Chat m = new Chat();
        new T1(m);
        new T2(m);
    }
}
/**
Hi
Hi there
How are you ?
I am good, what about you?
I am also doing fine!
Great!
**/
```

### Java sleep() and wait()

**sleep()** is a method which is used to pause the process for few seconds or the time we want to.

**wait()** thread goes in waiting state and it won't come back atutomatically until we cann **notify()** or **notifyall()**

The major difference is that **wait()** release the lock or monitor while **sleep()** doesn't release the lock or monitor while waiting.

Thread.sleep() sends the current thread into the “Not Runnable” state for some amount of time. The thread keeps the monitors it has acquired — i.e. if the thread is currently in a synchronized block or method no other thread can enter this block or method. If another thread calls t.interrupt(). it will wake up the sleeping thread.

While sleep() is a static method which means that it always affects the current thread (the one that is executing the sleep method). **A common mistake is to call t.sleep() where t is a different thread; even then, it is the current thread that will sleep, not the t thread**.


### Lock vs Monitor

**Locks**: A lock is kind of data which is logically part of an object's header on the heap memory. Each object in a JVM has this lock that any program can use to coordinate multi-threaded access to this object.  If any thread want to access instance variables of that object; then thread must “own” the object’s lock (set some flag in lock memory area). All other threads that attempt to access the object’s variables have to wait until the owning thread releases the object’s lock (unset the flag).

Once a thread owns a lock, it can request the same lock again multiple times, but then has to release the lock the same number of times before it is made available to other threads. If a thread requests a lock three times, for example, that thread will continue to own the lock until it has “released” it three times.

Please note that lock is acquired by a thread, when it explicitly ask for it. In Java, this is done with the synchronized keyword, or with wait and notify.

**Monitor**: Monitor is a synchronization construct that allows threads to have both mutual exclusion (using locks) (互斥锁) and cooperation 

In other words, along with data that implements a lock, every Java object is logically associated with data that implements a wait-set. Whereas locks help threads to work independently on shared data without interfering with one another, wait-sets help threads to cooperate with one another to work together towards a common goal e.g. all waiting threads will be moved to this wait-set and all will be notified once lock is released. This wait-set helps in building monitors with additional help of lock (mutex).

#### Mutual exclusion
Putting in very simple words, a monitor is like a building that contains one special room (object instance) that can be occupied by only one thread at a time. The room usually contains some data which needs to be protected from concurrent access. From the time a thread enters this room to the time it leaves, it has exclusive access to any data in the room. Entering the monitor building is called “entering the monitor.” Entering the special room inside the building is called “acquiring the monitor.” Occupying the room is called “owning the monitor,” and leaving the room is called “releasing the monitor.” Leaving the entire building is called “exiting the monitor.”

When a thread arrives to access protected data (enter the special room), it is first put in queue in building reception (entry-set). If no other thread is waiting (own the monitor), the thread acquires the lock and continues executing the protected code. When the thread finishes execution, it release the lock and exits the building (exiting the monitor).

If when a thread arrives and another thread is already owning the monitor, it must wait in reception queue (entry-set). When the current owner exits the monitor, the newly arrived thread must compete with any other threads also waiting in the entry-set. Only one thread will win the competition and own the lock.

There is no role of wait-set feature.


#### Cooperation
In general, mutual exclusion is important only when multiple threads are sharing data or some other resource. If two threads are not working with any common data or resource, they usually can’t interfere with each other and needn’t execute in a mutually exclusive way. Whereas mutual exclusion helps keep threads from interfering with one another while sharing data, cooperation helps threads to work together towards some common goal.

**Cooperation is important when one thread needs some data to be in a particular state and another thread is responsible for getting the data into that state e.g. producer/consumer problem** where read thread needs the buffer to be in a “not empty” state before it can read any data out of the buffer. If the read thread discovers that the buffer is empty, it must wait. The write thread is responsible for filling the buffer with data. Once the write thread has done some more writing, the read thread can do some more reading. It is also sometimes called a “Wait and Notify” OR “Signal and Continue” monitor because it retains ownership of the monitor and continues executing the monitor region (the continue) if needed. At some later time, the notifying thread releases the monitor and a waiting thread is resurrected to own the lock.

**Thiscooperation requires both i.e. entry-set and wait-set** . Below given diagram will help you in understand this cooperation.