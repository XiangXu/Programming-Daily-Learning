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

Class level locking should always be done **to make static data field safe**. As we know that **static** keyword associate data of method to class level, so use locking at static fields or methods to make it on class level.
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

2. **Do not synchronize on non final field on synchronized block in Java. Because reference of non final field may change any time and then different thread might synchronizing on different objects i.e. no synchronization at all**.

3. **Do not use String literals because they might be referenced else where in the application and can cause deadlock. String objects created with new keyword can be used safely. But as a best practice, create a new private scoped Object instance OR lock on the shared variable itself which we want to protect**. 


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
```java
package fundamental.threadstudy.wait_notify;

import java.util.List;

public class Consumer implements Runnable
{
    private final List<Integer> taskQueue;

    public Consumer(List<Integer> sharedQueue)
    {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run()
    {
        while (true) {
            try {
                consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (taskQueue)
        {
            while (taskQueue.isEmpty())
            {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting, size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(1000);
            int i = taskQueue.remove(0);
            System.out.println("Consumed: " + i);
            taskQueue.notifyAll();
        }
    }
}

package fundamental.threadstudy.wait_notify;

import java.util.List;

public class Producer implements Runnable
{
    private final List<Integer> taskQueue;
    private final int MAX_CAPACITY;

    public Producer(List<Integer> sharedQueue, int size)
    {
        this.taskQueue = sharedQueue;
        this.MAX_CAPACITY = size;
    }

    @Override
    public void run()
    {
        int counter = 0;
        while (true)
        {
            try
            {
                produce(counter++);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException
    {
        synchronized (taskQueue)
        {
            while(taskQueue.size() == MAX_CAPACITY)
            {
                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting, size " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(1000);
            taskQueue.add(i);
            System.out.println("Produced: " + i);
            taskQueue.notifyAll();
        }
    }
}


package fundamental.threadstudy.wait_notify;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExampleWithWaitAndNotify
{
    public static void main(String[] args)
    {
        List<Integer> taskQueue = new ArrayList<>();
        int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new Consumer(taskQueue), "Consumer");
        tProducer.start();
        tConsumer.start();
    }
}

```

#### Difference between yield() and join()

##### yield()

   1. yield is a static method and native too.
   2. yield tells the currently executing thread to give a chance to the threads that have equal priority in the Thread Pool.
   3. There is no guarantee that yield will make currently executing thread to runnable state immediately.
   4. It can only make a thread from Running State to Runnable State, not wait or blocked state.

```java
package fundamental.threadstudy;

class Producer extends Thread
{
    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("I am Producer : Produced Item " + i);
            Thread.yield();
        }
    }
}

class Consumer extends Thread
{
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("I am Consumer : Consumed Item " + i);
            Thread.yield();
        }
    }
}

public class YieldExample
{
    public static void main(String[] args)
    {
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
        consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority

        producer.start();
        consumer.start();
    }
}
/**
I am Producer : Produced Item 0
I am Consumer : Consumed Item 0
I am Producer : Produced Item 1
I am Consumer : Consumed Item 1
I am Producer : Produced Item 2
I am Consumer : Consumed Item 2
I am Producer : Produced Item 3
I am Consumer : Consumed Item 3
I am Producer : Produced Item 4
I am Consumer : Consumed Item 4
**/
```

##### join()

The join() method of a thread instance can be **used to "join" the start of a thread's execution to the end of another thread's execution** so that a thread will not start running until another thread has ended. 

**Giving a timeout within join(), will make the join() effect to be nullified after the specific timeout.**

```java
package fundamental.threadstudy;

public class JoinExample
{
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                System.out.println("First task started");
                System.out.println("Sleeping for 2 seconds");
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("First task completed");
            }
        });
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                System.out.println("Second task completed");
            }
        });
        t.start(); // Line 15
        t.join(); // Line 16
        t1.start();
    }
}

/**
Output:
 
First task started
Sleeping for 2 seconds
First task completed
Second task completed
**/
```

#### Difference between sleep() and wait()

**sleep()** is a method which is used to pause the process for few seconds or the time we want to.

**wait()** is a method which makes thread go in waiting state and it won't come back automatically until we call notify() or notifyAll().

The main major difference is the **wait()** releases the lock monitor while **sleep()** doesn't release lock or monitor while waiting. **wait()** is used for inter-thread communication while **sleep()** is used to introduce pause on execution, generally. 

