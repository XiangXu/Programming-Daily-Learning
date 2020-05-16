# Java Serialization and Deserialization

**Serialization is a mechanism of writing the state of an object into a byte stream**. It is mainly used in Hibernate, JPA.

**Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory**.

The serialization and deserialization process is platform independent, it means you can serialize an object in a platform and deserialize in different platform.

For serializing the object, we call the **writeObject()** method ObjectOutputStream, and for deserialication we call **readObject()** method of ObjectInputStream class.

We must have to implement the Serializable interface for serializing the object.

It is mainly used to **travel object's state on the network**.

## java.io.Serializable Interface

Serializable is a marker interface(has no data member and method). It is used to "mark" Java classes so that the object of these classes may get a certain capability. The Cloneable and Remote are also marker interface.

It must be implemented by the class whose object you want to persist.

**The String class and all the wrapper classes implement the java.io.Serializable interface by default**.

The ObjectOutputStream class is used to write primitive data types, and Java objects to an OutputStream. Only objects that support the java.io.Serializable interface can be written to streams.

```java
public class Persist
{
    public static void main(String[] args)
    {
        try
        {
            Student s1 = new Student(211, "Xiang");
            FileOutputStream fileOutputStream = new FileOutputStream("student.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(s1);
            fileOutputStream.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
```

An ObjectInputStream deserializes objects and primitive data written using an ObjectOutputStream.

```java
public class Depersist
{
    public static void main(String[] args) {
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student.txt"));
            Student student = (Student) inputStream.readObject();

            System.out.println(student.getId() +": " + student.getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
```

1. **If a class implements serializable then all its sub classes will also be serializable**.
2. **If a class has a reference to another class, all the references must be Serializable otherwise serialization process will not be performed**.
3. **If there is any static data member in a class, it will not be serialized because static is the part of class not object**.
4. **In case of array or collection, all the objects of array or collection must be serializable. If any object is not serialiizable, serialization will be failed**.

**Externalizable in Java**

The **Externalizable** interface provides the facility of **writing the state of an object into byte stream in compress format**. It is not a maker interface.

It provides two methods:

* public void writeExternal(ObjectOutput out) throws IOException
* public void readExternal(ObjectInput in) throws IOException


## The Transient Keyword

Java **Transient** keyword is used in serialization. If you define any data member as transient, it will not be serialized.

```java
class Employee implements Serializable
{  
    transient int id;  
    String name;  
    public Student(int id, String name) {  
    this.id = id;  
    this.name = name;  
    }  
}  
```

Now, id will not be serialized, so when you deserialize the object after serialization, you will not get the value of id. It will return default value always. In such case, it will return 0 because the data type of id is an integer.

## SerialVersionUID

**The serialization procee at runtime associates an id with each Serializable class which is known as SerialVersionUID**. It is used to **verify the sender and receiver of the serialiable object**. The sender and receiver must be the same. To verify it, SerialVersionUID is used. The sender and receiver must have the same SerialVersionUID, otherwise, **InvalidClassException will be thrown when you deserialize the object**. We can also declare our own SerialVersionUID in the Serializable class. To do so, you need to create a field SerialVersionUID and assign a value to it. It must be of the **long type with static and final**. It is suggested to explicitly declare the serialVersionUID field in the class and have it private also. For example:

```java
private static final long serialVersionUID=1L;  
```





Reference

https://www.javatpoint.com/serialization-in-java

