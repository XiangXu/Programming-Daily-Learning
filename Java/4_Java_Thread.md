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










Reference:

Thinking In Java