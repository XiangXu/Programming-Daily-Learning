# Spring Bean Annotations

## @ComponentScan

**@ComponentScan** configures which **packages to scan for classes with annotation configuration**.

```java
@Configuration
@ComponentScan(basePackages = "com.baeldung.annotations")
class VehicleFactoryConfig {}
```

Also we can point to classes in the base packages with the **basePackageClasses** arguments:

```java
@Configuration
@ComponentScan(basePackageClasses = VehicleFactoryConfig.class)
class VehicleFactoryConfig {}
```

Both arguments are arrays so that we can provide multiple packages for each. 

If no argument is specified, the scanning happens from the same package where the @ComponentScan annotated class is present.


## @Component

**Component** is a class level annotation. During the component scan, **Spring Framework automatically detects classes annotated with @Component**.

```java
@Component
class CarUtility {
    // ...
}
```

By default, the bean instances of this class have the same name as the class name with lowercase initial.

**@Repository**, **@Service**, **@Configuration** and **@Controller** are all meta-annotation of **@Component**.

## @Repository

DAO or Repository classes usually represent the database access layer in application, and should be annotated with @Repository

```java
@Repository
class VehicleRepository {
    // ...
}
```

One advantage of using this annotation is that **it has automatically persistence exception translation enabled**. When using a persistence framework such as Hibernate, native exceptions thrown within classes annotated with @Repoitory will be automatically translated into subclass of Spring's DataAccessException. 

If you are using Spring Abstraction for JDBC, JPA/Hibernate or JDO then you don't have to implement JDBC or RDBMS vendor specific error handling. So **Spring wraps all those exceptions and then wraps them into DataAccessException class**. When you want to switch to different persistence technology, you don't have to worry about refractoring your code.

## @Service

The **business logic** of an application usually resides within the service layer.

```java
@Service
public class VehicleService {
    // ...    
}
```
## @Controller

**@Controller** is a class level annotation which tells the Spring Framework that this class serves as **controller in Spring MVC**.
```java
@Controller
public class VehicleController {
    // ...
}
```

## @Configuration

Configuration classes can **contain bean definition methods** annotated with **@Bean**.

```java
@Configuration
class VehicleFactoryConfig {
 
    @Bean
    Engine engine() {
        return new Engine();
    }
 
}
```

Reference:

https://www.baeldung.com/spring-bean-annotations

https://stackoverflow.com/questions/32186035/spring-dao-repository-exception-handling




