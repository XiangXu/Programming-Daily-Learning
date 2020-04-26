## Bean Post Processors

The **BeanPostProcessor** interface defines callback methods that you can implement to provide your own instantiation logic, dependency-resolution logic, etc. 

The **BeanPostProcessor** operate on bean (or object) instances, which means that the Spring IoC container instantiates a bean instance and then BeanPostProcessor interfaces do their work.

An **ApplicationContext** automatically detects any beans that are defined with the implementation of the **BeanPostProcessor** interface and registers these beans as postprocessors, to be then called appropriately by the container upon bean creation.

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

@Component
public class InitHelloWorld implements BeanPostProcessor
{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        System.out.println("Before initialization: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After initialization: " + beanName);
        return bean;
    }
}

// Before initialization: helloWorld
// Create bean!!!!!!!!!!!!!!!!!!!!!!
// After initialization: helloWorld
```

More information:

https://www.jianshu.com/p/1417eefd2ab1

