# Java Nested Class

Java Inner class or nested class is a class which is declared inside the class or interface.

**We use inner classes to logically group classes and interfaces in one place so that it can be more readable and maintainable**.

Additinoally, it can access all the members of outer class including private members and methods.

## Advantage of Java Inner Classes

1. Nested classes represent a special type of relationship that **it can access all members(data members and methods) of outer class** including private.
2. Nested classes are used **to develop more readable and maintainable code** because logically group classes and interfaces in one place only.
3. **Code optimization**: It requires less code to write.

## Difference between nested class and inner class in Java

Inner class is part of nested class. **Non-static nested classes are known as inner class**.

## Type of Nested classes

* Non-static nested class(inner class): 
  1. Member inner class
  2. Anonymous inner class  
  3. Local inner class.
* Static nested class.  

### Java Member inner class

A non-static class that is created inside a class but outside a method is called member inner class.

```java
class TestMemberOuter1{  
 private int data=30;  
 class Inner{  
  void msg(){System.out.println("data is "+data);}  
 }  
 public static void main(String args[]){  
  TestMemberOuter1 obj=new TestMemberOuter1();  
  TestMemberOuter1.Inner in=obj.new Inner();  
  in.msg();  
 }  
}  
```

## Java Anonymous inner class

A class has no name is known anonymous inner class in Java. **It should be used if you have override method of class or interface**. It creates by : Class(maybe abstract or concrete) and Interface.

```java
abstract class Person{  
  abstract void eat();  
}  
class TestAnonymousInner{  
 public static void main(String args[]){  
  Person p=new Person(){  
  void eat(){System.out.println("nice fruits");}  
  };  
  p.eat();  
 }  
}  

interface Eatable{  
 void eat();  
}  
class TestAnnonymousInner1{  
 public static void main(String args[]){  
 Eatable e=new Eatable(){  
  public void eat(){System.out.println("nice fruits");}  
 };  
 e.eat();  
 }  
}  
```

## Java Local inner class

A class created inside a method is called local inner class in Java. If you want to invoke the methods of local inner class, you must instantiate this class inside the method.

```java
public class localInner1{  
 private int data=30;//instance variable  
 void display(){  
  class Local{  
   void msg(){System.out.println(data);}  
  }  
  Local l=new Local();  
  l.msg();  
 }  
 public static void main(String args[]){  
  localInner1 obj=new localInner1();  
  obj.display();  
 }  
}  

class localInner2{  
 private int data=30;//instance variable  
 void display(){  
  int value=50;//local variable must be final till jdk 1.7 only  
  class Local{  
   void msg(){System.out.println(value);}  
  }  
  Local l=new Local();  
  l.msg();  
 }  
 public static void main(String args[]){  
  localInner2 obj=new localInner2();  
  obj.display();  
 }  
}  

// 50
```

## Java static nested class

A static class created inside a class is called static nested class in Java. It cannot access non-static data members and methods. It can be accessed by outer class name.

* It can access static data member of outer class including private.
* Static nested class cannot access non-static(instance) data member or method.

```java
class TestOuter1{  
  static int data=30;  
  static class Inner{  
   void msg(){System.out.println("data is "+data);}  
  }  
  public static void main(String args[]){  
  TestOuter1.Inner obj=new TestOuter1.Inner();  
  obj.msg();  
  }  
}  

class TestOuter2{  
  static int data=30;  
  static class Inner{  
   static void msg(){System.out.println("data is "+data);}  
  }  
  public static void main(String args[]){  
  TestOuter2.Inner.msg();//no need to create the instance of static nested class  
  }  
}
```