# Java Fundamental Stuff

### How to use equal() method correctly?
```java
String str = null;
if (str.equals("SnailClimb")) {
  ...
} else {
  ..
}
```

Code above will throw NullPointExeception. If we update code like below, code should work correctly.

```java
"SnailClimb".equals(str);// false 
```

However, we would recommend to use **java.util.Objects#equals** 
```java
Objects.equals(null,"SnailClimb");// false
```

Here is the source code: 
```java
public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
```


### BigDecimal

The BigDecimal class provides operations on double numbers for arithmetic, scale handling, rounding, comparison, format conversion and hashing. It can handle very large and very small floating point numbers with great precision but compensating with the time complexity a bit.

Let's look at an example:
```java
float a = 1.0f - 0.9f;
float b = 0.9f - 0.8f;
System.out.println(a);// 0.100000024
System.out.println(b);// 0.099999964
System.out.println(a == b);// false loss of precision

BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("0.9");
BigDecimal c = new BigDecimal("0.8");
BigDecimal x = a.subtract(b);// 0.1
BigDecimal y = b.subtract(c);// 0.1
System.out.println(x.equals(y));// true 
```

### Comparison

**a.compareTo(b)** : return -1 means less, 0 means equal, 1 means bigger.
```java
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("0.9");
System.out.println(a.compareTo(b));// 1
```

### Scale
```java
BigDecimal m = new BigDecimal("1.255433");
BigDecimal n = m.setScale(3,BigDecimal.ROUND_HALF_DOWN);
System.out.println(n);// 1.255
```

In order to prevent precision loss, we would recommend to use **BigDecimal(String)**. 


### Arrays.asList()

Convert array into list: 
```java
String[] myArray = { "Apple", "Banana", "Orange" }； 
List<String> myList = Arrays.asList(myArray);
// or
List<String> myList = Arrays.asList("Apple","Banana", "Orange");
```

JDK Source Code:
```java
public static <T> List<T> asList(T... a) {
    return new ArrayList<>(a);
}
```

When you use this method, you need to pass an array of object rather than an arry of primitive.

Let's look at an example:
```java
int[] myArray = { 1, 2, 3 };
List myList = Arrays.asList(myArray);
System.out.println(myList.size());//1
System.out.println(myList.get(0));//address
System.out.println(myList.get(1));//Error：ArrayIndexOutOfBoundsException
int [] array=(int[]) myList.get(0);
System.out.println(array[0]);//1

//Correct way:
Integer[] myArray = { 1, 2, 3 };
```

The list of **Arrays.asList()** returned is not the type of **java.util.ArrayList**, it is an internal class in **java.util.Arrays**. **So you can not modify it by using default List moethods.**
```java
List myList = Arrays.asList(1, 2, 3);
myList.add(4);//Error：UnsupportedOperationException
myList.remove(1);//Error：UnsupportedOperationException
myList.clear();//Error：UnsupportedOperationException

List myList = Arrays.asList(1, 2, 3);
System.out.println(myList.getClass());//class java.util.Arrays$ArrayList
```

#### How to convert array into ArrayList?
```java
List list = new ArrayList<>(Arrays.asList("a", "b", "c"))
```

Java 8:
```java
Integer [] myArray = { 1, 2, 3 };
List myList = Arrays.stream(myArray).collect(Collectors.toList());

int [] myArray2 = { 1, 2, 3 };
List myList = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
```

Apache Commons Collections:
```java
List<String> list = new ArrayList<String>();
CollectionUtils.addAll(list, str);
```

## Difference between Collection.toArray() and Collection.toArray(Object obj[])

One is generic, the other isn't. toArray() will return Object[] while toArray(T[]) will return an array of type T[].

```java
public static void main(String[] args) {
    Object[] baseArray = new ArrayList<String>().toArray();
    System.out.println(baseArray.getClass().getCanonicalName());

    String[] improvArray = new ArrayList<String>().toArray(new String[5]);
    System.out.println(improvArray.getClass().getCanonicalName());
}
//Output:
//java.lang.Object[]
//java.lang.String[]
```

### Do not add/remove in foreach loop. To safely remove from a collection while iterating over it you should use an Iterator. 
```java
List<String> names = ....
Iterator<String> i = names.iterator();
while (i.hasNext()) {
   String s = i.next(); // must be called before you can call i.remove()
   // Do something
   i.remove();
}
```



Reference:

https://snailclimb.gitee.io/javaguide/#/docs/java/Java%E7%96%91%E9%9A%BE%E7%82%B9?id=11-%e6%ad%a3%e7%a1%ae%e4%bd%bf%e7%94%a8-equals-%e6%96%b9%e6%b3%95

https://www.geeksforgeeks.org/bigdecimal-class-java/

https://stackoverflow.com/questions/16748030/difference-between-arrays-aslistarray-and-new-arraylistintegerarrays-aslist

https://stackoverflow.com/questions/20308285/difference-between-collection-toarray-and-collection-toarrayobject-obj