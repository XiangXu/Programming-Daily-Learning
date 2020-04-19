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

**CachedThreadPool**: creates one thread per task. 

**FixedThreadPool**: uses a limited set ot threads to execute the submitted tasks. You do expensive thread allocation once, up front, and you thus limit the number of threads. It will generally create as many threads as it needs during the execution of a program and then will stop creating new threads as it recycles the old ones.

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

The call to **sleep()** can throw an **InterruptedException**. 

**TimeUnit** provides better readability by allowing you to specify the units of the **sleep()** delay.

### Priority

The **priority** convenys the importance of a thread to the scheduler. **getPriority() and setPriority()**

### Yielding

When you call **yield()**, you are suggesting that other threads of the same priority might be run. However, **you cannot rely on yield() for any serious control or tuning of your application.**

### Daemon threads

A **Daemon thread** is intended to provide a general service in the background as long as the program is running, but is not part of the essence of the program.

```java
thread.setDaemon(true); // must be called before start()
```




Reference:

Thinking In Java