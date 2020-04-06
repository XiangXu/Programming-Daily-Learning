# JVM Architecture

JVM(Java Virtual Machine) acts as a run-time engine to run Java applications. JVM is the one that actually calls the main method in a java code. JVM is a part of JRE(Java Runtime Environment).

![alt text][JVM]

[JVM]: https://media.geeksforgeeks.org/wp-content/uploads/jvm-3.jpg


## Class loader Subsystem

It is mainly responsible for three activities:

* Loading 
* linking
* Initialization

### Loading

The Class loader reads the .class file, generate the corresponding binary data and save it in method area. For each .class file, JVM stores following information in method area.

* Fully qualified name of the loaded class and its immediate parent class.
* Whether .class file is releated to Class or Interface or Enum.
* Modifier, Variables and Method information etc. 

After loading .class file, **JVM creates an object of type Class to represent this file in the heap memory**. 

### Linking

Performs verification, preparation, and(optionally) resolution(决议).

* **Verification**: it ensures the correctness of .class file. It checks whether this file is properly formatted and generated by valid compiler or not. If verification fails, we get run-time exception java.lang.VerifyError.

* **Preparation**: JVM allocates memory for class variables and initializing the memory to default values.

* **Resolution**: It is the process of replacing symbolic references from the type which direct references. It is done by searching into method area to locate the referenced entity.

### Initialization

In this phase, all static variables are assigned with their values defined in the code and static block. This is executed from top to bottom in a class and from parent to child in class hierarchy. 

* Bootstrap class loader: Every JVM implementation must have a bootstrap class loader, capable of loading trusted classes. It loads core java API classes present in JAVA_HOME/jre/lib directory. This path is popularly known as bootstrap path. It is implemented in native languages like C, C++.

* Extension class loader: It is child of bootstrap class loader. It loads the classes present in the extension directories JAVA_HOME/jre/lib/ext(Extension path) or any other directory specified by the java.ext.dirs system property. It is implemented in java by the sun.misc.Launcher$ExtClassLoader class.

* System/Application class loader : It is child of extension class loader. It is responsible to load classes from application class path. It internally uses Environment Variable which mapped to java.class.path. It is also implemented in Java by the sun.misc.Launcher$AppClassLoader class.

![alt text][loader]

[loader]: https://media.geeksforgeeks.org/wp-content/uploads/jvmclassloader.jpg


## JVM Memory

### Method area

In method area, all class level information like class name, immdeiate parent class, methods and variables information etc. are stored, including static variables. There is only one method area per JVM, and it is a shared resource. 

### Heap area:

Information of all objects is stored in heap memory. There is also one Heap Area per JVM. It is also a shared resource.

### Stack area:

For every thread, JVM create one run-time stack which is stored here. Every block of this stack is called activation record/stack frame which stores methods calls. All local variables of that method are stored in their corresponding frame. After a thread terminate, it's run-time stack will be destoryed by JVM. It is not a shared resource.

### PC registers:

Store address of current execution instruction of a thread. Obviously each thread has seperate PC Registers.

### Native method stacks:

For every thread, seperate native stack is created. It stores native method information.

![alt text][memory]

[memory]: https://media.geeksforgeeks.org/wp-content/uploads/jvm-memory-2.jpg


## Execution Engine

Execution Engine execute the .class. It reads the byte-code line by line, use data and information present in various memory area and execute instructions. It can be classified in three parts:

* Interpreter : It interprets the bytecode line by line and then executes. The disadvantage here is that when one method is called multiple times, every time interpretation is required.

* Just-In-Time Compiler(JIT) : It is used to increase efficiency of interpreter.It compiles the entire bytecode and changes it to native code so whenever interpreter see repeated method calls,JIT provide direct native code for that part so re-interpretation is not required,thus efficiency is improved.

* Garbage Collector : It destroy un-referenced objects.

## Java Native Interface (JNI) :

It is an interface which interacts with the Native Method Libraries and provides the native libraries(C, C++) required for the execution. It enables JVM to call C/C++ libraries and to be called by C/C++ libraries which may be specific to hardware.

It is a collection of the Native Libraries(C, C++) which are required by the Execution Engine.



Reference

https://www.geeksforgeeks.org/jvm-works-jvm-architecture/