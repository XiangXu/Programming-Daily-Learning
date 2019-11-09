# Does Java pass by reference or pass by value?

1. If Java use pass-by reference, why won't a swap function work?

Java does manipulate objects by reference, and all object variables are references. However, Java doesn't pass method arguments by refernce; it passes them by value.

Here is an example:
```java
public void badSwap(int var1, int var2)
{
  int temp = var1;
  var1 = var2;
  var2 = temp;
}
```

When **badSwap()** returns, the variables passed as arguments will still hold their original values. The method will also fail if we change the arguments type from int to Object, since Java passes object references by value as well. Now, here is where it gets tricky:

```java
public void tricky(Point arg1, Point arg2)
{
  arg1.x = 100;
  arg1.y = 100;
  Point temp = arg1;
  arg1 = arg2;
  arg2 = temp;
}
public static void main(String [] args)
{
  Point pnt1 = new Point(0,0);
  Point pnt2 = new Point(0,0);
  System.out.println("X: " + pnt1.x + " Y: " +pnt1.y); 
  System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
  System.out.println(" ");
  tricky(pnt1,pnt2);
  System.out.println("X: " + pnt1.x + " Y:" + pnt1.y); 
  System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);  
}

/**
outputs: 
X: 0 Y: 0
X: 0 Y: 0
X: 100 Y: 100
X: 0 Y: 0
**/
```

The method successfully alters the value of **pnt1**, even though it is passed by value; however, a swap of **pnt1** and **pnt2** fails. This is the major source of confusion. In the **main()** method, **pnt1** and **pnt2** are nothing more than object reference. When you pass **pnt1** and **pnt2** to the **tricky()** method, Java passes the reference by value just like any other parameters. This means the references passed to the method are actually *copies of the orginal reference*. Java copies and passes the **reference by value**, not the object. Thus, method manpiuldation will alter the objets. But since the references are copies, swaps will fail. The method references swap but not the original references. Unfortunately, after a method call, you are left with only the unswapped original references. For a swap to succeed outside of the method call, we need to swap the original references, not the copies.

O'Reilly's Java in a Nutshell by David Flanagan (see Resources) puts it best: "Java manipulates objects 'by reference,' but it passes object references to methods 'by value.'" As a result, you cannot write a standard swap method to swap objects.

Tony Sintes is a principal consultant at BroadVision. Tony, a Sun-certified Java 1.1 programmer and Java 2 developer, has worked with Java since 1997.


reference: 

https://www.javaworld.com/article/2077424/learn-java-does-java-pass-by-reference-or-pass-by-value.html