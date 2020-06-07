# final, finally and finalize 

### final

**final with variable**: The value of variable cannot be changed once initialized.

**final with class**: The class cannot be subclassed.

**final with method**: The method cannot be overriden by a subclass.

### finally

The finally keyword is used in association with try/catch block and guarantees that a section of code will be executed, even if an exception is thrown. The finally block will be executed after the try and catch blocks.

### finalize

That is a method that the **Garbage Collector** always call just before the deletion/destorying the object which is eligible for Garbage Collection, so as to perform clean-up activity.

```java
class Hi { 
    public static void main(String[] args) 
    { 
        Hi j = new Hi(); 
  
        // Calling finalize method Explicitly. 
        j.finalize(); 
  
        j = null; 
  
        // Requesting JVM to call Garbage Collector method 
        System.gc(); 
        System.out.println("Main Completes"); 
    } 
  
    // Here overriding finalize method 
    public void finalize() 
    { 
        System.out.println("finalize method overriden"); 
        System.out.println(10 / 0); 
    } 
} 

//exception in thread "main" java.lang.ArithmeticException:
//by zero followed by stack trace.


class RR { 
    public static void main(String[] args) 
    { 
        RR q = new RR(); 
        q = null; 
  
        // Requesting JVM to call Garbage Collector method 
        System.gc(); 
        System.out.println("Main Completes"); 
    } 
  
    // Here overriding finalize method 
    public void finalize() 
    { 
        System.out.println("finalize method overriden"); 
        System.out.println(10 / 0); 
    } 
} 

//finalize method overriden
//Main Completes

```

Reference:

https://snailclimb.gitee.io/javaguide/#/docs/java/Java%E7%96%91%E9%9A%BE%E7%82%B9?id=11-%e6%ad%a3%e7%a1%ae%e4%bd%bf%e7%94%a8-equals-%e6%96%b9%e6%b3%95