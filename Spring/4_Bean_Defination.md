# Spring Bean

## Bean Definition

The objects that form the backbone of your application and that are managed by the Spring IoC container are called **beans**.

**A bean** is an object that is instantiated, assembled, and otherwise managed by Spring IoC container. 

Following are the three important methods to provide configuration metadata to the Spring Container.

* XML based configuration file.
* Annotation-based configuration
* Java-based configuration

```java
//@Component("helloWorld")
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
}

@Configuration
public class ModuleConfiguration
{
    @Bean
    public HelloWorld helloWorld()
    {
        return new HelloWorld("Hello World");
    }
}

@SpringBootApplication
public class DemoApplication
{
	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
		helloWorld.getMessage();
	}
}
```
