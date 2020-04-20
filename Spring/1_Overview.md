# Overview

Reference:

https://www.tutorialspoint.com/spring/

## Benefit of Using the Spring Framework

* **Spring enables developer to develop enterprise-class application using POJOs(Plain Old Java Objects)**. The benefit of using POJOs is that you do not need an EJB container product such as application server but you have the option of using only a robust servlet container such as Tomcat or some commercial product. 

* **Spring is orgnized in a modular fashion.** Even though the number of packages and classes are substantial(丰富的), you have to worry only about the ones you need and ignore the rest.

* **Spring does not reinvent the wheel, instead it truly makes use some of the existing technologies.** For example several ORM frameworks, logging frameworks, JEE, Quartz and JDK timers, and other view technologies.

* **Testing an application written with Spring is simple because environment-dependent code is moved into this framework.** Furthermore, by using JavaBeanStyle POJOs, it becomes easier to use dependency injection for injecting test data.

* **Spring's web framework is a well-designed web MVC framework,** which provides a great alternative to web framework such as Structs or other over-engineered or less popular web framework.

* **Spring provides a convenient API to translate technology-specific exceptions (thrown by JDBC, Hibernate, or JDO) into consistent, unchecked exceptions.** 

* **Lightweight IoC containers tend to be lightweight, especially when compared with EJB containers.** This is beneficial for developing and deploying applications on computers with limited memory and CPU resources

* **Spring provides a consistent transaction management interface that can scale down to a local transaction (using a single database, for example) and scale up to global transactions (using JTA, for example).**
  
## Dependency Injection(DI)

**The Inversion Control(IoC)** is a general concept, and it can be expressed in many different ways. **Dependency Injection** is merely one concrete example of Inversion of Control.

When writing a complex Java application, application classes should be as independent as possible of other Java class to increase the possibility to reuse these classes and to test them independently of other classes while unit testing. 

**Dependency Injection helps in gluing these classes together and at same time keep them independent.**

**Dependency Injection can happyen in the way of passing parameters to the constructor or by post constructor using setter method.**

## Aspect Oriented Programming (AOP)

The functions that span(横跨) multiple points of an application are called **cross-cutting concerns** and those cross-cutting concerns are conceptually seperate from application's business logic. 

The key unit of modularity in OOP is the class, whereas in AOP the unit of modularity is the aspect. **DI helps you decouple your application objects from each other, while AOP helps you decouple cross-cutting concerns from the objects that they affect.**


