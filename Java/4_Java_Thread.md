# Java Thread 

### What is Single Thread

A single thread is basically a lightweight and smallest unit of processing. Java uses thread by using a "Thread Class".

There are two types of thread - **user thread and daemon thread**(daemon threads are used when we want to clean the application and are used in the background)

when an application first begins, user thread is created. After that, we can create many user threads and daemon threads. 
```java
package demotest;

public class GuruThread
{
       public static void main(String[] args) {
              System.out.println("Single Thread");
       }
}
```

Advantages of single thread:

1. Reduces overhead in the application as single thread execute in the system.

2. Also, it reduces the maintenance cost of the application.


### What is Multithreading

Multithreading in Java a process of executing two or more threads simultaneously(同时地) to maximum utilization of CPU.

Multithreading applications are where two or more threads run **concurrency**(并发的, 同时的). This multithreading is done, when multiple processes share common resources like CPU, Memory, etc. 

Each thread runs parallel to each other. Threads don't allocate seperate memory area; hence it saves memory. Also, context switching between threads less time. 
```java
package fundamental;

public class ThreadStudy implements Runnable
{
    public static void main(String[] args) {
        Thread guruThread1 = new Thread("Guru1");
        Thread guruThread2 = new Thread("Guru2");
        guruThread1.start();
        guruThread2.start();
        System.out.println("Thread names are following:");
        System.out.println(guruThread1.getName());
        System.out.println(guruThread2.getName());
    }
    @Override
    public void run() {
        
    }

}
```
Advantages of multithread:

1. The users are not blocked because threads are independent, and we can perform public multiple operations at times. 

2. As such the threads are independent, the other threads won't get effected if one thread meets a exception.


### Create Threads by Implementing a Runnable Interface

If your class is intended to be executed as a thread then you can achieve this by implementing a **Runnable** interface. You will need to follow three basic steps:

1. You need to implement a run() method provied by **Runnable** interface. This method provides an entry point for the thread and you will put your complete business logic inside this method. 
```java
public void run( )
```

2. You will instantiate a **Thread** object using the following constructor - 
```java
Thread(Runnable threadObj, String threadName);
```
Where, threadObj is an instance of a class that implements the **Runnable** interface and **threadName** is the name given to the new thread.

3. Once a Thread object is created, you can start it by calling **start()** method, which executes a call to run() method.
```java
void start();
```

Here is an exmaple:
```java
package fundamental.threadstudy;

class TestRunnable implements Runnable
{
    private Thread t;
    private String threadName;

    TestRunnable(String threadName)
    {
        this.threadName = threadName;
        System.out.println("Creating thread " + threadName);
    }

    @Override
    public void run()
    {
        System.out.println("Running thread " + threadName);
        try
        {
            for(int i = 4; i > 0; i--)
            {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Thread " + threadName + " interrupted");
        }

        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start()
    {
        System.out.println("Starting thread " + threadName);
        if(t == null)
        {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class RunnableDemo
{
    public static void main(String[] args) {
        TestRunnable R1 = new TestRunnable("Thread-1");
        R1.start();

         TestRunnable R2 = new TestRunnable("Thread-2");
        R2.start();
    }

}

/**
Creating thread Thread-1
Starting thread Thread-1
Creating thread Thread-2
Running thread Thread-1
Starting thread Thread-2
Running thread Thread-2
Thread: Thread-1, 4
Thread: Thread-2, 4
Thread: Thread-2, 3
Thread: Thread-1, 3
Thread: Thread-1, 2
Thread: Thread-2, 2
Thread: Thread-1, 1
Thread: Thread-2, 1
Thread Thread-1 exiting.
Thread Thread-2 exiting.
/**
```

### Create Threads by extending a Thread Class

The second way to create a thread is to create a new class that extends **Thread** class using following simple steps.

1. you need to override **run()** method available in Thread class. This method provides an entry point for the thread and you will put your complete business logic inside this method. 
```java
public void run( )
```

2. Once Thread object is created, you can start it by calling **start()** method, which executes a call to run( ) method.
```java
void start();
```

