# Enum in Java

Enumerstions serve the purpose of representing a goup of named constants in a programming language.

Enums are used when we know all possible values at **compile time**, such as choices on a menu, rounding modes, command line flags, etc. It is not necessary that the set of constants in a enum type stay **fixed** for all time.

In Java(from 1.5), enums are represented using **enum** data type. We can also add variables, methods and constructors to it. **The main objective of enum is to define our own data types**.

## Declaration of enum in java:

* Enum declaration can be done outside a Class or inside a Class but not inside a Method.

```java
enum Color 
{ 
    RED, GREEN, BLUE; 
} 
  
public class Test 
{ 
    // Driver method 
    public static void main(String[] args) 
    { 
        Color c1 = Color.RED; 
        System.out.println(c1); 
    } 
}

public class Test 
{ 
    enum Color 
    { 
        RED, GREEN, BLUE; 
    } 
  
    // Driver method 
    public static void main(String[] args) 
    { 
        Color c1 = Color.RED; 
        System.out.println(c1); 
    } 
}
```

* First line inside enum should be list of constants and then other things like methods, variables and constructor.
  
* According to Java naming conventions, it is recommended that we name constant with all capital letters.

## Important points of enum:

* Every enum internally implemented by using Class.

```java
/* internally above enum Color is converted to
class Color
{
     public static final Color RED = new Color();
     public static final Color BLUE = new Color();
     public static final Color GREEN = new Color();
}*/
```

* Every enum constant represents an **object** of type enum.

* enum type can be passed as an argument to **switch** statement.

* Every enum constant is always implicitly **public static final**. Since it is **static**, we can access it by using enum Name. Since it is **final**, we cannot create child enums.

* We can declare **main() method** inside enum. Hence we can invoke enum directly from the Command Prompt.

## Enum and Inheritance

* All enums implicitly extend **java.lang.Enum.class**. As a class can only extend **one** parent in Java, so an enum cannot extend anything else.

***toString() method** is overridden in **java.lang.Enum class**. which returns enum constant name.

* enum can implement many interfaces.

## values(), ordinal()顺序的 and valueOf() methods

* These methods are present inside java.lang.Enum.

* **values() method** can be used to return all values present inside enum.

* Order is important in enums. By using **ordinal() method**, each enum constant index can be found, just like array index.

* **valueOf() method** returns the enum constant of the specified string value, if exists.

```java
enum Color 
{ 
    RED, GREEN, BLUE; 
} 
  
public class Test 
{ 
    public static void main(String[] args) 
    { 
        // Calling values() 
        Color arr[] = Color.values(); 
  
        // enum with loop 
        for (Color col : arr) 
        { 
            // Calling ordinal() to find index 
            // of color. 
            System.out.println(col + " at index "
                             + col.ordinal()); 
        } 
  
        // Using valueOf(). Returns an object of 
        // Color with given constant. 
        // Uncommenting second line causes exception 
        // IllegalArgumentException 
        System.out.println(Color.valueOf("RED")); 
        // System.out.println(Color.valueOf("WHITE")); 
    } 
}
```

## constructor and methods

* enum can contain constructor and it is executed separately for each enum constant at the time of enum class loading. 

* We cannot create enum objects explicitly and hence we cannot invoke enum constructor directly.

* enum can contain both **concrete** methods and **abstract** methods. If an enum class has an abstract method, then each instance of them enum class must implement it.

```java
enum Color 
{ 
    RED, GREEN, BLUE; 
  
    // enum constructor called separately for each 
    // constant 
    private Color() 
    { 
        System.out.println("Constructor called for : " + 
        this.toString()); 
    } 
  
    public void colorInfo() 
    { 
        System.out.println("Universal Color"); 
    } 
} 
  
public class Test 
{     
    // Driver method 
    public static void main(String[] args) 
    { 
        Color c1 = Color.RED; 
        System.out.println(c1); 
        c1.colorInfo(); 
    } 
} 
```

## Enum with Customized Value in Java

By default enums have their own string values, we can also assign some customer values to enums.

```java
enum  Fruits
{
    APPLE(“RED”), BANANA(“YELLOW”), GRAPES(“GREEN”);
}
```

**Now to use this enum in code, there are some points we have to follow:**

1. We have to create parameterized constructor for this enum class. Why? Because as we know that enum class's object cannot be create explicitly so for initializing we use parameterized constructor. And the constructor cannot be public or protected it must have private or default modifiers. Why? if we create public or protected, it will allow initializing more than one objects. This is totally against enum conecpt.

2. We have to create one getter method to get the value of enums.

```java
package EnumStudy;

enum TrafficSignal
{
    RED("STOP"), GREEN("GO"), ORANGE("SLOW DOWN");

    private String action;

    public String getAction()
    {
        return action;
    }

    private TrafficSignal(String action)
    {
        this.action = action;
    }
}

public class EnumParameterTest
{
    public static void main(String[] args) {
        TrafficSignal[] signals = TrafficSignal.values();

        for (TrafficSignal signal : signals)
        {
            // use getter method to get the value
            System.out.println("name : " + signal.name() +
                    " action: " + signal.getAction() );
        }
    }
}

```

Reference

https://www.geeksforgeeks.org/enum-in-java/
