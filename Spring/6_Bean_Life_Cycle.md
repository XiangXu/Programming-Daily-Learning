## Bean Life Cycle

### Initialization Callbacks

**The init-method attribute specifies a method that is to be called on the bean immediately upon instantiation.**

The org.springframework.beans.factory.InitializationBean interface specifies a single method

```java
public class ExampleBean implements InitializingBean {
   public void afterPropertiesSet() {
      // do some initialization work
   }
}
```
In the case of XML-based configuration metadata

```xml
<bean id = "exampleBean" class = "examples.ExampleBean" init-method = "init"/>
```

```java
public class ExampleBean {
   public void init() {
      // do some initialization work
   }
}
```

### Destruction Callbacks

**Destory method speficies a method that is called just before a bean is removed from the continer.**

The org.springframework.beans.factory.DisposableBean interface specifies a single method

```java
public class ExampleBean implements DisposableBean {
   public void destroy() {
      // do some initialization work
   }
}
```

In the case of XML-based configuration metadata

```xml
<bean id = "exampleBean" class = "examples.ExampleBean" destroy-method = "destroy"/>
```

```java
public class ExampleBean {
   public void destroy() {
      // do some destruction work
   }
}
```

**It is recommended that you do not use the InitializingBean or DisposableBean callbacks, because XML configuration gives much flexibility in terms of naming your method.**

Example 
```java
@Component("helloWorld")
public class HelloWorld implements InitializingBean, DisposableBean
{
    private String message;

    public HelloWorld(String message)
    {
        this.message = message;
    }

    public void getMessage()
    {
        System.out.println("Your message: " + message);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destory bean!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Create bean!!!!!!!!!!!!!!!!!!!!!!");
    }
}
```