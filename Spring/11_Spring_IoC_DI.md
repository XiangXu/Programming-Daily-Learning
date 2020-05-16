# Intro to Inversion of Control and Dependency Injection with Spring

**Inversion of Control** is a principle in software engineering by which **the control of objects or portions of a program is trasnferred to a container or framework**.

IoC enables a framework to take a control of the flow of a program and makes calls to our custom code. To enable this, frameworks use abstractions with additional behavior build in. **If we want to add our own behavior, we need to extend the classes of the framework or plugin our own classes**.

The advantages of this architecture are:

* decoupling the execution of a task from its implementation.
* making it easier to switch between different implementations.
* greatly modularity of a program.
* greater ease in testing a program by isolating a component or mocking its dependencies and allowing components to communicate through contracts.

**Inversion of Control** can be achieved through various mechanisms such as: **Strategy design pattern**, **Service Locator pattern**, **Factory pattern**, and **Dependency Injection (DI)**.


## The Spring IoC Container 

In the Spring framework, the IoC container is represented by the interface **ApplicationContext**. 

The Spring container is responsible for **instantiating, configuring, and assembling objects know as beans, as well as managing their lifecycle**.

The Spring provides several implementations of the ApplicationContext interface

* **ClassPathXmlApplicationContext**: it will read files from your classpath. They must be in classes folder of your web application or in a jar in your libfolder.
* **FileSystemXmlApplicationContext**: it can access all your file system, for example c:/config/applicationContext.xml.
* **WebApplicationContext**: it is for web application.


Reference:

https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring

https://stackoverflow.com/questions/10412225/what-is-difference-between-class-path-file-system
