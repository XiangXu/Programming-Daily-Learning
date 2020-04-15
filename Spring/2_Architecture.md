# Architecture

The Spring Framework provdes about 20 modules which can be used based on application requirement.


![architecture](https://www.tutorialspoint.com/spring/images/spring_architecture.png)

## Data Access / Integration

The Data Access / Integration layer consists of the JDBC, ORM, OXM, JMS and Transaction modules.

* The **DJBC(Java Database Connectivity)** module provides a JDBC-abstraction layer that removes the need for tedious JDBC related coding.

* The **ORM(Object Related Mapping)** module provides integration layers for popular object-relational mapping APIs, including JPA, JPO, Hibernate, and iBatis. 

* The **OXM(Object XML Mapping)** provides an abstraction layer that supports Object/XML mapping implementions for JAXB, Castor, XMLBeans, JiBX and XStream.

* The **JMS(Java Messaging Service)** contains features for producing and consuming messages.

* The **Transaction** module supports programmatic and declarative transaction management for classes that implement special interfaces and for all your POJOs. 


## Web(MVC / Remoting)

* The **Web** module provides basic web-oriented integration features such as miltipart file-upload functionality and the initialization of the IoC container using servlet and a web-oriented application context. 

* The **Web-MVC** module contains Spring's Model-View-Controller implemention for web applications.

* The **Web-Socket** module provides support for WebSocket-based, two-way communication between the client and the server in web applications.

*The **Web-Portle(门户组件)** module provides the MVC implemention to be used in portle environment and mirrors the functionality of Web-Servlet module.


## Miscellaneous

* The **AOP** module provides an aspect-oriented programming implemention allowing you to define method-interceptors(拦截器) and pointcuts to clean decouple code that implements functionality that should be seperated.

* The **Aspects** module provdes the integration with AspectJ, which is again a powerful and mature AOP framework.

* The **Instrumentation(仪器)** module provides class instrumentation support and class loader implementations to be used in certain application services.

* The **Messaging** module provides support for STOMP(Streaming Text Oriented Messaging Protocol) as the WebSocket sub-protocol to use in applications. It also supports an annotation programming model for routing and processing STOMP messages from WebSocket.

* The **Test** module supports the testing of Spring components with JUnit or TestNG frameworks

## Core Container

* The **Core** module provides the fundenmental parts of framework, including the IoC and Dependency Injections features. 

* The **Bean** module provides BeanFactory, which is a sophisticated(复杂的) implementation of the factory pattern.

* The **Context** module builds on the solid base provided by the Core and Beans modules and it is medium to access any objects and configured. The **ApplicationContext** interface is the focal(焦点) point of Context module.

* The **SpEL(Spring Expression Language)** module provides a power expression language for querying and manipulating an object graph at runtime. 

Reference:

https://www.tutorialspoint.com/spring/spring_architecture.htm