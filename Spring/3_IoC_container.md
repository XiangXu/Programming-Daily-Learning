# IoC Container

**The container will create the objects, wire them together, configure them, and manage their complete life cycle from creation till destruction.**

**Spring container uses DI to manage the components that make up an application.**

The Spring container uses **DI** to manage the components that make up an application. Those objects are called Spring **Beans**.

The containers gets its instructions on what objects to instantiate, configure, and assemble by reading the configuration metadata provided. **XML, Java annotation or Java code**.

![spring-work](https://www.tutorialspoint.com/spring/images/spring_ioc_container.jpg)

## Spring BeanFactory Container

This is the simplest container providing the basic support for DI.

## Spring ApplicationContext Container

This container adds more enterprise-specific functionality such as the ability to resolve textual messages from a properties file and the ability to publish application events to interested event listerner.

**The ApplicationContext container includes all functionality of the BeanFactorycontainer, so it is generally recommended over BeanFactory.**