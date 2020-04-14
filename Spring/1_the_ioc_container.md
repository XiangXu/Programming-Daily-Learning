# The IoC Container

## Introduction to the Spring IoC Container and Beans

**Inversion of Control(IoC)** principle is also known as **dependency injection(DI)**. It is a process whereby objects define their dependencies(that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. 

The **container** then injects those dependencies when it creates the bean. This process is fundamentlly the inverse(hence the name, inversion of control) of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes or mechanism such as the Service Locator pattern.

The **org.springframework.beans** and **org.springframework.context** packages are the basis for Spring Framework's IoC **container**.

The **BeanFactory** interface provides an advanced configuration mechanism capable of managing any type of object. 

**ApplicationContext** is a sub-interface of **BeanFactory**, it adds:

* Easier integration with Spring's AOP features.
* Message resource handling(for use in internationalization).
* Event publication.
* Application layer specific contexts such as the **WebApplicationContext** for use in web application.


## Container Overview

The **org.springframework.context.ApplicationContext** interface represents the Spring IoC container and is responsible for instantiating, configuring, and assembling the beans. The container gets its instructions on what objects to instantiate(举例说明), configure and assembile by reading configuration metadata. The configuration metadata is represented in XML, Java annotations, or Java code.

Several implementations of the **ApplicationContext** interface are supplied with Spring. In stand-alone applications, it is common to create instance of **ClassPathXmlApplication** or **FileSystemXmlApplicationContext**. 


### Configuration Metadata

* **XML**
  
* **Annotation-based configuration:** Spring 2.5 introduced support for annotation-based configuraiton metadata.
  
* **Java-based configuration**: Starting with Spring 3.0, many features provided by the Spring JavaConfig project became part of the core Spring Framework. Thus, you can define beans external to your application classes by using Java rather than XML files. To use these new features, see the @Configuration, @Bean, @Import, and @DependsOn annotations.

Example of XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

1. The **id** attribute is a string that identifies the individual bean definition.
2. The **class** attribute defines the type of the bean and use the fully qualified class.


### Instantiating a Container

The location path or paths supplied to an **ApplicationContext** constructor are resources strings that let the container load configuration metadata from a variety of external resources, such as local file system, the java CLASSPATH, and so on.

```java
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
```

The following example shows the service layer objects (services.xml) configuration file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- services -->

    <bean id="petStore" class="org.springframework.samples.jpetstore.services.PetStoreServiceImpl">
        <property name="accountDao" ref="accountDao"/>
        <property name="itemDao" ref="itemDao"/>
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions for services go here -->

</beans>
```

The following example shows the data access objects daos.xml file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="accountDao"
        class="org.springframework.samples.jpetstore.dao.jpa.JpaAccountDao">
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>

    <bean id="itemDao" class="org.springframework.samples.jpetstore.dao.jpa.JpaItemDao">
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions for data access objects go here -->

</beans>
```

### Using a Container

```java
// create and configure beans
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");

// retrieve configured instance
PetStoreService service = context.getBean("petStore", PetStoreService.class);

// use configured instance
List<String> userList = service.getUsernameList();
```

## Bean Overview

**In Spring, the objects that form the backbones of your application and that are merged by the Spring IoC container are called beans. A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.**

### Naming Beans

Every bean has one or more identifiers. These identifiers must be unique within the container that hosts the bean. A bean usually has only one identifier. However, if it requires more than one, the extra ones can be considered aliases. 

In XML-based configuration metadata, you use the **id** attribute, the **name** attribute, or both to specify the bean identifiers. If you want to introduce other **aliases** for the bean, you can also specify them in the name attribute, separated by a comma (,), semicolon (;), or white space. 

#### Aliasing a Bean outside the Bean Definition

In large systems where configuration is split amongst each subsystem, with each subsystem having its own set of object definitions. In XML-based configuration metadata, you can use the <alias/> element to accomplish this. The following example shows how to do so:

```xml
<alias name="myApp-dataSource" alias="subsystemA-dataSource"/>
<alias name="myApp-dataSource" alias="subsystemB-dataSource"/>
```

If you use Javaconfiguration, the **@Bean** annotation can be used to provide aliases.

### Instantiating Beans

#### Instantiation with a constructor

When you create a bean by the constructor approach, all normal classes are usable by and compatible with Spring. That is, the class being developed does not need to implement any specific interfaces or to be coded in a specific fashion. Simply specifying the bean class should suffice. However, depending on what type of IoC you use for that specific bean, you may need a default (empty) constructor.

``xml
<bean id="exampleBean" class="examples.ExampleBean"/>
<bean name="anotherExample" class="examples.ExampleBeanTwo"/>
```

#### Instantiation with a Static Factory Method

When define a bean that you create with a static factory method, use the **class** attribute to specify the class contains the **static** factory method and an attribute named **factory-method** to specify the name of the factory method itself.

```xml
<bean id="clientService" 
    class="examples.ClientService" 
    factory-method="createInstance"/>
```

```java
public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }
}
```

#### Instantiation By Using an Instance Factory Method

Instantiation with an instance factory method invokes a non-static method of an existing bean from the container to create a new Bean. 

```xml
<!-- the factory bean, which contains a method called createInstance() -->
<bean id="serviceLocator" class="examples.DefaultServiceLocator">
    <!-- inject any dependencies required by this locator bean -->
</bean>

<!-- the bean to be created via the factory bean -->
<bean id="clientService"
    factory-bean="serviceLocator"
    factory-method="createClientServiceInstance"/>
```

```java
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientServiceImpl();

    public ClientService createClientServiceInstance() {
        return clientService;
    }
}
```

## Dependencies

### Dependency Injection

Coder is cleaner with the DI principle, and decoupling is more effective when objects are provided with their dependencies. The object doesn't look up its dependencies and does not know the location or class of the dependencies. As a result, your classes become easier to test, particularly when the dependencies are on interfaces or abstract base classes, which allow for stub or mock implementations to be used in unit tests.


Reference:

https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.htm












