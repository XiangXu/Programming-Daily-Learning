# What is a Spring Bean?

Definition of beans in the **Spring Framework Documentation**:

*In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container*.

## Inversion of Controller

**It is a process in which an object defines its dependencies without creating them**.

Instead of constructing dependencies by itself, an object can retrive its dependencies from an IoC container. **All we need to do is to provide the container with appropriate configuration metadata**.

**When a Spring IoC container constructs objects of those types, all the objects are called Spring beans as they are managed by the IoC container**.

```java
@Component
public class Company {
    // this body is the same as before
}

@Configuration
@ComponentScan(basePackageClasses = Company.class)
public class Config {
    @Bean
    public Address getAddress() {
        return new Address("High Street", 1000);
    }
}

ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
Company company = context.getBean("company", Company.class);
assertEquals("High Street", company.getAddress().getStreet());
assertEquals(1000, company.getAddress().getNumber());
```