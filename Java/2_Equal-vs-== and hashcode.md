# Equal vs == and hashCode()

### **equals()**
**equals(Object o)** method is used to indicate whether some other Ojbect o is equal to current object(on which the method has been invoked).

### **==**
**==** operator works perfectly(and should be in this case) for the primitive data types like int, long, boolean, etc. But for the non-primitive(or reference) data types == operator shows not the equality of the objects, but whether they refer to the same object in memory. It works in such a way due to Java memeory model(JVM): primitives and reference objects are kepet in different memory parts. 

Let's take a look at an example:
```java
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
}

class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Homer Simpson");
        Person person2 = new Person("Homer Simpson");

        System.out.println(person1 == person2);
        System.out.println(person1.equals(person2));
    }
}
```

If we run the code above, we will have the following output:<br>
false<br>
false

The reason is because right now we are using the **Object** class implementation of **equals()** method, let's take a look at the source code:
```java
public boolean equals(Object o) {
   return (this == o);
}
```
It calls **==** operator under the hood, so the false output looks more clear now.

There are serveral equals() rules that are going to help us in our implementation:

1. **Reflexive(反射)**: a.equals(a) must always return true (of course, if a != null, otherwise NullPointerException will be thrown);

2. **Symmetric(对称的)**: if a.equals(b) returns true, then b.equals(a) must always return true as well (and vice versa);

3. **Transitive**: if a.equals(b) returns true and a.equals(c) returns true, it means, that b.equals(c) must be true as well;

4. **Consistent**: if a.equals(b) returns true (or false) and neither a nor b has been changed, a.equals(b) should always return the same result.

Let's override equals() method:

```java
public boolean equals(Object o) 
{
    if(this == o)
        return true;
    
    if(o == null)
        return false;

    if(this.getlass() != o.getClass()))
        return false;

    Person p = (Person) o;
    return Objects.equals(this.name, p.name);
}

// Objects.equals source code in Object class:
public static boolean equals(Object a, Object b) {
    return (a == b) || (a != null && a.equals(b));
}
```

However this is not enough, now we are using name to compare 2 persons. But let’s imagine, that we are building an application, that is going to be used by a huge amount of people. Let it be a bank, for example. I’m pretty sure, that there will be hundreds or even thousands of situations when several different persons have the same name. For example, it could be Homer Simpson guy from Springfield and another Homer Simpson folk from South Park city. If the first one uses our application, our bank will think that both these 2 guys are the same person, so it will cause some issues: only one of them will be able to sign up in our app, or any of them can access the data of each other, etc. It is not good!

We will need to update our **Person** with an unique id. 
```java
public class Person 
{
    private long idNumber;
    private String name;

    public Person(long idNumber, String name) 
    {
        this.idNumber = idNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == 0)
            return true;
        
        if(o == null)
            return false;

        if(this.getClass() != o.getClass())
            return false;
        
        Person p = (Person) o;
        return this.idNumber == o.idNumber;
    }
}
```

### **hashCode()**
**hashCode()** method returns a hash code value of the object. Hash code,for now, is some int value. And that's it.

If we go to the **Object** class, we will see the following:
```java
public native int hashCode();
```

Keyword **native** in Java is used with methods to indicate, that the method is implemented in other language(for exmaple, C or C++). It is possible to call such methods using JNI(Java Native Interface). Such methods are used for the performance reasons or to access system or hardware resources, that is not prossible to proceed using Java.

In the Javadocs of this method, it is mentioned, that Object **hashCode()** is implemented by converting the internal address of the object into an integer. That’s why we have different results on different VMs.

There are 3 rules to hashCode method:

1. Each invocation(调用) of hashCode() method on the same object that hasn’t been changed must produce the same result each time.
   
2. If 2 objects are equal through the equals() method, then invoking the hashCode() method on each of these 2 objects must produce the same result.

3. But if invoking the hashCode() method on 2 objects produces the same result, it doesn’t mean that they are equal (through the equals() method).

But how equals() and hashcde() related to each other?

let's have a look at hashed collections fisrt. Such collections hash each their element to provide fast access to them (O(l) in most cases). The most popular Java hashed collection is **HashMap**. It is used to to keep "key - value" pairs. It uses **hashCode()** and **equals()** to proceed **put()** and **get()** operations. 

**HashMap** contains several buckets (the number changes if the size is reached), that could be used to store the **key-value** elements (such pair called Entry) in them. The bucket could contain zero, one or several elements. If there are several elements, it is called a collision, and the elements are stored in a LinkedList (it is only partly true, but, again, we are focusing on the concept right now) inside the bucket.

So when the **put(Key key, Value value)** method is called, the following steps are performed under the hood:

1. If key is null, the key-value pair(entry) is put into the 1st bucket; 
   
2. If not null, the **hashCode()** method is called on the key;
   
3. **HashMap** takes key hash value, processes it through the inernal computations to get the number of bucket to be used;
   
4. If the chosen bucket is empty, the Entry is put there, and that's it.
   
5. If the bucket is not empty, it iterates through all the existing entries inside the bucket and compares their keys with the key from put method using equals();

6. If equals() return false for each iteration, the Entry will be stored in the current bucket. 

7. If equals() reutrn true for some case, so the Entry has the same key as from the put() method, this Entry value will be replaced by the new value.

let take a look at **get(Key key)**

1. If key is null, we go to the 1st bucket and look for the Entry with the key == null — if exists, its value is returned;


2. If key is not null, the hashCode() method is called on the key;
HashMap takes the key hash value, processes it through the internal computations to get the number of the bucket to be used;We go to the bucket with the number providing on the 3rd step;

3.  If the bucket is empty — return null;

4. If that bucket contains only one Entry, we compare (equals()) that Entry key with our key: if true — return the Entry value, if false — return null;

5. If the bucket contains several elements, we iterate through each of them and compare (equals()) the key of each Entry with our key: if true — return matching Entry value, if false for each — return null.

So you have just seen, that the equals() and hashCode() methods work together inside the HashMap to get and put the data: hashCode() is used to compute the bucket number and equasls() is used to find the Entry with the same key.

Then we can update our Person class as below:
```java
public class Person {
    private long idNumber;
    private String name;

    public Person(long idNumber, String name) {
        this.idNumber = idNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Person p = (Person) o;
        return this.idNumber == p.idNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber);
    }
}
```

   



Reference:

https://itnext.io/how-and-why-to-cook-equals-and-hashcode-in-java-c108fd5b17dd