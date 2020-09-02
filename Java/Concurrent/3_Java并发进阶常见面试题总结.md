# Java 并发进阶常见面试题总结

## synchronized关键字

### 说一说自己对synchronized关键字的了解

**synchronized关键字解决的是多个线程之间访问资源的同步性, synchronized关键字可以保证被它修饰的方法或者代码在任何时刻只能有一个线程执行**.

另外, 在Java的早期版本中, synchronized属于重量级锁, 效率底下, 因为监视器锁(monitor)是依赖于底层的操作系统的Mutex Lock来实现的, Java的线程是映射到操作系统的原生态线程上. 如果要挂起或者唤醒一个线程, 都需要操作系统来帮忙完成, 而操作系统值实现线程之前的切换时需要从用户状态转到内核, 这个状态之前的转换需要相对比较长的时间, 时间成本相对比较高, 这也是为什么早期的synchronized效率低的原因. 庆幸的是在Java6之后Java官方对从JVM层面对synchronized较大优化, 所以现在的synchronized效率锁优化的很不错了. JDK1.6对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销.

### 说说自己是怎么使用synchronized关键字, 项目中用到了吗

synchronized关键字的三种实现方式:

* **修饰实例方法**: 作用于当前对象实例加锁, 进入同步代码前要获得当前对象实例的锁.
* **修饰静态方法**: 也就是给当前类加锁, 会作用于类所有对象实例, 因为静态成员不属于任何一个实例对象, 是类成员(static表明这个该类的一个静态资源, 不管new类多少个对象, 只有一份). 所以如果一个线程A调用一个实例对象的非静态synchronized方法, 而线程B需要调用这个实例对象所属的静态synchronized方法, 是允许的, 不会发生互斥现象, **因为访问静态synchronized方法占用的锁是当前类的锁, 而访问非静态synchronized方法占用的锁是当前实例对象锁**.
* **修饰代码块**: 指定加锁对象, 对给定对象加锁, 进入同步代码库前要获得给定对象的锁.

总结: 
* synchronized关键字加到static静态方法和synchronized(class)代码块上都是给Class类上锁.
* synchronized关键字加到实例方法上是给对象实例上锁.
* 尽量不要使用synchronized(String a), 因为JVM中字符常量具有缓存功能.

## 双重校验实现对象单例(线程安全)

```java
public class Singleton {

    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public  static Singleton getUniqueInstance() {
       //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```

需要注意的是uniqueInstance采用volatile关键字修饰是很有必要的, uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：

1. 为uniqueInstance分配空间.
2. 初始化uniqueInstance.
3. 将uniqueInstance指向分配的内存地址.

但是由于JVM具有指令重排的特性, 执行顺序有可能变为1->3->2. 指令重排在单线程环境下不会出现问题, 但是在多线程环境下会导致一个线程获得还没有初始化的实例. 例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。

**使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行**.

## 讲一下synchronized关键字的底层原理

synchronized关键字底层原理属于JVM层面.

### synchronized同步语句块的情况

```java
public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
}
```

通过 JDK 自带的 javap 命令查看 SynchronizedDemo 类的相关字节码信息：首先切换到类的对应目录执行 javac SynchronizedDemo.java 命令生成编译后的 .class 文件，然后执行javap -c -s -v -l SynchronizedDemo.class。

![synchronized-block](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-6/synchronized%E5%85%B3%E9%94%AE%E5%AD%97%E5%8E%9F%E7%90%86.png)

**synchronized同步语句块的实现使用的是monitorenter和monitorexit指令, 其中monitorenter指令指向同步代码块的开始的位置, monitorexit指令则指明同步代码块结束的位置**. 当执行monitorenter指令时, 线程试图获取锁也就是获取monitor(monitor对象存在于每个Java对象的对象头中, synchronized锁便是通过这种方式获取锁的, 也就是为什么Java中任意对象可以作为锁的原因)的持有权. 当计数器为0的时候则可以成功获取, 获取后讲计数器设为1也就是加1. 相应的在执行monitorexit指令后, 讲计数器设为0, 表明锁被释放. 如果获取对象失败, 那么当前线程就要阻塞等到, 直到锁被另一个线程释放为止.

### synchronized 修饰方法的情况

