# Java Thread

## Basic Threading

### Defining tasks

A thread drives a task, so you need a way to describe that tasks. This is provided by the **Runnable** interface.

```java
public class LiftOff implements Runnable
{
    private static int taskCount = 0;

    protected int countDown = 10;
    private final int id = taskCount++;

    public LiftOff(){}

    public LiftOff(int countDown)
    {
        this.countDown = countDown;
    }

    public String status()
    {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    @Override
    public void run()
    {
        while(countDown-- > 0)
        {
            System.out.println(status());
            Thread.yield();
        }
    }
}
```

### The Thread class

The traditional way to trun a **Runnable** object into a working task is to hand it to a **Thread** constructor.

```java
public class LiftOffTest
{
    public static void main(String[] args)
    {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for Liftoff");
    }
}
```

**When main() creates the Thread objects, it isn't capturing the references for any of them. Each thread "registers" itself so there is actually a reference to it someplace, and the garbage collector cannot clean it up until the task exists its run() and dies. A thread creates a seperate thread of execution that persists after the call to start() completes.**

### Using Executors

**Executor** provide a layer of indirection(间接的) between a client and the execution of a task. It allows you to manage the execution of asynchronous tasks withouth having to explicitly manage the lifecycle of threads. **Executors are the preferred method for starting tasks in Java.**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LiftOffTest
{
    public static void main(String[] args)
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        //  ExecutorService exec = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++)
        {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
```

The call to **shutdown()** prevents new tasks from being submitted to that **Executor**. The current thread will continue to run all tasks submitted before **shutdown()** was called. The program will exit as soon as all the tweeasks in the **Executor** finish.

**CachedThreadPool**: creates one thread per task. It will generally create as many threads as it needs during the execution of a program and then will stop creating new threads as it recycles the old ones.

**FixedThreadPool**: uses a limited set ot threads to execute the submitted tasks. You do expensive thread allocation once, up front, and you thus limit the number of threads. 

**Consider using FixedThreadPools in production code.**

**SingleThreadExecutor ensures that only one task at a time is running.** so you don't need to deal with synchronizing on the shared resource. 

### Producing return values from tasks

The **Callable** interface is a generic with a type of parameter representing the return value from the method **call()** and must be invoked using an **ExecutorService submit()** method.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String>
{
    private int id;
    public TaskWithResult(int id)
    {
        this.id = id;
    }

    @Override
    public String call() throws Exception
    {
        return "Result of TaskWithResult " + id;
    }
}

public class CallableDemo
{
    public static void main(String[] args)
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();

        for(int i=0; i<10; i++)
            results.add(exec.submit(new TaskWithResult(i)));

        for (Future<String> fs : results)
        {
            try
            {
                System.out.println(fs.get());
            }
            catch (InterruptedException | ExecutionException e)
            {
                System.out.println(e);
            }
            finally {
                exec.shutdown();
            }
        }
    }
}
```

You can query the **Future** with **isDone()** to see if it has completed.

### Sleeping

```java
    @Override
    public void run()
    {
        while(countDown-- > 0)
        {
            System.out.println(status());
//            Thread.sleep(100);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
```

**Causes the currently running thread to block for at least the specified number of milliseconds.**

The call to **sleep()** can throw an **InterruptedException**. 

**TimeUnit** provides better readability by allowing you to specify the units of the **sleep()** delay.

### Priority

The **priority** convenys the importance of a thread to the scheduler. **getPriority() and setPriority()**

### Yielding

**Causing the currently running thread to yield to any other threads of the same priority that are waiting to be scheduled.**

When you call **yield()**, you are suggesting that other threads of the same priority might be run. However, **you cannot rely on yield() for any serious control or tuning of your application.**

### Daemon threads

A **Daemon thread** is intended to provide a general service in the background as long as the program is running, but is not part of the essence of the program.

```java
thread.setDaemon(true); // must be called before start()
```

It is possible to customize the attributes of threads created by **Executors**

```java
public class DaemonThreadFactory implements ThreadFactory
{
    @Override
    public Thread newThread(Runnable r)
    {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}

public class DaemonThreadPoolExecutor extends ThreadPoolExecutor
{
    public DaemonThreadPoolExecutor()
    {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new DaemonThreadFactory());
    }
}

public class DaemonFromFactory implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
        ExecutorService executorService = new DaemonThreadPoolExecutor();
        for(int i=0; i<10; i++)
        {
            executorService.execute(new DaemonFromFactory());
        }

        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
```

**If a thread is a daemon, then any threads it creates will automatically be daemons.**

**You should be aware that daemon threads will terminates their run() methods without executing finally caluses.**

### Joining a thread

One thread may call **join()** on another thread to wait for the second thread to complete before proceeding. If a thread calls **t.join()** on another thread **t**, then the calling thread is suspended until the target thread **t** finishes. You may also call **join()** with a timeout argument.

```java
class BoyThread extends Thread
{
    @Override
    public void run()
    {
        System.out.println("A boy and a girl are going to shopping together");

        GirlThread girl = new GirlThread();
        girl.start();

        int time = 2000;
        try
        {
            girl.join(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Boy has been waiting for" + time + ", he went shopping alone.");
    }
}

class GirlThread extends Thread
{
    @Override
    public void run()
    {
        int time = 5000;

        System.out.println("Girl start to makeup，boy is waiting.");

        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Girl finish makeup，cost：" + time);
    }
}

public class ThreadJoinExample
{
    public static void main(String[] args)
    {
        BoyThread boyThread = new BoyThread();
        boyThread.start();
    }
}
```

### Catching Execptions

**Thread.UncaughtExceptionHandler** allows you to attach an exception handler to each **Thread** object. 

**Thread.UnacughtExcetpionHandler.uncaughtException()** is automatically called when that thread is about die from an uncaught exception. To use it, we create a new type of **ThreadFactory** which attaches a new Thread.UncaughtExceptionHandler to each new Thread it creates. 

```java
public class ExceptionThread implements Runnable
{
    @Override
    public void run()
    {
        throw new RuntimeException();
    }
}

public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
        System.out.println("Caught " + e);
    }
}

public class HandlerThreadFactory implements ThreadFactory
{
    @Override
    public Thread newThread(Runnable r)
    {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new UncaughtExceptionHandler());;
        return t;
    }
}

public class CaptureUncaughtExcetpion
{
    public static void main(String[] args)
    {
//      Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
//      ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionThread());
    }
}
```

### synchronized

When a task wishes to execute a piece of code guarded by the **synchronized** keyword, it checks to see if the lock is available, then acquires it, executes the code, and release it.

All objects automatically contain a single lock(also refered to as a **monitor**). When you call any **synchronized** method, that object is locked and no other **synchronized** method of that object can be called until the first one finishes and releases the lock.

**Note that it is especially important to make fields private when working with concurrency. other the synchronized keyword cannot prevent another task from accessing a field directly, and thus producing collision.**

When should you synchronize?

*If you are writing a variable that might next be read by another thread, or reading a variable that might have last been written by another thread, you must use synchronization, and further, both the reader and the writer must synchronize using the same monitor lock*.

```java
public class EventGenerator extends IntGenerator
{
    private int currentEventValue = 0;

    @Override
    public synchronized int next()
    {
        ++ currentEventValue;
        Thread.yield();
        ++ currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) {
        EventChecker.test(new EventGenerator());
    }
}
```
### Using explicit Lock objects

The **Lock** object must be explicitly created, locked and unlocked. It produces less elegant code than the build-in form. However, it is more flexible for solving certain types of problems. 

```java
public class MutexEvenGenerator extends IntGenerator
{
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public int next()
    {
        lock.lock();
        try
        {
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return  currentEvenValue;
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EventChecker.test(new MutexEvenGenerator());
    }
}
```

In general, when you are using **synchronized**, there is less code to write, and the opportunity for user error is greatly reduced, so you will usually only use the explicit **Lock** objects when you are solving special problems.

```java
private ReentrantLock lock = new ReentrantLock();

try
{
    boolean captured = lock.tryLock(2, TimeUnit,SECONDS);
}
catch(InterruptedException e)
{
    throw new RuntimeException(e);
}
```

### Atomicity and volatility

*If you can write a high-performance JVM for a modern microprocessor, then you are qualified to think about wheter your can void synchronizing.*

**Reading and writing primitive variables other than long and double is guaranteed to go to and from memory as indivisible(atomic) operations. You do get atomicity if you use the volatile keyword when defining a long or double variable.**

An atomic operation on a non-volatile field will not necessarily be flushed to main memory, so another task that reads that field will not necessarily see the new Value. If multiple tasks are accessing a field, that field should be **Volatile**; otherwise, the field should only be accessed via synchronization. **Synchronization** also causes flushing to main memory, so if a field is completely guarded by **synchronized** methods or blocks, it is not necessary to make it **volatile**.

**volatile** doesn't work when the value of a field depends on its previous value, nor does it work on fields whose value are constrained by the values of other fields, such as the lower and upper bound of Range class which must obey the constraint lower <= upper.

**It is typically only safe to use volatile instead of synchronized if the class has only one mutable field.**

### Thread Local Storage

A second way to prevent tasks from colliding(冲突) over shared resources is to eliminate the sharing of variables. **Thread Local Storage** is a mechanism that automatically creates different storage for the same variable, for each different thread that uses an object. 

```java
private static ThreadLocal<Integer> value = new ThreadLocal<Integer>()
{
    private Random rand = new Random(47);
    protected synchronized Integer initialValue()
    {
        return rand.nextInt(10000);
    }
};

public static void increment()
{
    value.set(value.get() + 1);
}

public static int get()
{
    return value.get();
}
```

**ThreadLocal** objects are usually sotred as **static** fields. When you create a **ThreadLocal** object, you are only able to access the contents of the object using the **get()** and **set()** methods. The **get()** method returns a copy of the object that is associated with the thread, and **set()** inserts its argument into the object stored for that thread, returning the old object that was in storage. 


### Thread States

1. **New**: A thread remains in this state only momentarily, as it is being created. It allocates any necessary system resources and platforms initialization. At this point it becomes eligible to receive CPU time. The scheduler will then transition this thread to the runnable or blocked statel.
   
2. **Runnable**: This means that a thread can be run when the time-slicing mechanism has CPU cycles available for the thread. Thus, the thread minght or might not be running at any moment, but there is nothing to prevent it from being run if the scheduler can arrange it. That is, it is not dead or blocked.

3. **Blocked**: The thread can be run, but something prevents it. While a thread is in the blocked state, the scheduler will simply skip it and not give it any CPU time. Until a thread reenters the runnable state, it won't perform any operations. 

4. **Dead**: A thread in the dead or terminated state is no longer scheduleable and will not receive any CPU time. Its task is completed, and it is no longer runnable. One way for a task to die is by returning from its **run()** method, but a task's trhead can aslo be interrupted. 

### Becoming blocked

* You have put the task to sleep by calling **sleep(milliseconds)**, in which case it will not be run for the specified time.

* You have suspended the execution of the thread with **wait()**. It will not become runnable until the thread gets the **notify() or **notifyAll()**.

* The task is waiting for some I/O to complete.

* The task is trying to call a **synchronized** method on antoher object, and that object's lock is not available because it has alaready accquired by another task.





Reference:

Thinking In Java