Here is an exmaple:
```java
package fundamental.threadstudy;

class TestThread extends Thread
{
    private Thread t;
    private String threadName;

    TestThread(String threadName)
    {
        this.threadName = threadName;
        System.out.println("Creating thread " + threadName);
    }

    @Override
    public void run()
    {
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
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

public class ThreadDemo
{
    public static void main(String[] args) {
        TestThread R1 = new TestThread("Thread-1");
        R1.start();

        TestThread R2 = new TestThread("Thread-2");
        R2.start();
    }
}
```

Some of the commonly used methods for threads are

|  Method |  Description  |
|---|---|
| public void start()  | This method starts the execution of the thread and JVM calls the run method on the thread   |
| public void wait() | This method causes current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object. |
| public void run()  | If this Thread object was instantiated using a seperate Runnable target, the run() method is invoked on the Runnable object |
| public final void setName(String name) | Changes the name of the Thread object. There is also a getName() method for retriving the name |
| public final void setPriority(int priority) | Sets the priority of this Thread object. The possible values are between 1 - 10 |
| public final void setDaemon(boolean on) | A parameter of true denotes this thread as a daemon Thread |
| public final void join(long millisec) | The current thread invokes this method on a second thread, causing current thread to block until the second thread termiantes or the specific number of milliseconds passes |
| public void interrupt() | Interrupts this thread, causing it to continue execution if it was blocked for any reason |
| public final boolean isAlive() | Returns true if the thread is alive, which is any time after the thread has been started before it runs to completion |

The previous methods are invoked on a particular Thread object. The following methods in the Thread class are static. Invoking one of the static methods performs the operation on the currently running thread.

|  Method |  Description  |
|---|---|
| public void yield()  | Causing the currently running thread to yield to any other threads of the same priority that are waiting to be scheduled |
| public static void sleep(long millisec) | Causes the currently running thread to block for at least the specified number of milliseconds |
| public static boolean holdsLock(Object x) | Returns true if the current thread holds the lock on the given Object |
| public static Thread currentThread() | Returns a reference to the currently running thread, which is the thread that invokes this method |
| public static void dumpStack() | Prints the stack trace for the currently running thread, which is useful when debugging a multithreaded application. |

Here is an example
```java
package fundamental.threadstudy;

class DisplayMessage implements Runnable
{
    private String message;

    DisplayMessage(String message)
    {
        this.message = message;
    }

    @Override
    public void run()
    {
        while(true) {
            System.out.println(message);
        }
    }
}

class GuessANumber extends Thread
{
    private int number;

    public GuessANumber(int number)
    {
        this.number = number;
    }

    @Override
    public void run()
    {
        int counter = 0;
        int guess = 0;
        do {
            guess = (int) (Math.random() * 100 + 1);
            System.out.println(this.getName() + " guesses " + guess);
            counter++;
        }
        while(guess != number);

        System.out.println("** Correct!" + this.getName() + "in" + counter + "guesses.**");
    }
}

public class ThreadMethodsTest
{
    public static void main(String[] args)
    {
        Runnable hello = new DisplayMessage("Hello");
        Thread thread1 = new Thread(hello);
        thread1.setDaemon(true);
        thread1.setName("Hello");
        System.out.println("Starting hello thread...");
        thread1.start();

        Runnable bye = new DisplayMessage("Goodbye");
        Thread thread2 = new Thread(bye);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.setDaemon(true);
        System.out.println("Starting goodbye thread...");
        thread2.start();

        System.out.println("Starting thread3...");
        Thread thread3 = new GuessANumber(27);
        thread3.start();

        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
        System.out.println("Starting thread4...");
        Thread thread4 = new GuessANumber(75);

        thread4.start();
        System.out.println("main() is ending...");
    }
}
```





### Thread Life Cycle in Java

1. **New**: In this phase, the thread is created using class "Thread class". It remains in this state till the program **starts** the thread. It is also known as born thread.