```java
public class SynchronizedDemo2 {
    public synchronized void method() {
        System.out.println("synchronized 方法");
    }
}
```

![synchronized-method](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-6/synchronized%E5%85%B3%E9%94%AE%E5%AD%97%E5%8E%9F%E7%90%862.png)

synchronized修饰方法用的是ACC_SYNCHRONIZED标识, 该标识指明了该方法是一个同步方法, JVM通过ACC_SYNCHRONIZED访问标志来辨别一个方法是否声明为同步方法, 从而执行相应的同步调用.

## 谈谈synchronized和ReentrantLock区别

### 两者都是可重入锁

两者都是可重入锁. 可重入锁的概念是: **自己可以再次获取自己的内部锁**. 比如一个线程获得了某个对象的锁, 此时这个对象锁还没有释放, 当其再次想要获取这个对象锁的时候还是可以获取的, 如果不可重入锁的话, 就会造成死锁. 同一个线程每次获取锁, 锁的计数器自增1, 所以要等到锁的计数器下降到0时才能释放锁.

### synchronized依赖于JVM而ReentrantLock依赖于API

synchronized 是依赖于 JVM 实现的，前面我们也讲到了 虚拟机团队在 JDK1.6 为 synchronized 关键字进行了很多优化，但是这些优化都是在虚拟机层面实现的，并没有直接暴露给我们。ReentrantLock 是 JDK 层面实现的（也就是 API 层面，需要 lock() 和 unlock() 方法配合 try/finally 语句块来完成），所以我们可以通过查看它的源代码，来看它是如何实现的。

### ReentrantLock 比 synchronized 增加了一些高级功能

相比synchronized, ReentrantLock增加了一些高级功能. 主要有三点: **等待中断; 可实现公平锁; 可实现选择性通知**.

* **ReentrantLock提供了一种能够中断等待锁的线程机制**, 通过lock.lockInterruptibly()来实现这个机制. 也就是说正在等待的线程可以放弃等待, 改为处理其他事情.

* **ReentrantLock可以指定是公平锁还是非公平锁. 而synchronized只能是非公平锁. 所谓非公平锁是先等待的线程先获得锁**. ReentrantLock默认情况是非公平的, 可以通过ReentrantLock类的ReentrantLock(boolean fair)的构造方法来制定是否是公平的.

* **synchronized关键字与wait()和notify()/notifyAll()方法相结合可以实现等待/通知机制，ReentrantLock类当然也可以实现，但是需要借助于Condition接口与newCondition() 方法**。Condition是JDK1.5之后才有的，它具有很好的灵活性，比如可以实现多路通知功能也就是在一个Lock对象中可以创建多个Condition实例（即对象监视器），线程对象可以注册在指定的Condition中，从而可以有选择性的进行线程通知，在调度线程上更加灵活。 在使用notify()/notifyAll()方法进行通知时，被通知的线程是由 JVM 选择的，用ReentrantLock类结合Condition实例可以实现“选择性通知” ，这个功能非常重要，而且是Condition接口默认提供的。而synchronized关键字就相当于整个Lock对象中只有一个Condition实例，所有的线程都注册在它一个身上。如果执行notifyAll()方法的话就会通知所有处于等待状态的线程这样会造成很大的效率问题，而Condition实例的signalAll()方法 只会唤醒注册在该Condition实例中的所有等待线程。

### 性能已不是选择标准.

## 说说synchronized关键字和volatile关键字的区别

synchronized关键字和volatile关键字是两个互补的存在, 而不是对立的存在:

* volatile关键字是线程同步的轻量级实现, 所以volatile性能肯定比synchronized关键字要好. **但是volatile关键字只能适用于变量而synchronized关键字可以修饰方法以及代码块**. synchronized在Java SE 1.6之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的偏向锁和轻量锁以及其它各种优化之后执行效率有了显著提升, **实际开发中使用synchronized关键字的场景还是要多一些**.

* 多线程访问volatile不会发生阻塞, 而synchronized关键字可能发生阻塞.

* volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证。

* volatile关键字主要用于解决变量在多个线程之间的可见性，而 synchronized关键字解决的是多个线程之间访问资源的同步性.








Reference

https://snailclimb.gitee.io/javaguide/#/docs/java/Multithread/JavaConcurrencyAdvancedCommonInterviewQuestions