Thread.sleep() sends the current thread into the “Not Runnable” state for some amount of time. The thread keeps the monitors it has acquired — i.e. if the thread is currently in a synchronized block or method no other thread can enter this block or method. If another thread calls t.interrupt(). it will wake up the sleeping thread.

While sleep() is a static method which means that it always affects the current thread (the one that is executing the sleep method). A common mistake is to call t.sleep() where t is a different thread; even then, it is the current thread that will sleep, not the t thread.


### Difference between lock and monitor 

#### Locks

**A lock is kind of data which is logically part of an object's header on the heap memory**. Each object in a JVM has this lock (or mutex) that any program can use to coordinate multi-threaded access to the object. If any thread want to access instance variables of that object; the thread must "own" the object's lock(set some flag in lock memory area). All other threads that attempt to access the object’s variables have to wait until the owning thread releases the object’s lock (unset the flag).

Once a thread owns a lock, it can request the same lock again multiple times, but then has to release the lock the same number of times before it is made available to other threads. If a thread requests a lock three times, for example, that thread will continue to own the lock until it has "released" it three times. 

Please note that lock is accquired by a thread, when it explicitly ask for it. In Java, this is done with the synchronized keyword, or with wait and notify.


#### Monitors

**Monitor is a synchronization construct that allows threads to have both mutual exclusion(using locks) and cooperation.** the ability to make threads wait for certain condition to be true(using **wait-set**).

In other words, along with data that implement a lock, every Java object is logically associated with data that implements **wait-set**. Whereas locks help threads to work independently on shared data without interfering with one other, wait-sets help threads to cooperate with one another to work together towards a common goal. all waiting threads will be moved to this wait-set and all will be notified once lock is released. **This wait-set helps building monitors with additional help of lock(mutex).**

#### Mutual exclusion

Putting in very simple words, a monitor is like a building that contains one special room (object instance) that can be occupied by only one thread at a time. The room usually contains some data which needs to be protected from concurrent(同时发生的) access. From the time a thread enters this room to the time it leaves, it has exclusive access to any data in the room. Entering the monitor building is called “entering the monitor.” Entering the special room inside the building is called “acquiring the monitor.” Occupying the room is called “owning the monitor,” and leaving the room is called “releasing the monitor.” Leaving the entire building is called “exiting the monitor.”

When a thread arrives to access protected data (enter the special room), it is first put in queue in building reception (entry-set). If no other thread is waiting (own the monitor), the thread acquires the lock and continues executing the protected code. When the thread finishes execution, it release the lock and exits the building (exiting the monitor).

If when a thread arrives and another thread is already owning the monitor, it must wait in reception queue (entry-set). When the current owner exits the monitor, the newly arrived thread must compete with any other threads also waiting in the entry-set. Only one thread will win the competition and own the lock.

**There is no role of wait-set feature.**

#### Cooperation

**Cooperation is important when one thread needs some data to be in particular state and another thread is responseible for getting data into that state. producer and consumer problem** where read thread needs the buffer to be in a “not empty” state before it can read any data out of the buffer. If the read thread discovers that the buffer is empty, it must wait. The write thread is responsible for filling the buffer with data. Once the write thread has done some more writing, the read thread can do some more reading. It is also sometimes called a **“Wait and Notify”** OR **“Signal and Continue”** monitor because it retains ownership of the monitor and continues executing the monitor region (the continue) if needed. At some later time, the notifying thread releases the monitor and a waiting thread is resurrected to own the lock.

This cooperation requires both i.e. **entry-set** and **wait-set**.

### Java Callable Future Example

One of the benefits of the Java executor framework is that we can run concurrent(同时发生的) task that may return a single result after processing the task. The Java Concurrenty API achieves thsi with the following two interfaces **Callable** and **Future**.

##### Callable

**Callable** interface has the *call()* method. In this method, we have to implemented the logic of a task. The **Callable** interface is a parameterized interface, meaning we have to indicate the type of data the **call()** method will return.

##### Future

**Future** interface has methods to obtain the result generated by a **Callable** and to manage its state. 

