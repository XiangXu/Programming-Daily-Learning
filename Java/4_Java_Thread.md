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


### Create Threads

There are two ways of creating threads: implementing an interface and extending a class.

There are a few differences between a class and an interface:

First, an interface can only contain abstract methods(Java 8 allows to implment default method in interface) and/or static final variables(constants). Classes, on the other hand , can implement methods and contain variables that are not constatns. 

Second, an interface cannot implement any methods. A class that implements an interface must implement all methods defined in that interface. An interface has the ability to extend from other interfaces, and unlike classes can extend from multiple interfaces.

Furthermore, an interface cannot be instantiated with new operator.

Here are two exmaples:
```java
import java.lang.*;
class Counter extends Thread 
{                      
        public void run()                       
        {              
            ....            
        }
}

class Counter implements Runnable
{
    @Override
    public void run() 
    {
        ...
    }
}
```

The first method of creating a thread is to simply extend from the **Thread** class. Do this only if the class you need executed as a thread does not ever need to be extended from another class. The **Thread** class is defined in the package java.lang, which needs to be imported so that our classes are aware of its definition.

The second method is defined in the Runnable interface and is being implemented. Note that we have an instance of the **Thread** class as a variable of the **Counter** class. The only difference between the two methods is that by implementing Runnable, there is greater flexibility in the creation of the class **Counter**. 

### Thread Life Cycle in Java

1. **New**: In this phase, the thread is created using class "Thread class". It remains in this state till the program **starts** the thread. It is also known as born thread.

2. **Runnable**: In this page, the instance of the thread is invoked with a start method. **The thread control is given to scheduler to finish the execution. It depends on the Scheduler, wheter to run the thread.**

3. **Running**: When the thread starts executing, then the state is changed to "running" state. The scheduler selects on thread from the thread pool, and it starts executing in the applicaiton.

4. **Waiting**: This is the state when a thread has to wait. As there multiple threads are running in the application, there is a need for synchronization(同时发生的) between threads. Hence, one thread has to wait,till other thread gets executed. Therefore, this state is referred as waiting state.

5. **Dead**: This is the state when the thread is terminated. The thread is in running state and as soon as it completed processing it is in "dead state".

Some of the commonly used methods for threads are

|  Method |  Description  |
|---|---|
| start()  | This method starts the execution of the thread and JVM calls the run method on the thread   |
| Sleep(int milliseconds)  | This method makes the thread sleep hence the thread's execution will pause for milliseconds provided and after that, again the thread starts executing. This help in synchronization of the threads.   |
| getName()  | It returns the name of the thread.   |
| setPriority(int newPriority)  | It changes the priority of the thread   |
| yield() | It causes current thread on halt and other threads to execute |
```java
package demotest;
public class thread_example1 implements Runnable {
    @Override
    public void run() {
    }
    public static void main(String[] args) {
        Thread guruthread1 = new Thread();
        guruthread1.start();
        try {
            guruthread1.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        guruthread1.setPriority(1);
        int gurupriority = guruthread1.getPriority();
        System.out.println(gurupriority);
        System.out.println("Thread Running");
  }
}
// Output:
//5
//Thread Running
```

### Java Thread Synchronization

In multithreading, there is the asynchronous(异步的) behavior of the programs. If one thread is writing some data and antoher thread is reading at the same time, might create inconsistency in the application. 

When there is a need to access the shared resources by two or more threads, the synchronization approach is utilized. 

Java has provided synchronized methods to implement synchronized behavior.

In this approach, once the thread reaches inside the synchronized block, then no other thread can call that method on the same object. All threads have to wait till that thread finishes the synchronized block and comes out of that. 

In this way, the synchronization helps in a multithreaded application. One thread has to wait till other thread finishes its execution only then other threads are allowed for execution.

It can be written in the following form:
```java
Synchronized(object)
{  
        //Block of statements to be synchronized
}
```


## Java Multithreading Example

In this example, it shows how to override run() and start() method of a runnable interface and create two threads of that class and run them accordingly.

```java
package fundamental;

public class ThreadStudy
{

    public static void main(String[] args)
    {
        WorkerThread workerThread1 = new WorkerThread("worker1");
        workerThread1.start();

        WorkerThread workerThread2 = new WorkerThread("worker2");
        workerThread2.start();
    }
}

class WorkerThread implements Runnable
{
    private Thread worker;
    private String workerName;

    WorkerThread(String workerName)
    {
        this.workerName = workerName;
    }

    @Override
    public void run()
    {
        System.out.println("Thread running " + workerName);
        for(int i=0; i<4; i++)
        {
            System.out.println(i);
            System.out.println(workerName);
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread has been interrupted");
            }
        }
    }

    public void start()
    {
        System.out.println("Thread started");
        if(worker == null)
        {
            worker = new Thread(this,  workerName);
            worker.start();;
        }
    }
}

/**
Outputs:
Thread started
Thread started
Thread running worker1
Thread running worker2
0
worker2
0
worker1
1
worker2
1
worker1
2
worker2
2
worker1
3
worker2
3
worker1
**/
```









Reference:

https://www.javaworld.com/article/2077138/introduction-to-java-threads.html

https://www.guru99.com/multithreading-java.html

https://www.jianshu.com/p/a3f9f2c3ecf8