2. **Runnable**: In this page, the instance of the thread is invoked with a start method. **The thread control is given to scheduler to finish the execution. It depends on the Scheduler, whether to run the thread.**

3. **Running**: When the thread starts executing, then the state is changed to "running" state. The scheduler selects on thread from the thread pool, and it starts executing in the applicaiton.

4. **Blocked/Waiting**: This is the state when a thread has to wait. As there multiple threads are running in the application, there is a need for synchronization(同时发生的) between threads. Hence, one thread has to wait,till other thread gets executed. Therefore, this state is referred as waiting state.

5. **Timed Waiting**: A runnable thread can enter the timed waiting state for a specific interval of time. A thread in this state transitions back to the runnable state when that time interval expires or when the event it is waiting for occurs.

6. **Terminated/Dead**: This is the state when the thread is terminated. The thread is in running state and as soon as it completed processing it is in "dead state".

### Thread Priorities

Every Java thread has priority that helps operation system determine the order in which threads are scheduled.

Java thread priorities are in range between MIN-PRIORITY (a constant of 1) and MAX_PRIORITY (a constant of 10). By default, every thread is given priority NORMAL_PRIORITY(a constant of 5).

Threads with higher priority are more important to a program and should be allocated processor time before lower-priority threads. However, thread priorities cannot guarantee the order in which threads execute and are very much platform dependent.


### Optimistic(乐观的) and Pessimistic(悲观的) Locking

Traditional locking mechanisms **using synchronized keyword in Java, is said to be pessimistic technique** of locking or multi-threading. It asks you to first guarantee that no other thread will interfere in between certain operation and then only allow you access to any instance/method.

*It's much like saying "please close the door first; otheriwe some other crook will come in and rearrange your stuff."*

Though above approach is safe and it does work, but it put a significant penalty on your application in terms of performance. Reason is simple that waiting threads can not do anything unless they also get a chance and perform the guarded operation.

There exist one more approach which is more efficient in performance, and it optimistic in nature. In this approach, you proceed with an update, being hopeful that you can complete it without interference. This approach relies on collision detection to determine if there has been interference from other parties during the update, in which case the operation fails and can be retried (or not).

*The optimistic approach is like the old saying, “It is easier to obtain forgiveness than permission”, where “easier” here means “more efficient”.*

**Compare and Swap** is a good example of such optimistic approach.

### Compare and Swap Algorithm

This algorithm compares the contents of a memory location to a given value and, only if they are the same, modifies the content of that memory location to a given new value. 

There are 3 parameters for a CSA operation:

1. A memory location V where value has to be placed. 
2. Old value A which was read by thread last time.
3. New value B which should be written over V.


### Java synchronized keyword

**Java synchronized keyword** marks a block or method a critical section. A critical section is where one and only one thread is executing at a time, and the thread holds the lock for synchronized section.

The **synchronized** keyword can be use with:

1. a code block
2. a method

```java
synchronized( lockObject ) 
{
   // synchronized statements
}
```

When a thread wants to execute synchronized statements inside the synchronized block,it MUST acquire the lock on lockObject‘s monitor. At a time, only one thread can acquire the monitor of a lock object. So all other threads must wait till this thread, currently acquired the lock, finish it’s execution.

In this way, synchronized keyword guarantees that only one thread will be executing the synchronized block statements at a time, and thus prevent multiple threads from corrupting the shared data inside the block.

Keep in mind that if a thread is put on sleep (using sleep() method) then it does not release the lock. At this sleeping time, no thread will be executing the synchronized block statements.

Java synchronization will throw NullPointerException if lock object used in 'synchronized (lock)' is null.