```java
package fundamental.threadstudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class FactorialCalculator implements Callable<Integer>
{

    private Integer number;

    public FactorialCalculator(Integer number)
    {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception
    {
        int result = 1;

        if(number == 0 || number == 1)
        {
            result =1;
        }
        else
        {
            for(int i=2; i <= number; i++)
            {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }

        System.out.println("Result for number - " + number + " -> " + result);
        return result;
    }
}

public class CallbackFutureStudy
{
    public static void main(String[] args)
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList = new ArrayList<>();

        Random random = new Random();

        for (int i=0; i<4; i++)
        {
            Integer number = random.nextInt(10);
            FactorialCalculator calculator  = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        for(Future<Integer> future : resultList)
        {
            try
            {
                System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        //shut down the executor service now
        executor.shutdown();
    }
}
/**
Result for number - 4 -> 24
Result for number - 6 -> 720
Future result is -  - 720; And Task done is true
Future result is -  - 24; And Task done is true
Result for number - 2 -> 2
Result for number - 6 -> 720
Future result is -  - 720; And Task done is true
Future result is -  - 2; And Task done is true
**/
```
Here we sent a **Callable** object to be executed in an executor using the **submit()** method. This method receives a **Callable** object as a parameter and returns a **Future** object that we can use with two main objectives:

1. **We can control the state of the task** - we can cancel the task and check if it has finished. For this purpose, we have used the **isDone()** method to check if the tasks had finished. 

2. **We can get the result returned by the call() method**. For this purpose, we have used the **get()** method. This method waits until the **Callable** object has finished the execution of the **call()** method and has returned its result. If the thread is interrupted while the get() method is waiting for the result, it throws an InterruptedException exception. If the call() method throws an exception, this method throws an ExecutionException exception. The Future interface provides another version of the get() method i.e. get(longtimeout,TimeUnitunit). This version of the get method, if the result of the task isn’t available, waits for it for the specified time. If the specified period of time passes and the result isn’t yet available, the method returns a null value.


### How to Restart Thread Using UncaughtExceptionHandler

```java
class ExceptionHandler implements UncaughtExceptionHandler
{
   public void uncaughtException(Thread t, Throwable e)
   {
      System.out.printf("An exception has been captured\n");
      System.out.printf("Thread: %s\n", t.getId());
      System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
      System.out.printf("Stack Trace: \n");
      e.printStackTrace(System.out);
      System.out.printf("Thread status: %s\n", t.getState());
      new Thread(new Task()).start();
   }
}

class Task implements Runnable
{
   @Override
   public void run()
   {
      Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
      System.out.println(Integer.parseInt("123"));
      System.out.println(Integer.parseInt("234"));
      System.out.println(Integer.parseInt("345"));
      System.out.println(Integer.parseInt("XYZ")); //This will cause NumberFormatException
      System.out.println(Integer.parseInt("456"));
   }
}
```

### AtomicInteger

The **AtomicInteger** class protects an underlying *int* value by providing methods that perform **atomic operation** on the value. It shall not be used as replacement for an **Integer** class.

#### Create, get and set value of AtomicInteger
```java
//Initial value is 0
AtomicInteger atomicInteger = new AtomicInteger();  
 
//Initial value is 100
AtomicInteger atomicInteger = new AtomicInteger(100);
 
int currentValue = atomicInteger.get();         //100
 
atomicInteger.set(1234); 
```

#### When to use AutomicInteger in Java

1. As an **atomic counter** which is being used by multiple threads concurrently.
   
2. In **compare and swap** operation to implement non-blocking algorithms.

```java
public class Main 
{
    public static void main(String[] args) 
    {
        AtomicInteger atomicInteger = new AtomicInteger(100);
         
        System.out.println(atomicInteger.addAndGet(2));         //102
        System.out.println(atomicInteger);                      //102
         
        System.out.println(atomicInteger.getAndAdd(2));         //102
        System.out.println(atomicInteger);                      //104
         
        System.out.println(atomicInteger.incrementAndGet());    //105   
        System.out.println(atomicInteger);                      //105   
                 
        System.out.println(atomicInteger.getAndIncrement());    //105
        System.out.println(atomicInteger);                      //106
         
        System.out.println(atomicInteger.decrementAndGet());    //105
        System.out.println(atomicInteger);                      //105
         
        System.out.println(atomicInteger.getAndDecrement());    //105
        System.out.println(atomicInteger);                      //104
    }
}
```

```java

boolean compareAndSet(int expect, int update)

import java.util.concurrent.atomic.AtomicInteger;
 
public class Main 
{
    public static void main(String[] args) 
    {
        AtomicInteger atomicInteger = new AtomicInteger(100);
         
        boolean isSuccess = atomicInteger.compareAndSet(100,110);   //current value 100
         
        System.out.println(isSuccess);      //true
         
        isSuccess = atomicInteger.compareAndSet(100,120); //current value 110
         
        System.out.println(isSuccess);      //false
         
    }
}
```

