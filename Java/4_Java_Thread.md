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

2. **Runnable**: In this page, the instance of the thread is invoked with a start method. **The thread control is given to scheduler to finish the execution. It depends on the Scheduler, wheter to run the thread.**

3. **Running**: When the thread starts executing, then the state is changed to "running" state. The scheduler selects on thread from the thread pool, and it starts executing in the applicaiton.

4. **Blocked/Waiting**: This is the state when a thread has to wait. As there multiple threads are running in the application, there is a need for synchronization(同时发生的) between threads. Hence, one thread has to wait,till other thread gets executed. Therefore, this state is referred as waiting state.

5. **Timed Waiting**: A runnable thread can enter the timed waiting state for a specific interval of time. A thread in this state transitions back to the runnable state when that time interval expires or when the event it is waiting for occurs.

6. **Terminated/Dead**: This is the state when the thread is terminated. The thread is in running state and as soon as it completed processing it is in "dead state".


### Thread Priorities

Every Java thread has priority that helps operation system determine the order in which threads are scheduled.

Java thread priorities are in range between MIN-PRIORITY (a constant of 1) and MAX_PRIORITY (a constant of 10). By default, every thread is given priority NORMAL_PRIORITY(a constant of 5).

Threads with higher priority are more important to a program and should be allocated processor time before lower-priority threads. However, thread priorities cannot guarantee the order in which threads execute and are very much platform dependent.

### Java Thread Synchronization(同时发生的)

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





Reference:

https://www.javaworld.com/article/2077138/introduction-to-java-threads.html

https://www.guru99.com/multithreading-java.html

https://www.jianshu.com/p/a3f9f2c3ecf8

https://www.tutorialspoint.com/java/java_multithreading.htm