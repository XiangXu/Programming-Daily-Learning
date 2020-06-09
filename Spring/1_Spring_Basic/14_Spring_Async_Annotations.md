# How To Do @Async in Spring

Annotating a method of a bean with @Async will make it **execute in a separate thread**, the caller will not wait for completion of the called method.

## Enable Async Support

```java
@Configuration
@EnableAsync
public class SpringAsyncConfig { ... }
```

## The @Async Annotation

**@Async** has two limitations:

* It must be applied to public methods only.
* self-invotation - **calling the async method from within the same class - won't work**.

The reasons are simple:

* **the method needs to be public** so that it can be proxied.
* **Self-invotation doesn't work** because it bypasses the proxy and calls the underlying method directly.

### Method With Void Return Type

```java
@Async
public void asyncMethodWithVoidReturnType() {
    System.out.println("Execute method asynchronously. "
      + Thread.currentThread().getName());
}
```

### Methods With Return Type

@Async can also applied to a method with return type - by wrapping the actual return in Future:

```java
@Async
public Future<String> asyncMethodWithReturnType() {
    System.out.println("Execute method asynchronously - "
      + Thread.currentThread().getName());
    try {
        Thread.sleep(5000);
        return new AsyncResult<String>("hello world !!!!");
    } catch (InterruptedException e) {
        //
    }
 
    return null;
}
```

Spring also provides an AsyncResult class which implements Future. This can be used to track the result of asynchronous method execution.

```java
public void testAsyncAnnotationForMethodsWithReturnType()
  throws InterruptedException, ExecutionException {
    System.out.println("Invoking an asynchronous method. "
      + Thread.currentThread().getName());
    Future<String> future = asyncAnnotationExample.asyncMethodWithReturnType();
 
    while (true) {
        if (future.isDone()) {
            System.out.println("Result from asynchronous process - " + future.get());
            break;
        }
        System.out.println("Continue doing something else. ");
        Thread.sleep(1000);
    }
}
```

## The executor

By default, Spring uses a SimpleAsyncTaskExecutor to actually run these methods asynchronously. The defaults can be overriden at two levels - at the application level or at the individual method level.

**SimpleAsyncTaskExecutor does not reuse any threads, rather it starts up a new thread for each invocation**. However, it does support a concurrency limit which will block any invocations that are over the limit until a slot has been freed up.

https://docs.spring.io/spring/docs/3.1.x/spring-framework-reference/html/scheduling.html#scheduling-task-executor

### Executor Configuration

Since Spring 2.1 you can use auto configuration and change the maximum number of threads in application properties file:

```
spring.task.execution.pool.core-size=20
Core number of threads.

spring.task.execution.pool.max-size=100
Maximum allowed number of threads. If tasks are filling up the queue, the pool can expand up to that size to accommodate the load. Ignored if the queue is unbounded.

spring.task.execution.pool.queue-capacity=0
Queue capacity. An unbounded capacity does not increase the pool and therefore ignores the "max-size" property.

spring.task.execution.pool.keep-alive=10s
Time limit for which threads may remain idle before being terminated.
```

If the number of threads is less than the corePoolSize, create a new Thread to run a new task.
If the number of threads is equal (or greater than) the corePoolSize, put the task into the queue.
If the queue is full, and the number of threads is less than the maxPoolSize, create a new thread to run tasks in.
If the queue is full, and the number of threads is greater than or equal to maxPoolSize, reject the task.


### Override the Executor at the Method Level

The required executor needs to be declared in a configuration class:

```java
@Configuration
@EnableAsync
public class SpringAsyncConfig {
     
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
```

Then the executor name should be provided as an attribute in @Async:

```java
@Async("threadPoolTaskExecutor")
public void asyncMethodWithConfiguredExecutor() {
    System.out.println("Execute method with configured executor - "
      + Thread.currentThread().getName());
}
```

### Override the Executor at the Application Level

The configuration class should implement the **AsyncConfigure** interface - which will mean that it has the implement the **getAsyncExecutor()** method. It's here that we will return the executor for the entire application - this now becomes the default executor to run methods annotated with @Async:

```java
@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {
     
    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolTaskExecutor();
    }
     
}
```

## Exception Handling 

When a method return type is a Future, exception handle is easy - Future.get() method will throw the exception.

But if the return type is void, **Exception will not be propagated to the calling method**. Hence we need to add extra configurations to handle exceptions.

```java
public class CustomAsyncExceptionHandler
  implements AsyncUncaughtExceptionHandler {
 
    @Override
    public void handleUncaughtException(
      Throwable throwable, Method method, Object... obj) {
  
        System.out.println("Exception message - " + throwable.getMessage());
        System.out.println("Method name - " + method.getName());
        for (Object param : obj) {
            System.out.println("Parameter value - " + param);
        }
    }
     
}

@Override
public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new CustomAsyncExceptionHandler();
}
```



Reference

https://www.baeldung.com/spring-async

http://www.bigsoft.co.uk/blog/2009/11/27/rules-of-a-threadpoolexecutor-pool-size