### How to use lock in Java

A **java.util.concurrent.locks.lock** is a thread synchronization mechanism just like synchronized blocks. A **lock** is, however, more flexible and more sophisticated(复杂的) than a synchronization block. **Lock** is an interface and **ReentrantLock** is one such implementation of lock interface.

```java
public class LockExample
{
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();;
    }
}
```

#### Difference between Lock Interface and synchronized keyword

The main differences between a Lock and synchronized block are:

1. Having a timeout to get access to a **synchronization** block is not possible. Using **Lock.tryLock(long timeout, TimeUtil timeUtil)**, it is possible.

2. The **synchronization** block must be fully contained within a single method. A lock can have it's calls to **lock()** and **unlock()** in seperate methods.

Example:

```java
package fundamental.threadstudy;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrinterQueue
{
    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document)
    {
        queueLock.lock();
        try
        {
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during "
                                + (duration / 1000) + " seconds :: Time - " + new Date());
            Thread.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
            queueLock.unlock();
        }

    }
}


class PrintingJob implements Runnable
{
    private PrinterQueue printerQueue;

    public PrintingJob(PrinterQueue printerQueue)
    {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run()
    {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printerQueue.printJob(new Object());
    }
}

public class LockExample
{
    public static void main(String[] args)
    {
        PrinterQueue printerQueue = new PrinterQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++)
        {
            thread[i] = new Thread(new PrintingJob(printerQueue), "Thread " + i);
        }
        for (int i = 0; i < 10; i++)
        {
            thread[i].start();
        }
    }
}

/**
Output:
 
Thread 0: Going to print a document
Thread 9: Going to print a document
Thread 8: Going to print a document
Thread 7: Going to print a document
Thread 5: Going to print a document
Thread 6: Going to print a document
Thread 4: Going to print a document
Thread 3: Going to print a document
Thread 2: Going to print a document
Thread 1: Going to print a document
Thread 0: PrintQueue: Printing a Job during 8 seconds :: Time - Tue Jan 06 15:19:02 IST 2015
Thread 0: The document has been printed
Thread 9: PrintQueue: Printing a Job during 1 seconds :: Time - Tue Jan 06 15:19:11 IST 2015
Thread 9: The document has been printed
Thread 8: PrintQueue: Printing a Job during 8 seconds :: Time - Tue Jan 06 15:19:12 IST 2015
Thread 8: The document has been printed
Thread 7: PrintQueue: Printing a Job during 9 seconds :: Time - Tue Jan 06 15:19:21 IST 2015
Thread 7: The document has been printed
Thread 5: PrintQueue: Printing a Job during 7 seconds :: Time - Tue Jan 06 15:19:31 IST 2015
Thread 5: The document has been printed
Thread 6: PrintQueue: Printing a Job during 5 seconds :: Time - Tue Jan 06 15:19:39 IST 2015
Thread 6: The document has been printed
Thread 4: PrintQueue: Printing a Job during 2 seconds :: Time - Tue Jan 06 15:19:44 IST 2015
Thread 4: The document has been printed
Thread 3: PrintQueue: Printing a Job during 2 seconds :: Time - Tue Jan 06 15:19:46 IST 2015
Thread 3: The document has been printed
Thread 2: PrintQueue: Printing a Job during 5 seconds :: Time - Tue Jan 06 15:19:49 IST 2015
Thread 2: The document has been printed
Thread 1: PrintQueue: Printing a Job during 5 seconds :: Time - Tue Jan 06 15:19:54 IST 2015
Thread 1: The document has been printed
**/
```

If you don’t call the unlock() method at the end of the critical section, the other threads that are waiting for that block will be waiting forever, causing a deadlock situation. If you use try-catch blocks in your critical section, don’t forget to put the sentence containing the unlock() method inside the finally section.


### Creating Threads using java.util.concurrent.ThreadFactory

The centeralization of creation logic brings us some advantages
1. It is easy to change the class of the objects created or the way we create these objects.

2. It is easy to limit the creation of objects for limited resources. For example, we can only have N objects of a type.

3. It is easy to generate statistical data about the creation of the objects. 

