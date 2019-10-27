# Equal vs ==

In general both equal() and "==" operator in Java are used to compare objects to check equality but here are some of differences between the two:

1. We can use == operators for **reference comparison (address comparison)** and .equals() method for **content comparison**. In simple word, == check if both objects point to the same memory location whereas .equals() evaluates to the comparison of values in objects. 

2. If class does not override the equal method, then by default it uses equal method of the closest parent class that has overriden this method.

```java
public class Test{
    public static void main(String args[])
    {
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
/**
output: false, true
**/
```