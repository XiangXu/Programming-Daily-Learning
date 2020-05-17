# The Spring @Qualifier Annotation

## Autowire Need for Disambiguation

The **@Autowired** annotation is a great way of making the need to inject a dependency in Spring explicit.

**If more than one bean of the same type is available in the container, the framework will throw NoUniqueBeanDefinitionException**.

```java
@Component("fooFormatter")
public class FooFormatter implements Formatter {
  
    public String format() {
        return "foo";
    }
}
 
@Component("barFormatter")
public class BarFormatter implements Formatter {
  
    public String format() {
        return "bar";
    }
}
 
@Component
public class FooService {
      
    @Autowired
    private Formatter formatter;

```

**Spring doesn't know which bean to inejct**.

## @Qualifier Annotation

By using the **@Qualifier** annotation, we can **eliminate the issue of which bean needs to be injected**.

```java
public class FooService {
      
    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;
}

@Component
@Qualifier("fooFormatter")
public class FooFormatter implements Formatter {
    //...
}

@Component
@Qualifier("barFormatter")
public class BarFormatter implements Formatter {
    //...
}
```

## @Qualifier vs @Primary 

**@Primary** can be used to decide which bean to inject when ambiguity is present regarding dependency injection.

**This annotation defines a preference when multiple beans of the same type are present**.

```java
@Configuration
public class Config {
  
    @Bean
    public Employee johnEmployee() {
        return new Employee("John");
    }
  
    @Bean
    @Primary
    public Employee tonyEmployee() {
        return new Employee("Tony");
    }
}
```

## @Qualifier vs Autowiring By Name

```java
public class FooService {
      
    @Autowired
    private Formatter fooFormatter;
}
```