```java
package fundamental.threadstudy.thread_factory;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

package fundamental.threadstudy.thread_factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory
{
    private int counter;
    private String name;
    private List<String> stats;

    public CustomThreadFactory(String name)
    {
        counter = 1;
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable runnable)
    {
        Thread thread = new Thread(runnable, name + "-Thread_" + counter);
        counter++;
        stats.add(String.format("Created thread %d with name %s on %s \n", thread.getId(), thread.getName(), new Date()));
        return thread;
    }

    public String getStats()
    {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while (it.hasNext())
        {
            buffer.append(it.next());
        }
        return buffer.toString();
    }

    public static void main(String[] args)
    {
        CustomThreadFactory factory = new CustomThreadFactory("CustomThreadFactory");
        Task task = new Task();
        Thread thread;
        System.out.printf("Starting the Threads\n\n");
        for (int i = 1; i <= 10; i++)
        {
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.printf("All Threads are created now\n\n");
        System.out.printf("Give me CustomThreadFactory stats:\n\n" + factory.getStats());
    }
}

/**
Output :
 
Starting the Threads
 
All Threads are created now
 
Give me CustomThreadFactory stats:
 
Created thread 9 with name CustomThreadFactory-Thread_1 on Tue Jan 06 13:18:04 IST 2015
Created thread 10 with name CustomThreadFactory-Thread_2 on Tue Jan 06 13:18:04 IST 2015
Created thread 11 with name CustomThreadFactory-Thread_3 on Tue Jan 06 13:18:04 IST 2015
Created thread 12 with name CustomThreadFactory-Thread_4 on Tue Jan 06 13:18:04 IST 2015
Created thread 13 with name CustomThreadFactory-Thread_5 on Tue Jan 06 13:18:04 IST 2015
Created thread 14 with name CustomThreadFactory-Thread_6 on Tue Jan 06 13:18:04 IST 2015
Created thread 15 with name CustomThreadFactory-Thread_7 on Tue Jan 06 13:18:04 IST 2015
Created thread 16 with name CustomThreadFactory-Thread_8 on Tue Jan 06 13:18:04 IST 2015
Created thread 17 with name CustomThreadFactory-Thread_9 on Tue Jan 06 13:18:04 IST 2015
Created thread 18 with name CustomThreadFactory-Thread_10 on Tue Jan 06 13:18:04 IST 2015
**/

```


### ExecutorService in Java


#### What is Executor Framework?

In simple Java application, we do not face much challenge while working with a small number of threads. If you have to develop a program runs a lot of concurrent tasks, this approach will present many disadvantages such as lots of bolier plate code, executing thread manually and keeping track of thread execution results.

**Exexutor framework** solved this problem. The framework consist of three main interfaces(and lots of child interfaces). **Executor, ExecutorService** and **ThreadPoolExecutor**.

**Benefit of Executor framework**

1. The framework mainly seperates task creation and execution. Task creation is mainly boiler plate code and is easily replaceable.

2. With an executor, we have to create task which implement either **Runable** or **Callable** interface and send them to the executor.

3. Executor internally maintain a (configurable) thread pool to improve application performance by avoiding the continous spawning of threads.

4. Executor is responseible for executing the tasks, running them with the necessary threads from the pool.

**Callable and Future**

1. It's **call()** method returns a result after the thread execution is complete.

2. When we send a **callable()** object to an executor, we get **Future** object's reference. We can use this object to query the status of thread and the result of the **Callable** object.


#### Creating ExecutorService instances

**ExecutorService** is an interface and it's implementations can execute a **Runnable** or **Callable** class in an asynchronous way. Note that invoking the **run()** method of a **Runnable** interface in a synchronous way is simply calling a method.

```java
//Executes only one thread
ExecutorService es = Executors.newSingleThreadExecutor(); 
 
//Internally manages thread pool of 2 threads
ExecutorService es = Executors.newFixedThreadPool(2); 
 
//Internally manages thread pool of 10 threads to run scheduled tasks
ExecutorService es = Executors.newScheduledThreadPool(10); 

ExecutorService executorService = new ThreadPoolExecutor(10, 100, 5L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
```

#### Submitting tasks to ExecutorService

**Execute Runnable Tasks**

We can execute 

Reference:

https://www.javaworld.com/article/2077138/introduction-to-java-threads.html

https://www.guru99.com/multithreading-java.html

https://www.jianshu.com/p/a3f9f2c3ecf8

https://howtodoinjava.com/java-concurrency-tutorial/