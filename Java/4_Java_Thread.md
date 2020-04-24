# Java Thread

## Basic Threading

### Defining tasks

A thread drives a task, so you need a way to describe that tasks. This is provided by the **Runnable** interface.

```java
package ThreadStudy;

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
package ThreadStudy;

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
package ThreadStudy;

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

The call to **shutdown()** prevents new tasks from being submitted to that **Executor**. The current thread will continue to run all tasks submitted before **shutdown()** was called. The program will exit as soon as all the taasks in the **Executor** finish.

**CachedThreadPool**: creates one thread per task. It will generally create as many threads as it needs during the execution of a program and then will stop creating new threads as it recycles the old ones.

**FixedThreadPool**: uses a limited set ot threads to execute the submitted tasks. You do expensive thread allocation once, up front, and you thus limit the number of threads. 

**Consider using FixedThreadPools in production code.**

**SingleThreadExecutor ensures that only one task at a time is running.** so you don't need to deal with synchronizing on the shared resource. 

### Producing return values from tasks

The **Callable** interface is a generic with a type of parameter representing the return value from the method **call()** and must be invoked using an **ExecutorService submit()** method.

```java
package ThreadStudy.CallableExample;

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
package fundamental.threadstudy;

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



Reference:

Thinking In Java