Here are two examples:
```java
public class MathClass 
{
    void printNumbers(int n) throws InterruptedException 
    {
        synchronized (this) 
        {
            for (int i = 1; i <= n; i++) 
            {
                System.out.println(Thread.currentThread().getName() + " :: "+  i);
                Thread.sleep(500);
            }
        }
    }
}

public class MathClass 
{
    synchronized void printNumbers(int n) throws InterruptedException 
    {
        for (int i = 1; i <= n; i++) 
        {
            System.out.println(Thread.currentThread().getName() + " :: "+  i);
            Thread.sleep(500);
        }
    }
}

public class Main 
{
    public static void main(String args[]) 
    {
        final MathClass mathClass = new MathClass();
 
        //first thread
        Runnable r = new Runnable() 
        {
            public void run() 
            {
                try {
                    mathClass.printNumbers(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
       
        new Thread(r, "ONE").start();
        new Thread(r, "TWO").start();
    }
}
```

### Object level lock vs Class level lock

#### Object level lock in Java

**Object level lock** is mechanism when we want to synchronize a **non-static method** or **non-static code block** such that only one thread will be able to execute the code block on given instance of the class. This should always be done to make **instance level data thread safe**.
```java
public class DemoClass
{
    public synchronized void demoMethod(){}
}
 
or
 
public class DemoClass
{
    public void demoMethod(){
        synchronized (this)
        {
            //other thread safe code
        }
    }
}
 
or
 
public class DemoClass
{
    private final Object lock = new Object();
    public void demoMethod(){
        synchronized (lock)
        {
            //other thread safe code
        }
    }
}
```
#### Class level lock in Java

**Class level lock** prevents miltiple threads to enter in *synchronized* block in any of all available instances of the class on running time. This means if in runtime there are 100 instances of *DemoClass*, then only one thread will be execute *demoMethod()* in any one of instance at a time, and all other instances will be locked for other threads. 

Class level lokcing should always be done **to make static data field safe**. As we know that **static** keyword associate data of method to class level, so use locking at static fields or methods to make it on class level.
```java
public class DemoClass
{
    //Method is static
    public synchronized static void demoMethod(){
 
    }
}
 
or
 
public class DemoClass
{
    public void demoMethod()
    {
        //Acquire lock on .class reference
        synchronized (DemoClass.class)
        {
            //other thread safe code
        }
    }
}
 
or
 
public class DemoClass
{
    private final static Object lock = new Object();
 
    public void demoMethod()
    {
        //Lock object is static
        synchronized (lock)
        {
            //other thread safe code
        }
    }
}
```

1. According to the Java language specification you can not use synchronized keyword with constructor. It is illegal and result in compilation error.

2. Do not synchronize on non final field on synchronized block in Java. Because reference of non final field may change any time and then different thread might synchronizing on different objects i.e. no synchronization at all.

3. Do not use String literals because they might be referenced else where in the application and can cause deadlock. String objects created with new keyword can be used safely. But as a best practice, create a new private scoped Object instance OR lock on the shared variable itself which we want to protect. 


#### wait(), notify() and notifyAll() 

##### wait()

It tells the calling method to give up the lock and go to sleep until some other thread enters the same monitor and calls *notify()*. The *wait()* method releases the lock prior to waiting and reacquires the lock prior to returning from the *wait()* method. The *wait()* method is actually tightly integrated with the synchronization lock, using the feature not available directly from the synchronization mechanism.
```java
synchronized( lockObject )
{ 
    while( ! condition )
    { 
        lockObject.wait();
    }
     
    //take the action here;
}
```

##### notify() and notifyAll()

It wakes up one single thread that called *wait()* on the same object. It should be noted that calling *notify()* does not actually give up a lock on a resource. It tells a waiting thread that thread can wake up. However, the lock is not actually given up until the modifier's synchronised block has completed.
```java
synchronized( lockObject )
{ 
   //establish_the_condition;
 
    lockObject.notify();
     
    //any additional code if needed
}
```

**notifyAll()** wakes up all the threads that called **wait()** on the same object. The hightest priority thread will run first in most of situation, though not guaranteed.





Reference:

https://www.javaworld.com/article/2077138/introduction-to-java-threads.html

https://www.guru99.com/multithreading-java.html

https://www.jianshu.com/p/a3f9f2c3ecf8

https://www.tutorialspoint.com/java/java_multithreading.html

https://howtodoinjava.com/java-concurrency-tutorial/