## Dependency Injection

When writing a complex Java application, application classes should be as independent as possible of other Java classes to increase possibility to reuse the these classes and to test them independently of other classes while unit testing. 

## Constructor-based dependency injection
```java
@Component("spellCheck")
public class SpellCheck
{
    public SpellCheck(){
        System.out.println("Inside SpellCheck constructor." );
    }
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
    }
}

public class TextEditor
{
    private SpellCheck spellCheck;

    // The reason why we don't need autowired here:
    // Starting with Spring 4.3, if a class, which is configured as a Spring bean, has only one constructor, the @Autowired         annotation can be omitted and Spring will use that constructor and inject all necessary dependencies.
    public TextEditor(SpellCheck spellCheck)
    {
        System.out.println("Inside TextEditor constructor." );
        this.spellCheck = spellCheck;
    }

    public void spellCheck() {
        spellCheck.checkSpelling();
    }
}

@Configuration
public class HelloWorldConfiguration
{
    @Bean
    public TextEditor textEditor(SpellCheck spellCheck)
    {
        return new TextEditor(spellCheck);
    }
}

@SpringBootApplication
public class DemoApplication
{
	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		TextEditor textEditor = (TextEditor) context.getBean("textEditor");
		textEditor.spellCheck();;
	}


}
```

## Setter-based dependency injection

```java
@Autowired
public void setSpellCheck(SpellCheck spellCheck)
{
    System.out.println("Inside setSpellCheck" );
    this.spellCheck = spellCheck;
}
```